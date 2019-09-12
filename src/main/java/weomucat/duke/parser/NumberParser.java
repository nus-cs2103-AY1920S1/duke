package weomucat.duke.parser;

import weomucat.duke.exception.InvalidParameterException;

/**
 * A NumberParser is able to parse a string into an integer.
 */
public class NumberParser {

  private String number;

  /**
   * Creates a NumberParser.
   *
   * @param number the integer to parse
   */
  public NumberParser(String number) {
    this.number = number;
  }

  /**
   * Parses the given number string into an integer.
   *
   * @param error error message which is thrown if unable to parse
   * @return parsed integer
   * @throws InvalidParameterException if the number string is not a valid integer
   */
  public int parse(String error) throws InvalidParameterException {
    try {
      return Integer.parseInt(this.number);
    } catch (NumberFormatException e) {
      throw new InvalidParameterException(error);
    }
  }
}
