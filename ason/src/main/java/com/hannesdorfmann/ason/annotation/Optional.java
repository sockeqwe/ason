package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.*;

/**
 * Mark a {@link com.hannesdorfmann.ason.annotation.Property} as optional,
 * if you want to specify that the given property is option
 * @author Hannes Dorfmann
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Optional {
}
