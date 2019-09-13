package weomucat.duke.command;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.date.DateRange;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DurationParser;
import weomucat.duke.task.EventTask;

public class EventCommand extends Command<AddTaskCommandListener> {

  private static final String PARAMETER_AT = "/at";
  private static final String DATE_RANGE_DELIMITER = "\\|";
  private static final String PARAMETER_EVERY = "/every";

  private EventTask task;

  public EventCommand(Collection<AddTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_AT, PARAMETER_EVERY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {
    if (body.equals("")) {
      throw new InvalidParameterException("The description of an event cannot be empty.");
    }

    String parameterAt = parameters.get(PARAMETER_AT);
    if (parameterAt == null || parameterAt.equals("")) {
      throw new InvalidParameterException("The date range of an event cannot be empty.");
    }

    Collection<DateRange> at = new ArrayList<>();

    String[] ranges = parameterAt.split(DATE_RANGE_DELIMITER);
    for (String range : ranges) {
      at.add(DateRange.parse(range));
    }

    this.task = EventTask.create(body, at);

    String parameterEvery = parameters.get(PARAMETER_EVERY);
    if (parameterEvery != null) {
      Duration every = new DurationParser(parameterEvery).parse();
      this.task.setRecurrence(every);
    }
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.addTaskCommandUpdate(task));
  }
}
