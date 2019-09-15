package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.DateParameter;
import weomucat.duke.command.parameter.IntervalParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.task.DeadlineTask;

public class DeadlineCommand extends Command<AddTaskCommandListener> {

  private static final String KEYWORD = "deadline";
  private static final String DESCRIPTION = "Creates a deadline task."
      + " A deadline is a task that has a due date.";

  private static final String PARAMETER_BY = "/by";
  private static final String PARAMETER_EVERY = "/every";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The description of this deadline.";
  private static final String PARAMETER_AT_DESCRIPTION = "The due date of this deadline.";
  private static final String PARAMETER_EVERY_DESCRIPTION =
      "The recurrence interval of this deadline.";

  private static final String DEFAULT_PARAMETER_NAME = "description";
  private static final String PARAMETER_AT_NAME = "due";
  private static final String PARAMETER_EVERY_NAME = "recurrence";

  private StringParameter description;
  private DateParameter dueDate;
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
    this.dueDate = new DateParameter(PARAMETER_AT_DESCRIPTION, true,
        PARAMETER_AT_NAME);
    this.recurrence = new IntervalParameter(PARAMETER_EVERY_DESCRIPTION, false,
        PARAMETER_EVERY_NAME);
    return new ParameterOptions(this.description)
        .put(PARAMETER_BY, this.dueDate)
        .put(PARAMETER_EVERY, this.recurrence);
  }

  @Override
  public Class<AddTaskCommandListener> getListenerClass() {
    return AddTaskCommandListener.class;
  }

  @Override
  DukeConsumer<AddTaskCommandListener> getListenerConsumer() {
    return listener -> listener.addTaskCommandUpdate(new DeadlineTask(
        this.description.value(),
        this.dueDate.value(),
        this.recurrence.value()));
  }
}