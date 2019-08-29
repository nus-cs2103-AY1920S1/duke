package weomucat.duke.command.listener;

/**
 * When ListTaskCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface ListTaskCommandListener {

  /**
   * When ListTaskCommand is run, this method will be called.
   */
  void listTaskCommandUpdate();
}
