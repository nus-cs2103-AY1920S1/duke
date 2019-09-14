package weomucat.duke.ui.listener;

import weomucat.duke.exception.DukeException;

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
}
