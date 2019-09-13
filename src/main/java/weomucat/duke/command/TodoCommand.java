package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.command.parameter.StringParameter;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.TodoTask;

public class TodoCommand extends Command<AddTaskCommandListener> {

  private StringParameter description;

  public TodoCommand(Collection<AddTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.description = new StringParameter("Todo Description", true);
    return new ParameterOptions(this.description);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.addTaskCommandUpdate(new TodoTask(
        this.description.value())));
  }
}
