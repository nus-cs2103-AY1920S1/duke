package weomucat.duke.ui.listener;

import weomucat.duke.exception.DukeException;

/**
 * When user input is received, this listener will be notified.
 */
@FunctionalInterface
public interface UserInputListener {

  /**
   * When user input is received, this method will be called.
   *
   * @param userInput user input
   * @throws DukeException If there is anything wrong with processing.
   */
  void userInputUpdate(String userInput) throws DukeException;
}
