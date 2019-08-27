package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.DeadlineTask;
import weomucat.duke.task.Task;

public abstract class DeadlineCommand implements Command {

  private String description;
  private String by;

  @Override
  public String[] getParameterOptions() {
    return new String[]{PARAMETER_BY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {
    this.description = body;
    this.by = parameters.get(PARAMETER_BY);
  }

  public void run() throws DukeException {
    DeadlineTask task = new DeadlineTask(this.description, this.by);
    updateListeners(task);
  }

  public abstract void updateListeners(Task task) throws DukeException;
}
