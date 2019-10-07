package weomucat.doko.command.listener;

/**
 * When ByeCommand is run, this listener will be notified.
 */
@FunctionalInterface
public interface ByeCommandListener extends CommandListener {

  /**
   * When ByeCommand is run, this method will be called.
   */
  void byeCommandUpdate();
}
