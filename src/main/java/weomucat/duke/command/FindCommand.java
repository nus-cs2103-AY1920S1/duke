package weomucat.duke.command;

import java.util.HashMap;

public abstract class FindCommand implements Command {

  private String keyword;

  @Override
  public String[] getParameterOptions() {
    return new String[0];
  }

  @Override
  public void setParameters(String body, HashMap<String, String> parameters) {
    this.keyword = body;
  }

  @Override
  public void run() {
    updateListeners(keyword);
  }

  /**
   * Listeners to update when this Command is run.
   *
   * @param keyword search string to look for in tasks
   */
  public abstract void updateListeners(String keyword);
}
