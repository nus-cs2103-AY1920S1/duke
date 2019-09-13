package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.DateRangesParameter;
import weomucat.duke.command.parameter.DurationParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.EventTask;

public class EventCommand extends Command<AddTaskCommandListener> {

  private static final String PARAMETER_AT = "/at";
  private static final String PARAMETER_EVERY = "/every";

  private StringParameter description;
  private DateRangesParameter dateRanges;
  private DurationParameter recurrence;

  public EventCommand(Collection<AddTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter("Event Description", true);
    this.dateRanges = new DateRangesParameter("Event Date Range(s)", true);
    this.recurrence = new DurationParameter("Event Recurrence Duration", false);
    return new ParameterOptions(this.description)
        .put(PARAMETER_AT, this.dateRanges)
        .put(PARAMETER_EVERY, this.recurrence);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.addTaskCommandUpdate(new EventTask(
        this.description.value(),
        this.dateRanges.value(),
        this.recurrence.value())));
  }
}
