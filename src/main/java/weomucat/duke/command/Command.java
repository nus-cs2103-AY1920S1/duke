package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.parser.CommandParser;

/**
 * A Command takes in certain parameters, then performs a set of instructions.
 */
public interface Command {

  String PARAMETER_AT = "/at";
  String PARAMETER_BY = "/by";
  String PARAMETER_EVERY = "/every";

  /**
   * The parameters that this Command takes in.
   */
  String[] getParameterOptions();

  /**
   * Sets the parameter of this Command to those from user input.
   *
   * @param body       the body from user input. See {@link CommandParser}
   * @param parameters the parameters from user input
   * @throws DukeException If any parameter given is wrong.
   */
  void setParameters(String body, HashMap<String, String> parameters) throws DukeException;

  /**
   * Perform some instructions.
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  void run() throws DukeException;
}
