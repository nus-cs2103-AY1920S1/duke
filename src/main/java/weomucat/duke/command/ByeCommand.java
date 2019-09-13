package weomucat.duke.command;

import java.util.Collection;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;

public class ByeCommand extends Command<ByeCommandListener> {

  public ByeCommand(Collection<ByeCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public ParameterOptions getParameterOptions() {
    return null;
  }

  @Override
  public void run() throws DukeException {
    forEachListener(ByeCommandListener::byeCommandUpdate);
  }
}
