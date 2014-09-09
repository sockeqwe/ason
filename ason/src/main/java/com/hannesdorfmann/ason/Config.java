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

  /**
   * Is every {@link com.hannesdorfmann.ason.annotation.Property} annoteted property as
   * default required? Required means that an exception will be thrown, if the property is missing
   * is json.
   */
  public boolean jsonPropertyRequired = DEFAULT_jsonPropertyRequired;

  /**
   * Should null values be written while converting java objects to json?
   */
  public boolean writeNullValues = DEFAULT_writeNullValues;


  //
  // Default options
  //

  /**
   * The default value for {@link #ignoreUnknownJsonProperty}
   */
  public static final boolean DEFAULT_ignoreUnknownJsonProperty = true;

  /**
   * The default value for {@link #writeNullValues}
   */
  public static final boolean DEFAULT_writeNullValues = false;

  public static final boolean DEFAULT_jsonPropertyRequired = false;

  /**
   * Creates a real config object out of the config annotation
   * @param annotation
   * @return
   */
  public static Config fromAnnotation(com.hannesdorfmann.ason.annotation.Config annotation){

    Config c = new Config();
    c.ignoreUnknownJsonProperty = annotation.ignoreUnknownProperties();
    c.writeNullValues = annotation.writeNullValues();
    return c;

  }
}
