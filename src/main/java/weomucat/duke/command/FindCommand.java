package weomucat.duke.command;

import java.util.Collection;
import java.util.HashMap;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.exception.DukeException;

public class FindCommand extends Command<FindTaskCommandListener> {

  private String keyword;

  public FindCommand(Collection<FindTaskCommandListener> listeners) {
    super(listeners);
  }

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {
    this.keyword = body;
  }

  @Override
  public void run() throws DukeException {
    forEachListener(listener -> listener.findTaskCommandUpdate(keyword));
  }
}
