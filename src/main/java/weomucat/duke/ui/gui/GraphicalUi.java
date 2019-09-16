package weomucat.duke.ui.gui;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import weomucat.duke.Pair;
import weomucat.duke.command.ByeCommand;
import weomucat.duke.command.Command;
import weomucat.duke.task.NumberedTaskList;
import weomucat.duke.task.Task;
import weomucat.duke.ui.Ui;
import weomucat.duke.ui.listener.UserInputListener;
import weomucat.duke.ui.message.Message;

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
        listener.commandUpdate(new ByeCommand());
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

  private void displayTaskMessage(Message message) {
    Platform.runLater(() -> this.root.addMessage(new DukeTaskMessage(message)));
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
  public void listTaskUpdate(Message message, NumberedTaskList tasks) {
    displayMessage(message);
    for (Pair<Integer, Task> pair : tasks) {
      // Get task from tasks
      Task task = pair.value();

      // Format task with no. in front
      Message m = task.toMessage();
      String title = String.format("%d. %s", pair.key(), m.getTitle());
      displayTaskMessage(m.addTitle(title));
    }
  }

  @Override
  public void modifyTaskUpdate(Message message, Task task) {
    displayMessage(message);
    displayTaskMessage(task.toMessage());
  }

  @Override
  public void taskListSizeUpdate(int size) {
    displayMessage(new Message()
        .addBody(String.format("Now you have %d task(s) in the list.", size)));
  }

  @Override
  public void userInputUpdate(String userInput) {
    for (UserInputListener listener : this.userInputListeners) {
      listener.userInputUpdate(userInput);
    }
  }

  @Override
  public void commandUpdate(Command<?> command) {
    for (UserInputListener listener : this.userInputListeners) {
      listener.commandUpdate(command);
    }
  }
}
