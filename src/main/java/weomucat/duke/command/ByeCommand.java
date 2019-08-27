package weomucat.duke.command;

import java.util.HashMap;
import weomucat.duke.exception.DukeException;

public abstract class ByeCommand implements Command {

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) throws DukeException {

  }

  @Override
  public void run() throws DukeException {
    updateListeners();
  }

  public abstract void updateListeners();
}
