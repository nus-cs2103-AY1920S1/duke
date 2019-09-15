package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.DateRangesParameter;
import weomucat.duke.command.parameter.IntervalParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.task.EventTask;

public class EventCommand extends Command<AddTaskCommandListener> {

  private static final String PARAMETER_AT = "/at";
  private static final String PARAMETER_EVERY = "/every";

  private StringParameter description;
  private DateRangesParameter dateRanges;
  private IntervalParameter recurrence;

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter("Event Description", true);
    this.dateRanges = new DateRangesParameter("Event Date Range(s)", true);
    this.recurrence = new IntervalParameter("Event Recurrence Interval", false);
    return new ParameterOptions(this.description)
        .put(PARAMETER_AT, this.dateRanges)
        .put(PARAMETER_EVERY, this.recurrence);
  }

  @Override
  Class<AddTaskCommandListener> getListenerClass() {
    return AddTaskCommandListener.class;
  }

  @Override
  DukeConsumer<AddTaskCommandListener> getListenerConsumer() {
    return listener -> listener.addTaskCommandUpdate(new EventTask(
        this.description.value(),
        this.dateRanges.value(),
        this.recurrence.value()));
  }
}
