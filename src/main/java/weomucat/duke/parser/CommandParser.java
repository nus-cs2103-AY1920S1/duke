package weomucat.duke.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import weomucat.duke.command.parameter.Parameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.InvalidParameterException;

/**
 * A CommandParser is responsible for deciphering user input into:
 * - Command
 * - Body (i.e default parameter)
 * - Parameters
 */
public class CommandParser {

  // Split and join with whitespace.
  private static final String DELIMITER = " ";

  private String command;
  private String[] tokens;

  /**
   * Default constructor.
   *
   * @param input User input to decipher.
   */
  public CommandParser(String input) {
    assert input != null;

    this.tokens = input.trim().split(DELIMITER);

    LinkedList<String> tokens = new LinkedList<>(Arrays.asList(this.tokens));
    this.command = tokens.pollFirst();
  }

  /**
   * Get the command from user input.
   *
   * @return Deciphered command
   */
  public String getCommand() {
    return command;
  }

  /**
   * Parse userInput from a list of parameter options.
   * Each parameter option will parse parameterized user input.
   *
   * @param parameterOptions list of parameter options
   */
  public void parse(ParameterOptions parameterOptions) throws InvalidParameterException {
    if (parameterOptions == null) {
      return;
    }

    LinkedList<String> tokens = new LinkedList<>(Arrays.asList(this.tokens));

    // Remove command from tokens.
    tokens.pollFirst();

    // Put parameter strings into a HashSet for O(1) lookup.
    HashSet<String> lookup = new HashSet<>(parameterOptions.keySet());

    // Split user input into individual parameterized strings and put into result.
    HashMap<String, String> result = new HashMap<>();
    LinkedList<String> parameter = new LinkedList<>();
    while (!tokens.isEmpty()) {
      String token = tokens.pollLast();

      // If token is a parameter, put parameter in result.
      if (lookup.contains(token)) {
        result.put(token, String.join(DELIMITER, parameter).trim());

        // Clear for next parameter.
        parameter.clear();
      } else {
        // Append token to the parameter.
        parameter.addFirst(token);
      }
    }

    Parameter defaultParameter = parameterOptions.getDefaultParameter();
    if (defaultParameter != null) {
      // Last parameterized string is body.
      defaultParameter.parse(String.join(DELIMITER, parameter).trim());
    }

    // For each parameter option, parse parameterized strings.
    for (Map.Entry<String, Parameter> p : parameterOptions.entrySet()) {
      p.getValue().parse(result.get(p.getKey()));
    }
  }
}
