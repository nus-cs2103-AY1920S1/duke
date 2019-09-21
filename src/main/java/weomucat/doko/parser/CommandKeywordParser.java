package weomucat.doko.parser;

import weomucat.doko.command.Command;
import weomucat.doko.command.UserCommands;
import weomucat.doko.exception.DokoException;

public class CommandKeywordParser implements Parser<Command<?>> {

  private String input;

  public CommandKeywordParser(String input) {
    this.input = input.trim();
  }

  @Override
  public Command<?> parse() throws DokoException {
    return UserCommands.get(this.input);
  }
}
