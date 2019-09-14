package weomucat.duke.parser;

import weomucat.duke.exception.InvalidParameterException;

public class IntegerParser {

  private String input;

  public IntegerParser(String input) {
    this.input = input.trim();
  }

  /**
   * Parses user input into an integer.
   *
   * @return an integer
   * @throws InvalidParameterException if the input is not an integer
   */
  public Integer parse() throws InvalidParameterException {
    try {
      return Integer.parseInt(this.input);
    } catch (NumberFormatException e) {
      throw new InvalidParameterException(
          String.format("'%s' is not a valid integer.", this.input));
    }
  }
}
