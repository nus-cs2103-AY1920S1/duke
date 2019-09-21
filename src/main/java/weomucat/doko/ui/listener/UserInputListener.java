package weomucat.doko.ui.listener;

import weomucat.doko.command.Command;

/**
 * When user input is received, this listener will be notified.
 */
public interface UserInputListener {

  /**
   * When a user input string is received, this method will be called.
   *
   * @param userInput user input
   */
  void userInputUpdate(String userInput);

  /**
   * When a command is received, this method will be called.
   *
   * @param command command
   */
  void commandUpdate(Command<?> command);
}
