package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.EventTask;
import weomucat.duke.task.Task;

public abstract class EventCommand implements Command {

  private String description;
  private String at;

  @Override
  public String[] getParameterOptions() {
    return new String[]{PARAMETER_AT};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {
    this.description = body;
    this.at = parameters.get(PARAMETER_AT);
  }

  @Override
  public void run() throws DukeException {
    EventTask task = new EventTask(this.description, this.at);
    updateListeners(task);
  }

  public abstract void updateListeners(Task task) throws DukeException;
}
