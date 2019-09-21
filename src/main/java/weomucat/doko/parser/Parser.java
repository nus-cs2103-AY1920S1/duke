package weomucat.doko.parser;

import weomucat.doko.exception.DokoException;

/**
 * Parses user input into T.
 */
public interface Parser<T> {

  /**
   * Parses user input.
   *
   * @return result of parsing
   * @throws DokoException if unable to parse the given user input
   */
  T parse() throws DokoException;
}
