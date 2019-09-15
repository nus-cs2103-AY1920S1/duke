package weomucat.duke.command.parameter;

import java.util.Collection;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DateRangesParser;

public class DateRangesParameter extends Parameter<Collection<DateRange>> {

  public DateRangesParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Collection<DateRange> parseLogic(String input) throws InvalidParameterException {
    return new DateRangesParser(input).parse();
  }

  @Override
  public String type() {
    return "ISO-8601 Date Range(s)";
  }
}
