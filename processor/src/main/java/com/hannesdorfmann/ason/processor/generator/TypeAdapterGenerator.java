package com.hannesdorfmann.ason.processor.generator;

import com.hannesdorfmann.ason.Config;
import com.hannesdorfmann.ason.annotation.Ignore;
import com.hannesdorfmann.ason.annotation.Json;
import com.hannesdorfmann.ason.annotation.Property;
import com.hannesdorfmann.ason.processor.ProcessorMessage;
import com.hannesdorfmann.ason.processor.repacked.com.squareup.javawriter.JavaWriter;
import java.io.Writer;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

/**
 * This class is responsible to generate the parser java code for a annotated class
 *
 * @author Hannes Dorfmann
 */
public class TypeAdapterGenerator {

  private Elements elementUtils;
  private Types typeUtils;
  private Filer filer;
  private RoundEnvironment roundEnvironment;
  private ProcessingEnvironment processingEnv;

  public TypeAdapterGenerator(RoundEnvironment roundEnv, Elements elementUtils, Types typeUtils,
      Filer filer) {
    this.elementUtils = elementUtils;
    this.typeUtils = typeUtils;
    this.filer = filer;
  }

  private String getPackageName(TypeElement type) {
    return elementUtils.getPackageOf(type).getQualifiedName().toString();
  }

  public void generateCode(Element annotatedElement) throws Exception {

    // Check that the annotated element is a class
    if (!(annotatedElement instanceof TypeElement)
        || annotatedElement.getKind() != ElementKind.CLASS) {

      ProcessorMessage.error(annotatedElement, "The @%s Annotation can only be applied on classes",
          Json.class.getSimpleName());
      return;
    }

    TypeElement annotatedClass = (TypeElement) annotatedElement;

    // check config
    Json jsonAnnotation = annotatedElement.getAnnotation(Json.class);
    Config config = null;
    com.hannesdorfmann.ason.annotation.Config[] configs = jsonAnnotation.value();

    if (configs.length > 1) {
      // More than 1 --> not allowed
      ProcessorMessage.error(annotatedElement, "Only one @%s is allowed for class ",
          com.hannesdorfmann.ason.annotation.Config.class.getSimpleName(),
          annotatedElement.getSimpleName());
    }

    if (configs.length == 1) {
      config = Config.fromAnnotation(configs[0]); // Read the config annotation
    }

    JsonClass jsonClass = new JsonClass(annotatedClass, getPackageName(annotatedClass), config);

    // check fields
    TypeElement currentClass = annotatedClass;

    do {

      for (Element enclosed : annotatedClass.getEnclosedElements()) {
        // Check fields
        if (enclosed instanceof VariableElement && enclosed.getKind() == ElementKind.FIELD) {
          VariableElement field = (VariableElement) enclosed;
          if (field.getAnnotation(Ignore.class) != null) {
            continue;
          }

          // Check accessablility
          checkPropertyAccessability(field, currentClass, annotatedClass);

          // Read properties
          String name = field.getSimpleName().toString();
          boolean required = false;

          Property propertyAnnotation = field.getAnnotation(Property.class);
          if (propertyAnnotation != null) {
            String annotatedName = propertyAnnotation.value();
            if (annotatedName != null && annotatedName.length() > 0) {
              name = annotatedName;
            }

            required = propertyAnnotation.required();
          }

          // Check if field already mapped
          checkPropertyName(jsonClass, name, currentClass, annotatedClass);

          // Everything is ok, so we can add it
          JsonProperty property = new JsonProperty(field, name);
          property.setRequired(required);

          // TODO type adapter
          jsonClass.addProperty(property);
        }
      }

      // Check super class
      TypeMirror superClassMirror = currentClass.getSuperclass();
      if (superClassMirror.getKind() != TypeKind.NONE) {
        // NONE if java.lang.Object has been reached

        if (superClassMirror instanceof DeclaredType
            && ((DeclaredType) superClassMirror).asElement() instanceof TypeElement) {
          currentClass = ((TypeElement) ((DeclaredType) superClassMirror).asElement());
        } else {
          currentClass = null;
        }
      } else {
        currentClass = null;
      }
    } while (currentClass != null);



    // Write the jave code
    JavaFileObject jfo = filer.createSourceFile(jsonClass.getAdapterClassName(), annotatedClass);
    Writer writer = jfo.openWriter();
    JavaWriter jw = new JavaWriter(writer);
    jsonClass.writeJavaClass(jw);
    jw.close();

  }

  /**
   * Throw a exception with an formated string as message
   *
   * @throws Exception
   */
  private void throwException(String msg, Object... params) throws Exception {
    throw new Exception(String.format(msg, params));
  }

  /**
   * Checks if the json property is already mapped
   *
   * @throws Exception
   */
  private void checkPropertyName(JsonClass jsonClass, String propertyName, TypeElement currentClass,
      TypeElement annotatedClass) throws Exception {

    if (jsonClass.containsProperty(propertyName)) {

      throwException("A field with the name %s in %s already exists for annotated class %s. "
              + "Rename this field with @%s or ignore this field with @%s", propertyName,
          currentClass.getSimpleName(), annotatedClass.getSimpleName(),
          Property.class.getSimpleName(), Ignore.class.getSimpleName());
    }
  }

  private void checkPropertyAccessability(VariableElement field, TypeElement currentClass,
      TypeElement annotatedClass) throws Exception {

    Set<Modifier> modifiers = field.getModifiers();

    // Final is not allowed
    if (modifiers.contains(Modifier.FINAL)) {
      throwException("Field with the name %s in %s is declared as FINAL for annotated class %s. "
              + "FINAL fields can not be assigned! Remove final modifier or ignore this field with @%s",
          field.getSimpleName(), currentClass.getSimpleName().toString(),
          annotatedClass.getSimpleName(), Ignore.class.getSimpleName());
    }

    // private fields are not allowed
    if (modifiers.contains(Modifier.PRIVATE)) {
      throwException("Field with the name %s in %s is declared as PRIVATE for annotated class %s. "
              + "Only PUBLIC or DEFAULT (PACKAGE VISIBLITY) is allowed. "
              + "If you don't want to map this field, then you can ignore this field with @%s",
          field.getSimpleName().toString(), currentClass.getSimpleName(),
          annotatedClass.getSimpleName(), Ignore.class.getSimpleName());
    }

    // protected fields are not allowed
    if (modifiers.contains(Modifier.PROTECTED)) {
      throwException(
          "Field with the name %s in %s is declared as PROTECTED for annotated class %s. "
              + "Only PUBLIC or DEFAULT (package visibility) is allowed. "
              + "If you don't want to map this field, then you can ignore this field with @%s",
          field.getSimpleName().toString(), currentClass.getSimpleName(),
          annotatedClass.getSimpleName(), Ignore.class.getSimpleName());
    }

    // TODO transient support?
  }
}
