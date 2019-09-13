package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.InvalidParameterException;
import weomucat.duke.parser.NumberParser;

public class DeleteCommand extends Command<DeleteTaskCommandListener> {

  private int index;

  public DeleteCommand(Collection<DeleteTaskCommandListener> listeners) {
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
    forEachListener(listener -> listener.deleteTaskCommandUpdate(this.index));
  }
}
