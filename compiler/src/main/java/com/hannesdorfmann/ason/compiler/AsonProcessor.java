package com.hannesdorfmann.ason.compiler;

import com.hannesdorfmann.ason.annotation.Json;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import java.util.Set;

/**
 * This is the default Ason annotation processor
 *
 * @author Hannes Dorfmann
 */
@SupportedAnnotationTypes("com.hannesdorfmann.ason.annotation.Json")
public class AsonProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> typeElements, RoundEnvironment roundEnv) {

        for (Element elem : roundEnv.getElementsAnnotatedWith(Json.class)) {

            Json json = elem.getAnnotation(Json.class);
            String message = "annotation found in " + elem.getSimpleName();
            processingEnv.getMessager().printMessage(Kind.WARNING, message);

        }
        return true; // no further processing of this annotation type
    }
}
