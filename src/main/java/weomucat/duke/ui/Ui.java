package weomucat.duke.ui;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.task.listener.AddTaskListener;
import weomucat.duke.task.listener.DeleteTaskListener;
import weomucat.duke.task.listener.DoneTaskListener;
import weomucat.duke.task.listener.FindTaskListener;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Ui is responsible for receiving input from the user, and displaying output to the user.
 */
public class Ui implements AddTaskListener, DeleteTaskListener, DoneTaskListener, FindTaskListener,
    ListTaskListener {

  private static final String SAY_INDENTATION = "\t";
  private static final String SAY_HORIZONTAL_LINE =
      "============================================================";

  private ArrayList<UserInputListener> userInputListeners;
  private Scanner scanner;

  /**
   * Default constructor.
   *
   * @param inputStream The stream which user input is from
   */
  public Ui(InputStream inputStream) {
    this.userInputListeners = new ArrayList<>();
    this.scanner = new Scanner(inputStream);
  }

  /**
   * Add a UserInputListener to the Controller. When user input is received, this listener will be
   * notified.
   *
   * @param listener user input listener
   */
  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  /**
   * Get the next line of user input from the {@link InputStream}.
   *
   * @throws DukeException If any listener throws a DukeException
   */
  public void nextUserInput() throws DukeException {
    String userInput = this.scanner.nextLine();
    for (UserInputListener listener : userInputListeners) {
      listener.userInputUpdate(userInput);
    }
  }

  /**
   * Displays a nice message to the user.
   *
   * @param lines use varargs for multiline messages.
   */
  public void displayMessage(String... lines) {
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
    for (String line : lines) {
      System.out.println(SAY_INDENTATION + line);
    }
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
  }

  /**
   * Displays an error message to the user.
   *
   * @param lines use varargs for multiline messages.
   */
  public void displayError(String... lines) {
    lines[0] = "☹ OOPS!!! " + lines[0];
    displayMessage(lines);
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage("Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d task(s) in the list.", tasks.size()));
  }

  @Override
  public void deleteTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage("Noted. I've removed this task:",
        task.toString(),
        String.format("Now you have %d task(s) in the list.", tasks.size()));
  }

  @Override
  public void doneTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage("Nice! I've marked this task as done:", task.toString());
  }

  @Override
  public void findTaskUpdate(TaskListTasks tasks) {
    ArrayList<String> result = new ArrayList<>();
    result.add("Here are the matching tasks in your list:");

    for (int i = 0; i < tasks.size(); i++) {
      // Get task from tasks
      Task task = tasks.get(i);

      // Format task with no. in front
      result.add(String.format("%d. %s", i + 1, task));
    }

    displayMessage(result.toArray(new String[0]));
  }

  @Override
  public void listTaskUpdate(TaskListTasks tasks) {
    ArrayList<String> out = new ArrayList<>();
    out.add("Here are the tasks in your list:");

    for (int i = 0; i < tasks.size(); i++) {
      // Get task from tasks
      Task task = tasks.get(i);

      // Format task with no. in front
      out.add(String.format("%d. %s", i + 1, task));
    }

    displayMessage(out.toArray(new String[0]));
  }
}
