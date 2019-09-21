package weomucat.doko.command.parameter;

import weomucat.doko.command.Command;
import weomucat.doko.exception.DokoException;
import weomucat.doko.parser.CommandKeywordParser;

public class CommandNameParameter extends Parameter<Command<?>> {

  public CommandNameParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Command<?> parseLogic(String input) throws DokoException {
    if (input.equals("")) {
      return null;
    } else {
      return new CommandKeywordParser(input).parse();
    }
  }

  @Override
  public String type() {
    return "Command Name";
  }
}
