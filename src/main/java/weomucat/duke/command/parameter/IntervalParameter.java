package weomucat.duke.command.parameter;

import weomucat.duke.date.Interval;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.IntervalParser;

public class IntervalParameter extends Parameter<Interval> {

  public IntervalParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Interval parseLogic(String input) throws InvalidParameterException {
    return new IntervalParser(input).parse();
  }

  @Override
  public String type() {
    return "ISO-8601 Duration";
  }
}
