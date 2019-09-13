package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.task.TodoTask;

public class TodoCommand extends Command<AddTaskCommandListener> {

  private TodoTask task;

  public TodoCommand(Collection<AddTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws InvalidParameterException {
    this.task = TodoTask.create(body);
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.addTaskCommandUpdate(this.task));
  }
}
