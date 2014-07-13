package com.hannesdorfmann.ason;

/**
 * @author Hannes Dorfmann
 * @since 1.0
 */
public class Ason {

  /**
   * The suffix for the generated ParserWriter
   */
  public static final String PARSER_WRITER_SUFFIX = "AsonParserWriter";

  private Config config = new Config();

  /**
   * Get the default config
   * @return
   * @see #setConfig(Config)
   */
  public Config getConfig() {
    return config;
  }

  /**
   * Sets the default configuration that will be used for all
   * {@link com.hannesdorfmann.ason.annotation.Json @Json} annotated classes. However each @Json
   * annotaged class can override the default config by using the
   * {@link com.hannesdorfmann.ason.annotation.Config @Config} annotation in combination with the
   * @Json annotation.
   *
   * @param config
   */
  public void setConfig(Config config) {
    this.config = config;
  }
}
