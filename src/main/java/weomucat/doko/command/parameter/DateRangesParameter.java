package weomucat.doko.command.parameter;

import java.util.Collection;
import weomucat.doko.date.DateRange;
import weomucat.doko.exception.InvalidParameterException;
import weomucat.doko.parser.DateRangesParser;

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
