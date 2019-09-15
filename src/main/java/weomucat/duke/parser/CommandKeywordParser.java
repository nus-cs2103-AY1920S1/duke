package weomucat.duke.parser;

import weomucat.duke.command.Command;
import weomucat.duke.command.UserCommands;
import weomucat.duke.exception.DukeException;

public class CommandKeywordParser implements Parser<Command<?>> {

  private String input;

  public CommandKeywordParser(String input) {
    this.input = input.trim();
  }

  @Override
  public Command<?> parse() throws DukeException {
    return UserCommands.get(this.input);
  }
}
