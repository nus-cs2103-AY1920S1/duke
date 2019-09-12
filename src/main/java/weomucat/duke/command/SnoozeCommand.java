package weomucat.duke.command;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;

public abstract class SnoozeCommand implements Command {

  private int taskIndex;
  private Duration duration;

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_BY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {

    if (body.equals("")) {
      throw new InvalidParameterException("The task index cannot be empty!");
    }

    try {
      // Get index of task
      this.taskIndex = Integer.parseInt(body) - 1;
    } catch (NumberFormatException e) {
      throw new InvalidParameterException("The task index is not a valid number.");
    }

    String by = parameters.get(PARAMETER_BY);
    if (by.equals("")) {
      throw new InvalidParameterException("The duration cannot be empty!");
    }

    try {
      this.duration = Duration.parse(by);
    } catch (DateTimeParseException e) {
      throw new InvalidParameterException("I do not understand the duration."
          + "Please enter in ISO-8601 format.");
    }
  }

  @Override
  public void run() throws DukeException {
    updateListeners(this.taskIndex, this.duration);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param duration the duration to snooze for
   */
  public abstract void updateListeners(int taskIndex, Duration duration) throws DukeException;
}
