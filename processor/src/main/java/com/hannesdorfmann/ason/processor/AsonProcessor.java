package com.hannesdorfmann.ason.processor;

import com.hannesdorfmann.ason.annotation.Json;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

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
  public Set<String> getSupportedAnnotationTypes() {
    Set<String> supportTypes = new LinkedHashSet<String>();
    supportTypes.add(Json.class.getCanonicalName());

    return supportTypes;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  @Override
  public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnv) {

    try {
      PrintWriter out = new PrintWriter("/Users/hannes/annotation.txt");

      for (Element elem : roundEnv.getElementsAnnotatedWith(Json.class)) {

        Json json = elem.getAnnotation(Json.class);
        String message = "annotation found in " + elem.getSimpleName();
        processingEnv.getMessager().printMessage(Kind.WARNING, message);

        out.write(message + "\n");
      }
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return true; // no further processing of this annotation type
  }

  private String getPackageName(TypeElement type) {
    return elementUtils.getPackageOf(type).getQualifiedName().toString();
  }

  private void error(Element element, String message, Object... args) {
    if (args.length > 0) {
      message = String.format(message, args);
    }
    processingEnv.getMessager().printMessage(Kind.ERROR, message, element);
  }
}
