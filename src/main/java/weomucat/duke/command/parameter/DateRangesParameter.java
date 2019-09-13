package weomucat.duke.command.parameter;

import java.util.ArrayList;
import java.util.Collection;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;

public class DateRangesParameter extends Parameter<Collection<DateRange>> {

  private static final String DATE_RANGE_DELIMITER = "\\|";

  public DateRangesParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Collection<DateRange> parseLogic(String userInput) throws InvalidParameterException {
    Collection<DateRange> result = new ArrayList<>();

    String[] ranges = userInput.split(DATE_RANGE_DELIMITER);
    for (String range : ranges) {
      result.add(DateRange.parse(range));
    }

    /*
    if (result.size() == 0) {
      throw new InvalidParameterException("The date range(s) cannot be empty.");
    }
     */
    return result;
  }
}
