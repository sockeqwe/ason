package com.hannesdorfmann.ason.converter;

/**
 * This class is responsible for converting a value from string (extracted from json)
 * to the desired type.
 * <p>
 * <b>This class MUST provide an empty default constructor (parameterless constructor)</b>
 * </p>
 *
 * @author Hannes Dorfmann
 */
// TODO make a interface
public abstract class AbstractConverter<T> implements Converter<T> {

  /**
   * The name of the class it should convert.
   * For example: If you want convert a string to java.util.Date then this method should return
   * java.util.Date.class
   */
  public abstract Class<T> getType();

  /**
   * Returns the canonical name of the type this converter is for by calling {@link #getType()}
   */
  @Override
  public String getTypeCanonicalName() {
    return getType().getCanonicalName();
  }

  /**
   * Converts the value (String extracted from json) to the desired java object.
   *
   * @throws Exception
   */
  @Override
  public T fromJson(String value) throws Exception {

    if (value == null) {
      return getDefaultFromValue();
    } else {
      return from(value);
    }
  }

  /**
   * Converts the value (String extracted from json) to the desired java object.
   *
   * @throws Exception
   */
  protected abstract T from(String value) throws Exception;

  /**
   * @throws Exception
   */
  @Override
  public String toJson(T value) throws Exception {
    if (value == null) {
      return getDefaultToValue();
    } else {
      return to(value);
    }
  }

  /**
   * Converts the real java object to a string that will representing the java objects value in
   * json
   */
  public abstract String to(T value);

  /**
   * The default value for {@link #from(String)} in case that the pased string json value is null
   */
  protected abstract T getDefaultFromValue();

  /**
   * The default value for {@link #to(Object)} (in case that value is null).
   */
  protected abstract String getDefaultToValue();
}
