package com.hannesdorfmann.ason.processor;

import com.hannesdorfmann.ason.annotation.Json;
import com.hannesdorfmann.ason.processor.generator.TypeAdapterGenerator;
import java.io.PrintWriter;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * This is the default Ason annotation processor
 *
 * @author Hannes Dorfmann
 */
@SupportedAnnotationTypes("com.hannesdorfmann.ason.annotation.Json")
public class AsonProcessor extends AbstractProcessor {

  private Elements elementUtils;
  private Types typeUtils;
  private Filer filer;

  @Override
  public synchronized void init(ProcessingEnvironment env) {
    super.init(env);

    elementUtils = env.getElementUtils();
    typeUtils = env.getTypeUtils();
    filer = env.getFiler();
  }

  @Override
  public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnv) {

    ProcessorMessage.init(processingEnv);

    try {
      PrintWriter out = new PrintWriter("/Users/hannes/annotation.txt");

      TypeAdapterGenerator adapterGenerator =
          new TypeAdapterGenerator(roundEnv, elementUtils, typeUtils, filer);

      for (Element elem : roundEnv.getElementsAnnotatedWith(Json.class)) {

        Json json = elem.getAnnotation(Json.class);
        String message = "annotation found in " + elem.getSimpleName();
        ProcessorMessage.note(elem, message);

        adapterGenerator.generateCode(elem);

        // TODO remove out
        out.write(message + "\n");
      } out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true; // no further processing of this annotation type
  }
}
