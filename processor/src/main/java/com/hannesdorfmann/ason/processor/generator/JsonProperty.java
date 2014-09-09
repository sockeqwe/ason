package com.hannesdorfmann.ason.processor.generator;

/**
 * @author Hannes Dorfmann
 */
public class JsonProperty {

  protected String jsonPropertyName;
  protected boolean required;
  protected String converterName;

  protected JsonProperty(String jsonPropertyName,  boolean required,
      String converterName) {
    this.jsonPropertyName = jsonPropertyName;
    this.required = required;
    this.converterName = converterName;
  }

  public JsonProperty(String jsonPropertyName) {
    this.jsonPropertyName = jsonPropertyName;
  }


  public boolean isRequired() {
    return required;
  }

  public JsonProperty setRequired(boolean required) {
    this.required = required;
  }

  public String getConverterName() {
    return converterName;
  }

  public JsonProperty setConverterName(String converterName) {
    this.converterName = converterName;
  }

  /**
   * The Json property name
   */
  public String getJsonPropertyName() {
    return jsonPropertyName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    JsonProperty that = (JsonProperty) o;

    if (jsonPropertyName != null ? !jsonPropertyName.equals(that.jsonPropertyName)
        : that.jsonPropertyName != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return jsonPropertyName != null ? jsonPropertyName.hashCode() : 0;
  }
}
