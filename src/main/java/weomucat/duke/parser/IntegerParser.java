package weomucat.duke.parser;

import weomucat.duke.exception.InvalidParameterException;

public class IntegerParser implements Parser<Integer> {

  private String input;

  public IntegerParser(String input) {
    this.input = input.trim();
  }

  @Override
  public Integer parse() throws InvalidParameterException {
    try {
      return Integer.parseInt(this.input);
    } catch (NumberFormatException e) {
      throw new InvalidParameterException(
          String.format("'%s' is not a valid integer.", this.input));
    }
  }
}
