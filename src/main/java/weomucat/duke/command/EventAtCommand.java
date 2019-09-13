package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.NumberParser;

public class EventAtCommand extends Command<EventAtCommandListener> {

  private static final String PARAMETER_AT = "/at";

  private int taskIndex;
  private int atIndex;

  public EventAtCommand(Collection<EventAtCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_AT};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {
    if (body.equals("")) {
      throw new InvalidParameterException("The task index cannot be empty!");
    }
    this.taskIndex = new NumberParser(body).parse("The task index is not a valid number.") - 1;

    String at = parameters.get(PARAMETER_AT);
    if (at == null || at.equals("")) {
      throw new InvalidParameterException("The schedule index cannot be empty!");
    }
    this.atIndex = new NumberParser(at).parse("The schedule index is not a valid number.") - 1;
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.eventAtCommandUpdate(taskIndex, atIndex));
  }
}
