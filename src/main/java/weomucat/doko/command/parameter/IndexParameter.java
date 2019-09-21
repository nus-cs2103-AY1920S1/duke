package weomucat.doko.command.parameter;

import weomucat.doko.exception.InvalidParameterException;
import weomucat.doko.parser.IntegerParser;

public class IndexParameter extends Parameter<Integer> {

  public IndexParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Integer parseLogic(String input) throws InvalidParameterException {
    return new IntegerParser(input).parse() - 1;
  }

  @Override
  public String type() {
    return "Index";
  }
}
