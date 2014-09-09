package com.hannesdorfmann.ason.processor.generator;

import com.hannesdorfmann.ason.Config;
import com.squareup.javawriter.JavaWriter;
import java.io.IOException;

/**
 * @author Hannes Dorfmann
 */
public abstract class JsonAssignment {

  protected String jsonPropertyName;
  protected Config config;
  protected boolean required;
  protected String converterName;

  protected JsonAssignment(String jsonPropertyName, Config config, boolean required,
      String converterName) {
    this.jsonPropertyName = jsonPropertyName;
    this.config = config;
    this.required = required;
    this.converterName = converterName;
  }

  public Config getConfig() {
    return config;
  }

  public void setConfig(Config config) {
    this.config = config;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public String getConverterName() {
    return converterName;
  }

  public void setConverterName(String converterName) {
    this.converterName = converterName;
  }


  public JsonAssignment(String jsonPropertyName){
    this.jsonPropertyName = jsonPropertyName;
  }

  /**
   * The Json property name
   * @return
   */
  public String getJsonPropertyName(){
    return jsonPropertyName;
  }

  /**
   * read Json as input and store it to the java object
   * @param writer
   */
  public abstract void readJsonToObject(JavaWriter writer) throws IOException;

  /**
   * Write the java object value to Json
   * @param writer
   */
  public abstract void writeObjectToJson(JavaWriter writer) throws IOException;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    JsonAssignment that = (JsonAssignment) o;

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
