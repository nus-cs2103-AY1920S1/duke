package weomucat.duke.command;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.DurationParser;
import weomucat.duke.parser.NumberParser;

public class SnoozeCommand extends Command<SnoozeTaskCommandListener> {

  private static final String PARAMETER_BY = "/by";

  private int taskIndex;
  private Duration duration;

  public SnoozeCommand(Collection<SnoozeTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_BY};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters)
      throws InvalidParameterException {

    if (body.equals("")) {
      throw new InvalidParameterException("The task index cannot be empty!");
    }
    this.taskIndex = new NumberParser(body).parse("The task index is not a valid number.") - 1;

    String by = parameters.get(PARAMETER_BY);
    if (by == null || by.equals("")) {
      throw new InvalidParameterException("The duration cannot be empty!");
    }
    this.duration = new DurationParser(by).parse();
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.snoozeTaskCommandUpdate(this.taskIndex, this.duration));
  }
}
