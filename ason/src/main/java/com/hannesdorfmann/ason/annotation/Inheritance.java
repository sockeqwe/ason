package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated property is responsible to determine the subclass.
 * An example is shown in the docs of {@link Subclass}
 *
 * @author Hannes Dorfmann
 * @see Subclass
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD }) @Retention(RetentionPolicy.CLASS)
@Documented
public @interface Inheritance {

  Subclass[] value();
}
