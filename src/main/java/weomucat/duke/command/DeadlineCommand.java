package weomucat.duke.command;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.date.Date;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DurationParser;
import weomucat.duke.task.DeadlineTask;

public class DeadlineCommand extends Command<AddTaskCommandListener> {

  private static final String PARAMETER_BY = "/by";
  private static final String PARAMETER_EVERY = "/every";

  private DeadlineTask task;

  public DeadlineCommand(Collection<AddTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_BY, PARAMETER_EVERY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {
    if (body.equals("")) {
      throw new InvalidParameterException("The description of a deadline cannot be empty.");
    }

    String parameterBy = parameters.get(PARAMETER_BY);
    if (parameterBy == null || parameterBy.equals("")) {
      throw new InvalidParameterException("The due date of a deadline cannot be empty.");
    }
    Date by = Date.parse(parameterBy);

    this.task = DeadlineTask.create(body, by);

    String parameterEvery = parameters.get(PARAMETER_EVERY);
    if (parameterEvery != null) {
      Duration every = new DurationParser(parameterEvery).parse();
      this.task.setRecurrence(every);
    }
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.addTaskCommandUpdate(this.task));
  }
}