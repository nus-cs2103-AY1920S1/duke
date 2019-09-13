package weomucat.duke.command.parameter;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import weomucat.duke.exception.InvalidParameterException;

public class DurationParameter extends Parameter<Duration> {

  public DurationParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Duration parseLogic(String userInput) throws InvalidParameterException {
    Duration result;
    try {
      result = Duration.parse(userInput);
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException("I do not understand the duration."
          + "Please enter in ISO-8601 format.");
    }

    if (result.isNegative()) {
      throw new InvalidParameterException("The duration must be positive!");
    }
    return result;
  }
}
