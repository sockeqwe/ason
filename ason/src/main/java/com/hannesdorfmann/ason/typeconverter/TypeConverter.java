package com.hannesdorfmann.ason.typeconverter;

/**
 * This class is responsible for converting a (non primitive) value
 * from string (extracted from json)
 * to the desired type
 *
 * @author Hannes Dorfmann
 */
public abstract class TypeConverter<T> {

  public abstract String forType();

  public T fromJson(String value) throws Exception {

    if (value == null) {
      return getDefaultFromValue();
    } else {
      return from(value);
    }
  }

  protected abstract T from(String value) throws Exception;

  String toJson(T value) throws Exception {
    if (value == null) {
      return getDefaultToValue();
    } else {
      return to(value);
    }
  }

  public abstract String to(T value);

  protected abstract T getDefaultFromValue();

  /**
   * @return
   */
  protected abstract String getDefaultToValue();
}
