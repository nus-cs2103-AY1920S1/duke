package weomucat.duke.command.parameter;

import weomucat.duke.exception.InvalidParameterException;

public class IndexParameter extends Parameter<Integer> {

  public IndexParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Integer parseLogic(String userInput) throws InvalidParameterException {
    try {
      return Integer.parseInt(userInput) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidParameterException(
          String.format("The %s is not a valid number.", getDescription()));
    }
  }
}
