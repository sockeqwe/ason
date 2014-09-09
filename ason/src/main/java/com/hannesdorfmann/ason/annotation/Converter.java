package com.hannesdorfmann.ason.annotation;

import com.hannesdorfmann.ason.converter.AbstractConverter;

/**
 * With this annotation you can specify the converter that should be used for parsing a json
 * property to a java object property and vice versa
 *
 * @author Hannes Dorfmann
 */
public @interface Converter {

  Class<? extends AbstractConverter> value();
}
