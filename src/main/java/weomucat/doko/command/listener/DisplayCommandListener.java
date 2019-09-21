package weomucat.doko.command.listener;

import weomucat.doko.ui.message.Message;

/**
 * When DisplayCommand is run, this listener will be notified.
 */
public interface DisplayCommandListener extends CommandListener {

  /**
   * Displays nice messages to the user.
   *
   * @param messages messages to display
   */
  void displayMessage(Message... messages);

  /**
   * Displays error messages to the user.
   *
   * @param messages error messages to display
   */
  void displayError(Message... messages);
}
