package weomucat.duke.command.listener;

/**
 * When LoadTasksCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface LoadTasksCommandListener extends CommandListener {

  /**
   * When ListTasksCommand is run, this method will be called.
   */
  void loadTasksCommandUpdate();
}
