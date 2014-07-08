package com.hannesdorfmann.ason.parserwriter;

import com.hannesdorfmann.ason.exception.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Hannes Dorfmann
 */
interface ParserWriter<T> {

    /**
     * Reads json from the given InputStream and parses a concrete object out of it.
     *
     * @param json
     * @return The parsed object
     */
    T parse(InputStream json, ParseConfig config) throws ParseException, IOException;

    /**
     * Writes the given value as json to the given outputstream
     *
     * @param out
     * @param value
     * @throws IOException
     */
    void write(OutputStream out, T value) throws IOException;

}
