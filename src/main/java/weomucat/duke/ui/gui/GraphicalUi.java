package weomucat.duke.ui.gui;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weomucat.duke.exception.DukeException;
import weomucat.duke.task.Task;
import weomucat.duke.task.TaskListTasks;
import weomucat.duke.ui.Message;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * Represents a JavaFx Graphical User Interface of Duke.
 */
public class GraphicalUi extends Application implements Ui, UserInputListener {

  // Amount of time in milliseconds to sleep before closing the gui.
  private static final int BYE_COMMAND_SLEEP = 1000;

  // For getting this instance when started by JavaFx
  private static GraphicalUi instance;

  private boolean running;
  private Root root;
  private ArrayList<UserInputListener> userInputListeners;

  /**
   * Default constructor.
   */
  public GraphicalUi() {
    this.running = true;
    this.userInputListeners = new ArrayList<>();

    // JavaFx created a GraphicalUi instance, set the instance to the static variable.
    instance = this;
  }

  /**
   * Creates a JavaFx application on a separate thread.
   * Blocks the main thread until the application is created.
   *
   * @return the instance of GraphicalUi which was created
   */
  public static GraphicalUi create() throws InterruptedException {
    GraphicalUi.instance = null;

    // Start GUI on separate thread.
    new Thread(() -> Application.launch(GraphicalUi.class)).start();

    // Block main thread until GraphicalUi instance is created.
    while (GraphicalUi.instance == null) {
      Thread.sleep(THREAD_POLL_SLEEP_DURATION);
    }

    return GraphicalUi.instance;
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
  public void displayMessage(Message message) {
    Platform.runLater(() -> this.root.addMessage(new DukeMessage(message)));
  }

  @Override
  public void displayError(Message message) {
    Platform.runLater(() -> this.root.addMessage(new DukeErrorMessage(message)));
  }

  @Override
  public void addTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage(new Message("Got it. I've added this task:"));
    displayMessage(task.toMessage());
    displayMessage(new Message(
        String.format("Now you have %d task(s) in the list.", tasks.size())));
  }

  @Override
  public void byeCommandUpdate() {
    try {
      this.running = false;
      Thread.sleep(BYE_COMMAND_SLEEP);
      Platform.exit();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage(new Message("Noted. I've removed this task:"));
    displayMessage(task.toMessage());
    displayMessage(new Message(
        String.format("Now you have %d task(s) in the list.", tasks.size())));
  }

  @Override
  public void doneTaskUpdate(TaskListTasks tasks, Task task) {
    displayMessage(new Message("Nice! I've marked this task as done:"));
    displayMessage(task.toMessage());
  }

  @Override
  public void findTaskUpdate(TaskListTasks tasks) {
    displayMessage(new Message("Here are the matching tasks in your list:"));

    for (int i = 0; i < tasks.size(); i++) {
      // Get task from tasks
      Task task = tasks.get(i);

      // Format task with no. in front
      Message message = task.toMessage();
      String title = String.format("%d. %s", i + 1, message.getTitle());
      displayMessage(message.setTitle(title));
    }
  }

  @Override
  public void listTaskUpdate(TaskListTasks tasks) {
    displayMessage(new Message("Here are the tasks in your list:"));
    for (int i = 0; i < tasks.size(); i++) {
      // Get task from tasks
      Task task = tasks.get(i);

      // Format task with no. in front
      Message message = task.toMessage();
      String title = String.format("%d. %s", i + 1, message.getTitle());
      displayMessage(message.setTitle(title));
    }
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
