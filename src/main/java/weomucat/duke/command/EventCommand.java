package weomucat.duke.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.task.EventTask;
import weomucat.duke.task.Task;

public abstract class EventCommand implements Command {

  private static final String DATE_RANGE_DELIMITER = "\\|";

  private String description;
  private Collection<DateRange> at;

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_AT};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {
    this.description = body;

    String at = parameters.get(PARAMETER_AT);
    if (at.equals("")) {
      throw new InvalidParameterException("The date range of an event cannot be empty.");
    }

    this.at = new ArrayList<>();

    String[] ranges = at.split(DATE_RANGE_DELIMITER);
    for (String range : ranges) {
      this.at.add(DateRange.parse(range));
    }
  }

  @Override
  public void run() throws DukeException {
    EventTask task = new EventTask(this.description, this.at);
    updateListeners(task);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param task event task this Command produces
   * @throws DukeException If there is anything wrong with processing.
   */
  public abstract void updateListeners(Task task) throws DukeException;
}
