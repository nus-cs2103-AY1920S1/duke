package weomucat.duke.ui;

import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.FindTaskListener;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Ui is responsible for receiving input from the user, and displaying output to the user.
 */
public interface Ui extends AddTaskListener, ByeCommandListener, DeleteTaskListener,
    DoneTaskListener, FindTaskListener, ListTaskListener {

  /**
   * Add a UserInputListener to the Ui. When user input is received, this listener will be
   * notified.
   *
   * @param listener user input listener
   */
  void newUserInputListener(UserInputListener listener);

  /**
   * Tell Ui to start listening for user input.
   */
  void acceptUserInput();

  /**
   * Displays a nice message to the user.
   *
   * @param message message to display.
   */
  void displayMessage(Message message);

  /**
   * Displays an error message to the user.
   *
   * @param message error message to display.
   */
  void displayError(Message message);
}
