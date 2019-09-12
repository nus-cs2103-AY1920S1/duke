package weomucat.duke.command;

import java.util.HashMap;

public abstract class ByeCommand implements Command {

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {

  }

  @Override
  public void run() {
    updateListeners();
  }

  /**
   * Listeners to update when this Command is run.
   */
  public abstract void updateListeners();
}
