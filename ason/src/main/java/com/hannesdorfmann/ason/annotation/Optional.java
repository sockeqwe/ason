package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * Mark a {@link com.hannesdorfmann.ason.annotation.Property} as optional,
 * if you want to specify that the given property is option
 *
 * @author Hannes Dorfmann
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD }) @Retention(RetentionPolicy.CLASS)
@Documented
public @interface Optional {
}
