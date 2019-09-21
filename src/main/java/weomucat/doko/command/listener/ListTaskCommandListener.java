package weomucat.doko.command.listener;

/**
 * When ListTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface ListTaskCommandListener extends CommandListener {

  /**
   * When ListTaskCommand is run, this method will be called.
   *
   * @param all whether or not to list all tasks
   */
  void listTaskCommandUpdate(boolean all);
}
