package weomucat.duke.command;

import java.time.Duration;
import java.util.HashMap;
import weomucat.duke.date.Date;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DurationParser;
import weomucat.duke.task.DeadlineTask;
import weomucat.duke.task.Task;

public abstract class DeadlineCommand implements Command {

  private String description;
  private Date by;
  private Duration every;

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_BY, PARAMETER_EVERY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {
    this.description = body;

    String by = parameters.get(PARAMETER_BY);
    if (by.equals("")) {
      throw new InvalidParameterException("The due date of a deadline cannot be empty.");
    }
    this.by = Date.parse(by);

    String every = parameters.get(PARAMETER_EVERY);
    if (!every.equals("")) {
      this.every = new DurationParser(every).parse();
    }
  }

  @Override
  public void run() throws DukeException {
    DeadlineTask task = new DeadlineTask(this.description, this.by)
        .setEvery(every);
    updateListeners(task);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param task deadline task this Command produces
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void updateListeners(Task task) throws DukeException;
}
