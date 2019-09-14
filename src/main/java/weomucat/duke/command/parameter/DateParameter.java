package weomucat.duke.command.parameter;

import weomucat.duke.date.Date;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DateParser;

public class DateParameter extends Parameter<Date> {

  public DateParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Date parseLogic(String userInput) throws InvalidParameterException {
    return new DateParser(userInput).parse();
  }
}
