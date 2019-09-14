package weomucat.duke.command.parameter;

import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.IntegerParser;

public class IndexParameter extends Parameter<Integer> {

  public IndexParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  Integer parseLogic(String userInput) throws InvalidParameterException {
    return new IntegerParser(userInput).parse() - 1;
  }
}
