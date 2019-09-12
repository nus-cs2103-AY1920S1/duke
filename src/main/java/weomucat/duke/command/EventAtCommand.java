package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.NumberParser;

public abstract class EventAtCommand implements Command {

  private int taskIndex;
  private int atIndex;

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_AT};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {
    if (body.equals("")) {
      throw new InvalidParameterException("The task index cannot be empty!");
    }
    this.taskIndex = new NumberParser(body).parse("The task index is not a valid number.") - 1;

    String at = parameters.get(PARAMETER_AT);
    if (at.equals("")) {
      throw new InvalidParameterException("The schedule index cannot be empty!");
    }
    this.atIndex = new NumberParser(at).parse("The schedule index is not a valid number.") - 1;
  }

  @Override
  public void run() throws DukeException {
    updateListeners(taskIndex, atIndex);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param taskIndex the index of the event in the task list
   * @param atIndex   the index of the schedule in the tentative schedule list
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void updateListeners(int taskIndex, int atIndex) throws DukeException;
}
