package weomucat.doko.command.parameter;

import weomucat.doko.date.Date;
import weomucat.doko.exception.InvalidParameterException;
import weomucat.doko.parser.DateParser;

public class DateParameter extends Parameter<Date> {

  public DateParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Date parseLogic(String input) throws InvalidParameterException {
    return new DateParser(input).parse();
  }

  @Override
  public String type() {
    return "ISO-8601 Date";
  }
}
