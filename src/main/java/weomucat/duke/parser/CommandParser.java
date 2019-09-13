package weomucat.duke.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * A CommandParser is responsible for deciphering user input into:
 * - Command
 * - Body (i.e default parameter)
 * - Parameters
 */
public class CommandParser {

  private String command;
  private String body;
  private String[] tokens;

  /**
   * Default constructor.
   *
   * @param input User input to decipher.
   */
  public CommandParser(String input) {
    assert input != null;

    // Split input by whitespace.
    this.tokens = input.trim().split(" ");

    LinkedList<String> tokens = new LinkedList<>(Arrays.asList(this.tokens));
    this.command = tokens.pollFirst();
    this.body = String.join(" ", tokens).trim();
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
   * Get the body from user input.
   * If Command expects parameters, run this after parseParameters.
   *
   * @return Deciphered body
   */
  public String getBody() {
    return body;
  }

  /**
   * Parse a list of parameters which a Command expects.
   * Given parameters are trimmed.
   * Empty parameters are returned as an empty string.
   *
   * @param parameters The list of parameters
   * @return A HashMap of (parameter name, parameter value)
   */
  public HashMap<String, String> parseParameters(String... parameters) {
    assert parameters != null;

    LinkedList<String> tokens = new LinkedList<>(Arrays.asList(this.tokens));

    // Remove command from tokens.
    tokens.pollFirst();

    // Put parameters into a HashSet for O(1) lookup.
    HashSet<String> lookup = new HashSet<>(Arrays.asList(parameters));
    HashMap<String, String> result = new HashMap<>();

    LinkedList<String> parameter = new LinkedList<>();

    while (!tokens.isEmpty()) {
      String token = tokens.pollLast();

      // If token is a parameter, put parameter in result.
      if (lookup.contains(token)) {
        result.put(token, String.join(" ", parameter).trim());

        // Clear for next parameter.
        parameter.clear();
      } else {
        // Append token to the parameter.
        parameter.addFirst(token);
      }
    }

    // Last parameter is body.
    this.body = String.join(" ", parameter).trim();
    return result;
  }
}
