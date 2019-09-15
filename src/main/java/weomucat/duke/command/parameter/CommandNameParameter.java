package weomucat.duke.command.parameter;

import weomucat.duke.command.Command;
import weomucat.duke.exception.DukeException;
import weomucat.duke.parser.CommandKeywordParser;

public class CommandNameParameter extends Parameter<Command<?>> {

  public CommandNameParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  Command<?> parseLogic(String input) throws DukeException {
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
