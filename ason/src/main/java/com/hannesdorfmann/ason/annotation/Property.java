package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * This annotation can be used to map a json property from json to the corresponding property of the
 * {@link Json} annotated class.
 * <p/>
 * Can be applied to any field or method. If you want to apply that to a method your method must
 * provide a single parameter of type string
 *
 * @author Hannes Dorfmann
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Property {

    /**
     * The name of the json property. If not specified, the field name will be used.
     *
     * @return
     */
    String value() default "";

}