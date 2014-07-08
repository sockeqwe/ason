package com.hannesdorfmann.ason.parserwriter;

/**
 * @author Hannes Dorfmann
 */
public class ParseConfig {

    /**
     * If you do <b>not</b> want that an exception will be thrown if a json
     * property is detected that is
     * not present in the corresponding java class.
     * otherwise false.
     * <p/>
     * <p>This property can also be set for each class by annotation
     * {@link com.hannesdorfmann.ason.annotation.Json#ignoreUnknownJsonProperties()}
     * </p>
     */
    public static boolean ignoreUnknownJsonProperty = true;

}
