package weomucat.duke.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.InvalidParameterException;

public class DateRangesParser implements Parser<Collection<DateRange>> {

  public static final String DATE_RANGES_DELIMITER = "|";

  private String input;

  public DateRangesParser(String input) {
    this.input = input.trim();
  }

  @Override
  public Collection<DateRange> parse() throws InvalidParameterException {
    Collection<DateRange> result = new ArrayList<>();

    String[] ranges = this.input.split(Pattern.quote(DATE_RANGES_DELIMITER));
    for (String range : ranges) {
      result.add(new DateRangeParser(range).parse());
    }

    if (result.size() == 0) {
      throw new InvalidParameterException("The date range(s) cannot be empty.");
    }
    return result;
  }
}
