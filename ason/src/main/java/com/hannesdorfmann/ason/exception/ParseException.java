package com.hannesdorfmann.ason.exception;

/**
 * This Exception will be thrown if an error has occurred while parsing json
 *
 * @author Hannes Dorfmann
 */
public class ParseException extends Exception {

    public ParseException(String msg) {
        super(msg);
    }
}
