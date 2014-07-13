package com.hannesdorfmann.ason.annotation;

/**
 * This annotation can be uesd with {@link Json @Json} to specify a different configuration for
 * the @Json annotated class. That will override the default Ason configuration set by
 * {@link com.hannesdorfmann.ason.Ason#setConfig(com.hannesdorfmann.ason.Config)}
 * @author Hannes Dorfmann
 * @since 1.0
 */
public @interface Config {

  /**
   * @see com.hannesdorfmann.ason.Config#ignoreUnknownJsonProperty
   * @return
   * @since 1.0
   */
  boolean ignoreUnknownProperties() default
      com.hannesdorfmann.ason.Config.DEFAULT_ignoreUnknownJsonProperty;

  /**
   * @see com.hannesdorfmann.ason.Config#writeNullValues
   * @return
   * @since 1.0
   */
  boolean writeNullValues() default
      com.hannesdorfmann.ason.Config.DEFAULT_writeNullValues;
}
