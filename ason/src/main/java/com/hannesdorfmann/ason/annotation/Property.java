package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.*;

/**
 * This annotation can be used to map a json property from json to the corresponding property of the
 * {@link Json} annotated class.
 *
 * Can be applied to any field or method. If you want to apply that to a method your method must
 * provide a single parameter of type string
 * @author Hannes Dorfmann
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Property {

    /**
     * The name of the json property. If not specified, the field name will be used.
     * @return
     */
    String value() default "";

}
