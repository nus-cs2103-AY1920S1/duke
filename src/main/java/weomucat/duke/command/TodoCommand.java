package weomucat.duke.command;

import weomucat.duke.DukeConsumer;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.task.TodoTask;

public class TodoCommand extends Command<AddTaskCommandListener> {

  private StringParameter description;

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter("Todo Description", true);
    return new ParameterOptions(this.description);
  }

  @Override
  Class<AddTaskCommandListener> getListenersClass() {
    return AddTaskCommandListener.class;
  }

  @Override
  DukeConsumer<AddTaskCommandListener> getListenerConsumer() {
    return listener -> listener.addTaskCommandUpdate(new TodoTask(
        this.description.value()));
  }
}
