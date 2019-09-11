package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;

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

    try {
      // Get index of task
      this.taskIndex = Integer.parseInt(body) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidParameterException("The task index is not a valid number.");
    }

    // Get index of at
    String at = parameters.get(PARAMETER_AT);

    if (at.equals("")) {
      throw new InvalidParameterException("The schedule index cannot be empty!");
    }

    try {
      // Get index of task
      this.atIndex = Integer.parseInt(body) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidParameterException("The schedule index is not a valid number.");
    }
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
