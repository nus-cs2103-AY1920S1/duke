package weomucat.duke.parser;

import static weomucat.duke.parser.FullCommandParser.COMMAND_DELIMITER;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import weomucat.duke.command.parameter.Parameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

/**
 * A CommandParser is responsible for deciphering user input into:
 * - Command
 * - Body (i.e default parameter)
 * - Parameters
 */
public class CommandParametersParser implements Parser<Void> {

  private String input;
  private ParameterOptions parameterOptions;

  /**
   * Parse userInput from a list of parameter options.
   * Each parameter option will parse parameterized user input.
   *
   * @param input            user input to parse
   * @param parameterOptions list of parameter options
   */
  public CommandParametersParser(String input, ParameterOptions parameterOptions) {
    this.input = input.trim();
    this.parameterOptions = parameterOptions;
  }

  @Override
  public Void parse() throws DukeException {
    String[] tokens = input.split(COMMAND_DELIMITER);
    LinkedList<String> tokenList = new LinkedList<>(Arrays.asList(tokens));

    // Put parameter strings into a HashSet for O(1) lookup.
    HashSet<String> lookup = new HashSet<>(this.parameterOptions.keySet());

    // Split user input into individual parameterized strings and put into result.
    HashMap<String, String> result = new HashMap<>();
    LinkedList<String> parameter = new LinkedList<>();
    while (!tokenList.isEmpty()) {
      String token = tokenList.removeLast();

      // If token is a parameter, put parameter in result.
      if (lookup.contains(token)) {
        result.put(token, String.join(COMMAND_DELIMITER, parameter).trim());

        // Clear for next parameter.
        parameter.clear();
      } else {
        // Append token to the parameter.
        parameter.addFirst(token);
      }
    }

    Parameter defaultParameter = this.parameterOptions.getDefaultParameter();
    if (defaultParameter != null) {
      // Last parameterized string is body.
      defaultParameter.parse(String.join(COMMAND_DELIMITER, parameter).trim());
    }

    // For each parameter option, parse parameterized strings.
    for (Map.Entry<String, Parameter> p : this.parameterOptions.entrySet()) {
      p.getValue().parse(result.get(p.getKey()));
    }

    return null;
  }
}
