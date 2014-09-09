package com.hannesdorfmann.ason.converter;

/**
 * Unless you have a good reason you should use {@link AbstractConverter} as your base class for
 * your converter
 *
 * @author Hannes Dorfmann
 * @see AbstractConverter
 */
public interface Converter<T> {

  /**
   * Returns the canonical name of the type this converter is for
   */
  public String getTypeCanonicalName();

  /**
   * Converts the value (String extracted from json) to the desired java object.
   *
   * @throws Exception
   */
  public T fromJson(String value) throws Exception;

  /**
   * Converts the real java object to a string that will representing the java objects value in
   * json
   */
  public String toJson(T value) throws Exception;
}
