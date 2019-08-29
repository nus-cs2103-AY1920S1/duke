package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;

public abstract class DeleteCommand implements Command {

  private int index;

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {
    // Get index of task
    try {
      this.index = Integer.parseInt(body) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidParameterException("The index is not a valid number.");
    }
  }

  @Override
  public void run() throws DukeException {
    updateListeners(this.index);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param i index of task to delete
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void updateListeners(int i) throws DukeException;
}
