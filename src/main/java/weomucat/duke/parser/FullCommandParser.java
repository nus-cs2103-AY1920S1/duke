package weomucat.duke.parser;

import weomucat.duke.command.Command;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class FullCommandParser implements Parser<Command<?>> {

  // Split and join with whitespace.
  static final String COMMAND_DELIMITER = " ";

  private String input;

  public FullCommandParser(String input) {
    this.input = input.trim();
  }

  @Override
  public Command<?> parse() throws DukeException {
    String[] tokens = this.input.split(COMMAND_DELIMITER, 2);

    String commandName = tokens[0];
    String parameters = tokens.length > 1 ? tokens[1] : "";

    // Resolve command from user input.
    Command<?> command = new CommandKeywordParser(commandName).parse();

    // Get the parameter options of the command.
    ParameterOptions options = command.getParameterOptions();

    if (options != null) {
      // Parse and set the parameters.
      new CommandParametersParser(parameters, options).parse();
    }

    return command;
  }
}
