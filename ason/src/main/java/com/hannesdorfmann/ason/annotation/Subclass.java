package com.hannesdorfmann.ason.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used within a {@link Inheritance} annotation to specify which subclass should
 * be chosen by the given name.
 *
 * <p>Example: <br />
 * {@code
 *
 * @Json class Animal {
 *
 * @Property String name;
 *
 * @Inheritance({
 * @Subclass(value = "Dog", Dog.class),
 * @Subclass(value = "Cat", Cat.class)
 * })
 * @Property String type;
 *
 * }
 *
 * }
 *
 * </p>
 *
 * The example shows, that the json property "type" is responsible to decide which sublass should
 * be
 * instantiated. If  json property "type" equals "Dog" then the an instance of Dog class will be
 * instantiated.
 *
 *
 * @author Hannes Dorfmann
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD }) @Retention(RetentionPolicy.CLASS)
@Documented
public @interface Subclass {
  String value();

  Class<?> Class();
}
