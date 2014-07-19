package com.hannesdorfmann.ason.processor.generator;

import com.squareup.javawriter.JavaWriter;
import java.io.IOException;

/**
 * maps json value to java object and vice versa by making getter and setter calls
 *
 * @author Hannes Dorfmann
 */
public class MethodJsonAssignment extends JsonAssignment{


  protected String javaSetterMethod;
  protected String javaGetterMethod;

  public MethodJsonAssignment(String jsonPropertyName) {
    super(jsonPropertyName);
  }

  @Override public void readJsonToObject(JavaWriter writer) throws IOException{
    writer.emitStatement("o"); // TODO continue here
  }

  @Override public void writeObjectToJson(JavaWriter writer) throws IOException{

  }
}
