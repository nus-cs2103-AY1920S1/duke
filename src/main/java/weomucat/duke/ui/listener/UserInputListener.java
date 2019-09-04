package weomucat.duke.ui.listener;

import weomucat.duke.exception.DukeException;

/**
 * When user input is received, this listener will be notified.
 */
public interface UserInputListener {

  /**
   * When user wants to shutdown duke, this method will be called.
   *
   * @throws DukeException If there is anything wrong with processing.
   */
  void byeUpdate() throws DukeException;

  /**
   * When a user input string is received, this method will be called.
   *
   * @param userInput user input
   * @throws DukeException If there is anything wrong with processing.
   */
  void userInputUpdate(String userInput) throws DukeException;
}
