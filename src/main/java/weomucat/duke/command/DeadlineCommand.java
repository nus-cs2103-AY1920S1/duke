package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.DateParameter;
import weomucat.duke.command.parameter.IntervalParameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.task.DeadlineTask;

public class DeadlineCommand extends Command<AddTaskCommandListener> {

  private static final String PARAMETER_BY = "/by";
  private static final String PARAMETER_EVERY = "/every";

  private StringParameter description;
  private DateParameter dueDate;
  private IntervalParameter recurrence;

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter("Deadline Description", true);
    this.dueDate = new DateParameter("Deadline Due Date", true);
    this.recurrence = new IntervalParameter("Deadline Recurrence Interval", false);
    return new ParameterOptions(this.description)
        .put(PARAMETER_BY, this.dueDate)
        .put(PARAMETER_EVERY, this.recurrence);
  }

  @Override
  public Class<AddTaskCommandListener> getListenersClass() {
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