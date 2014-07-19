package com.hannesdorfmann.ason.processor.generator;

import com.squareup.javawriter.JavaWriter;
import java.io.IOException;

/**
 * @author Hannes Dorfmann
 */
public abstract class JsonAssignment {

  protected String jsonPropertyName;


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


}
