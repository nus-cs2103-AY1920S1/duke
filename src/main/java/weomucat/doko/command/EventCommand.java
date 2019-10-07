package weomucat.doko.command;

import weomucat.doko.DokoConsumer;
import weomucat.doko.command.listener.AddTaskCommandListener;
import weomucat.doko.command.parameter.DateRangesParameter;
import weomucat.doko.command.parameter.IntervalParameter;
import weomucat.doko.command.parameter.ParameterOptions;
import weomucat.doko.command.parameter.StringParameter;
import weomucat.doko.task.EventTask;

public class EventCommand extends Command<AddTaskCommandListener> {

  private static final String KEYWORD = "event";
  private static final String DESCRIPTION = "Creates an event task."
      + " An event has a start date and an end date.";

  private static final String PARAMETER_AT = "/at";
  private static final String PARAMETER_EVERY = "/every";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The description of this event.";
  private static final String PARAMETER_AT_DESCRIPTION = "The schedule(s) of this event.";
  private static final String PARAMETER_EVERY_DESCRIPTION =
      "The recurrence interval of this event.";

  private static final String DEFAULT_PARAMETER_NAME = "description";
  private static final String PARAMETER_AT_NAME = "schedule";
  private static final String PARAMETER_EVERY_NAME = "recurrence";

  private StringParameter description;
  private DateRangesParameter dateRanges;
  private IntervalParameter recurrence;

  @Override
  public String getKeyword() {
    return KEYWORD;
  }

  @Override
  String getDescription() {
    return DESCRIPTION;
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter(DEFAULT_PARAMETER_DESCRIPTION, true,
        DEFAULT_PARAMETER_NAME);
    this.dateRanges = new DateRangesParameter(PARAMETER_AT_DESCRIPTION, true,
        PARAMETER_AT_NAME);
    this.recurrence = new IntervalParameter(PARAMETER_EVERY_DESCRIPTION, false,
        PARAMETER_EVERY_NAME);
    return new ParameterOptions(this.description)
        .put(PARAMETER_AT, this.dateRanges)
        .put(PARAMETER_EVERY, this.recurrence);
  }

  @Override
  Class<AddTaskCommandListener> getListenerClass() {
    return AddTaskCommandListener.class;
  }

  @Override
  DokoConsumer<AddTaskCommandListener> getListenerConsumer() {
    return listener -> listener.addTaskCommandUpdate(new EventTask(
        this.description.value(),
        this.dateRanges.value(),
        this.recurrence.value()));
  }
}
