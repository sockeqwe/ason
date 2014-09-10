package com.hannesdorfmann.ason.processor.generator;

import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;

/**
 * @author Hannes Dorfmann
 */
public class JsonProperty {

  protected String jsonPropertyName;
  protected boolean required;
  protected String converterName;
  protected VariableElement field;

  protected JsonProperty(VariableElement field, String jsonPropertyName, boolean required,
      String converterName) {
    this.field = field;
    this.jsonPropertyName = jsonPropertyName;
    this.required = required;
    this.converterName = converterName;
  }

  public JsonProperty(VariableElement field, String jsonPropertyName) {
    this.field = field;
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

  public VariableElement getField() {
    return field;
  }

  public boolean isPrimitiveInt() {
    return field.asType().getKind() == TypeKind.INT;
  }

  public boolean isPrimitiveLong(){
    return field.asType().getKind() == TypeKind.LONG;
  }

  public boolean isPrimitiveFloat(){
    return field.asType().getKind() == TypeKind.FLOAT;
  }

  public boolean isPrimitiveDouble(){
    return field.asType().getKind() == TypeKind.DOUBLE;
  }


  public boolean isPrimitiveBoolean(){
    return field.asType().getKind() == TypeKind.BOOLEAN;
  }

  public boolean isPrimitiveByte(){
    return field.asType().getKind() == TypeKind.BYTE;
  }


  public boolean isPrimitiveChar(){
    return field.asType().getKind() == TypeKind.CHAR;
  }

  /**
   * Get the name of the type of this field
   *
   * @return
   */
  public String getType(){
    return field.asType().toString();
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
