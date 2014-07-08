package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;


/**
 * Mark a field or getter method to be not take into count for reading ro writing json
 *
 * @author Hannes Dorfmann
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Ignore {
}
