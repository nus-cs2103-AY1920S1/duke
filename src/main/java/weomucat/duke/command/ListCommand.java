package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.exception.DukeException;

public class ListCommand extends Command<ListTaskCommandListener> {

  private static final String PARAMETER_ALL = "/all";

  private boolean all;

  public ListCommand(Collection<ListTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[] {PARAMETER_ALL};
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {
    this.all = parameters.get(PARAMETER_ALL) != null;
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.listTaskCommandUpdate(this.all));
  }
}
