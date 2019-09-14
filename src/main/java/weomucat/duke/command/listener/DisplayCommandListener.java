package weomucat.duke.command.listener;

import weomucat.duke.ui.Message;

/**
 * When DisplayCommand is run, this listener will be notified.
 */
public interface DisplayCommandListener extends CommandListener {

  /**
   * Displays a nice message to the user.
   *
   * @param message message to display
   */
  void displayMessage(Message message);

  /**
   * Displays an error message to the user.
   *
   * @param message error message to display
   */
  void displayError(Message message);
}
