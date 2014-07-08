package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
/**
 * Annotated every class that can be parsed from json or can be written as json
 * with this annotation
 *
 * @author Hannes Dorfmann
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Json {

    /**
     * Default return value is: true
     * @return true, if you do <b>not</b> want that an exception will be thrown
     * if a json property is detected that is
     * not present in the corresponding java class. Otherwise, false.
     */
    boolean ignoreUnknownJsonProperties() default true;
}
