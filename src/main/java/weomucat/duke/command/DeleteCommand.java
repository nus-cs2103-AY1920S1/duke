package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.NumberParser;

public abstract class DeleteCommand implements Command {

  private int index;

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {
    if (body.equals("")) {
      throw new InvalidParameterException("The index cannot be empty!");
    }
    this.index = new NumberParser(body).parse("The index is not a valid number.") - 1;
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
