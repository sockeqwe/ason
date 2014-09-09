package com.hannesdorfmann.ason;

/**
 * A simple class to configurate the Ason processor.
 * You can also set each of the config properties for each Json annotated class by using the
 * {@link com.hannesdorfmann.ason.annotation.Config}
 * annotation.
 *
 * @author Hannes Dorfmann
 */
public class Config {

  /**
   * The default value for {@link #ignoreUnknownJsonProperty}
   */
  public static final boolean DEFAULT_ignoreUnknownJsonProperty = true;
  /**
   * If you do <b>not</b> want that an exception will be thrown if a json
   * property is detected that is
   * not present in the corresponding java class.
   * otherwise false.
   * <p/>
   * <p>This property can also be set for each class by annotation
   * {@link com.hannesdorfmann.ason.annotation.Config#ignoreUnknownProperties()}}
   * </p>
   */
  public boolean ignoreUnknownJsonProperty = DEFAULT_ignoreUnknownJsonProperty;

  //
  // Default options
  //
  /**
   * The default value for {@link #writeNullValues}
   */
  public static final boolean DEFAULT_writeNullValues = false;
  /**
   * Should null values be written while converting java objects to json?
   */
  public boolean writeNullValues = DEFAULT_writeNullValues;

  /**
   * Creates a real config object out of the config annotation
   */
  public static Config fromAnnotation(com.hannesdorfmann.ason.annotation.Config annotation) {

    Config c = new Config();
    c.ignoreUnknownJsonProperty = annotation.ignoreUnknownProperties();
    c.writeNullValues = annotation.writeNullValues();
    return c;
  }
}
