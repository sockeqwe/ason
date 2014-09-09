package com.hannesdorfmann.ason.processor.generator;

import com.hannesdorfmann.ason.Config;
import com.hannesdorfmann.ason.annotation.Ignore;
import com.hannesdorfmann.ason.annotation.Json;
import com.hannesdorfmann.ason.annotation.Property;
import com.hannesdorfmann.ason.processor.ProcessorMessage;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

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

    // check fields
    Set<JsonAssignment> properties = new HashSet<JsonAssignment>();

    TypeElement currentClass = annotatedClass;

    do {

      for (Element enclosed : annotatedClass.getEnclosedElements()) {
        // Check fields
        if (enclosed instanceof VariableElement && enclosed.getKind() == ElementKind.FIELD) {
          VariableElement field = (VariableElement) enclosed;
          if (field.getAnnotation(Ignore.class) != null) {
            continue;
          }

          if (properties.contains(field.getSimpleName())) {
            throwException("A field with the name %s in %s already exists for annotated class %s. "
                    + "Rename this field with @%s or ignore this field with @%s",
                field.getSimpleName(), currentClass.getSimpleName(), annotatedClass.getSimpleName(),
                Property.class.getSimpleName(), Ignore.class.getSimpleName());
          }
        }
      }
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
  }

  /**
   * Throw a exception with an formated string as message
   *
   * @throws Exception
   */
  private void throwException(String msg, Object... params) throws Exception {
    throw new Exception(String.format(msg, params));
  }
}
