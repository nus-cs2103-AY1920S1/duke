package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.NumberParser;

public class DoneCommand extends Command<DoneTaskCommandListener> {

  private int index;

  public DoneCommand(Collection<DoneTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {
    if (body.equals("")) {
      throw new InvalidParameterException("The index cannot be empty!");
    }
    this.index = new NumberParser(body).parse("The index is not a valid number.") - 1;
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.doneTaskCommandUpdate(this.index));
  }
}
