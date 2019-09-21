package weomucat.doko.command.listener;

/**
 * When FindTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface FindTaskCommandListener extends CommandListener {

  /**
   * When FindTaskCommand is run, this method will be called.
   *
   * @param keyword the keyword to look for
   */
  void findTaskCommandUpdate(String keyword);
}
