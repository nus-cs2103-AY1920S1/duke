package weomucat.duke.command.parameter;

import java.util.Collection;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DateRangesParser;

public class DateRangesParameter extends Parameter<Collection<DateRange>> {

  public DateRangesParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Collection<DateRange> parseLogic(String userInput) throws InvalidParameterException {
    return new DateRangesParser(userInput).parse();
  }
}
