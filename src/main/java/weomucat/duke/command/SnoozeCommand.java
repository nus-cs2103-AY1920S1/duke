package weomucat.duke.command;

import java.time.Duration;
import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DurationParser;
import weomucat.duke.parser.NumberParser;

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
    this.taskIndex = new NumberParser(body).parse("The task index is not a valid number.") - 1;

    String by = parameters.get(PARAMETER_BY);
    if (by.equals("")) {
      throw new InvalidParameterException("The duration cannot be empty!");
    }
    this.duration = new DurationParser(by).parse();
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
