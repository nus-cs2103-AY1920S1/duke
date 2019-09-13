package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.exception.DukeException;

public class ByeCommand extends Command<ByeCommandListener> {

  public ByeCommand(Collection<ByeCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {

  }

  @Override
  public void run() throws DukeException {
    forEachListener(ByeCommandListener::byeCommandUpdate);
  }
}
