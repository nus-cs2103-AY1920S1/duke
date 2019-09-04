package weomucat.duke.ui.gui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Represents a Graphical User Interface of Duke.
 */
public class GraphicalUi extends Application implements Ui, UserInputListener {

  // For getting this instance when started by JavaFx
  public static GraphicalUi instance;

  private boolean running;
  private Root root;
  private ArrayList<UserInputListener> userInputListeners;

  /**
   * Default constructor.
   */
  public GraphicalUi() {
    this.running = true;
    this.userInputListeners = new ArrayList<>();
    instance = this;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Root.fxml"));
    primaryStage.setScene(new Scene(fxmlLoader.load()));

    this.root = fxmlLoader.getController();
    this.root.newUserInputListener(this);
    primaryStage.show();
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    if (this.running) {
      for (UserInputListener listener : this.userInputListeners) {
        listener.byeUpdate();
      }
    }
  }

  @Override
  public void newUserInputListener(UserInputListener listener) {
    this.userInputListeners.add(listener);
  }

  @Override
  public void acceptUserInput() {

  }

  @Override
  public void displayMessage(String... lines) {
    String message = String.join("\n", lines);
    Platform.runLater(() -> this.root.addDukeText(message));
  }

  @Override
  public void displayError(String... lines) {
    lines[0] = "\u2639 OOPS!!! " + lines[0];
    String message = String.join("\n", lines);
    Platform.runLater(() -> this.root.addDukeErrorText(message));
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage("Got it. I've added this task:",
        task.toString(),
        String.format("Now you have %d task(s) in the list.", tasks.size()));
  }

  @Override
  public void byeCommandUpdate() {
    try {
      this.running = false;
      Thread.sleep(1000);
      Platform.exit();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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

  @Override
  public void byeUpdate() {

  }

  @Override
  public void userInputUpdate(String userInput) throws DukeException {
    for (UserInputListener listener : this.userInputListeners) {
      listener.userInputUpdate(userInput);
    }
  }
}
