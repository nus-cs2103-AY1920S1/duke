package weomucat.duke.command.listener;

/**
 * When ByeCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface ByeCommandListener {

  /**
   * When ByeCommand is run, this method will be called.
   */
  void byeCommandUpdate();
}
