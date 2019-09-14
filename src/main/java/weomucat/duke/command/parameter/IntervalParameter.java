package weomucat.duke.command.parameter;

import weomucat.duke.date.Interval;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.IntervalParser;

public class IntervalParameter extends Parameter<Interval> {

  public IntervalParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Interval parseLogic(String userInput) throws InvalidParameterException {
    return new IntervalParser(userInput).parse();
  }
}
