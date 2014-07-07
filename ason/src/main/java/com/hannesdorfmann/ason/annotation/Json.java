package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.*;

/**
 * Annotated every class that can be parsed with this annotation
 * @author Hannes Dorfmann
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Json {
}
