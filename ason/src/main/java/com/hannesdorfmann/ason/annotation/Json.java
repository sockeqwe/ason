package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated every class that can be parsed from json or can be written as json
 * with this annotation. You can also specify config for this
 *
 * @author Hannes Dorfmann
 */
@Target(ElementType.TYPE) @Retention(RetentionPolicy.CLASS) @Documented
public @interface Json {

  Config[] value() default { };
}
