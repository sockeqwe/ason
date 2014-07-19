package com.hannesdorfmann.ason.processor.generator;

import com.hannesdorfmann.ason.annotation.Json;
import com.hannesdorfmann.ason.processor.ProcessorMessage;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
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

  public void generateCode(Element annotatedClass) {

    // Check that the annotated element is a class
    if (!(annotatedClass instanceof TypeElement)
        || annotatedClass.getKind() != ElementKind.CLASS) {

      ProcessorMessage.error(annotatedClass, "The @%s Annotation can only be applied on classes",
          Json.class.getSimpleName());
      return;
    }
  }
}
