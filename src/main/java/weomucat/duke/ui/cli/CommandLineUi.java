package weomucat.duke.ui.cli;

import static weomucat.duke.Duke.THREAD_SLEEP_DURATION;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Represents a Command Line User Interface of Duke.
 */
public class CommandLineUi implements Ui {

  private static final String SAY_INDENTATION = "\t";
  private static final String SAY_HORIZONTAL_LINE = "============================================================";

  private ArrayList<UserInputListener> userInputListeners;
  private BufferedReader bufferedReader;
  private boolean running;

  /**
   * Default constructor.
   */
  public CommandLineUi() {
    this.userInputListeners = new ArrayList<>();
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    this.running = true;
  }

  @Override
  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @Override
  public void acceptUserInput() {
    while (this.running) {
      try {
        // Work around for non-blocking scanner.
        // https://www.javaspecialists.eu/archive/Issue153.html
        while (!this.bufferedReader.ready()) {

          // Terminate if not running.
          if (!this.running) {
            return;
          }

          Thread.sleep(THREAD_SLEEP_DURATION);
        }

        // Get next user input
        String userInput = this.bufferedReader.readLine().trim();

        // Ignore empty user input
        if (!userInput.equals("")) {
          for (UserInputListener listener : userInputListeners) {
            listener.userInputUpdate(userInput);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void displayMessage(String... lines) {
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
    for (String line : lines) {
      System.out.println(SAY_INDENTATION + line);
    }
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
  }

  @Override
  public void displayError(String... lines) {
    System.out.print("\033[0;31m");
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
    lines[0] = "\u2639 OOPS!!! " + lines[0];
    for (String line : lines) {
      System.out.println(SAY_INDENTATION + line);
    }
    System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
    System.out.print("\033[0;30m");
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage("Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d task(s) in the list.", tasks.size()));
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
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
