package weomucat.duke.parser;

import weomucat.duke.exception.DukeException;

/**
 * Parses user input into T.
 */
public interface Parser<T> {

  /**
   * Parses user input.
   *
   * @return result of parsing
   * @throws DukeException if unable to parse the given user input
   */
  T parse() throws DukeException;
}
