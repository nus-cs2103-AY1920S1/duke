package weomucat.duke;

import java.util.Locale;
import javafx.application.Application;
import weomucat.duke.exception.StorageException;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskList;
import weomucat.duke.ui.UiList;
import weomucat.duke.ui.cli.CommandLineUi;
import weomucat.duke.ui.gui.GraphicalUi;

/**
 * Duke is a personal assistant chatbot that is able to remember tasks.
 */
public class Duke {

  // Amount of time in milliseconds to sleep in all threads.
  public static final long THREAD_SLEEP_DURATION = 100;

  public static final Locale LOCALE = Locale.ENGLISH;
  public static final String DATETIME_PARSE_PATTERN = "ddMMyy HHmm";
  public static final String DATETIME_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

  private Controller controller;
  private TaskList taskList;
  private TaskListStorage storage;
  private UiList uiList;

  public static void main(String[] args) throws Exception {
    new Duke("data/tasks").run();
  }

  /**
   * Default constructor.
   *
   * @param taskListPath Relative path of taskList database.
   */
  public Duke(String taskListPath) {
    this.controller = new Controller();
    this.taskList = new TaskList();
    this.storage = new TaskListStorage(taskListPath);
    this.uiList = new UiList();
  }

  private void run() throws Exception {
    // Start GUI on separate thread.
    new Thread(() -> Application.launch(GraphicalUi.class)).start();

    // Block main thread until GraphicalUi instance is created.
    while (GraphicalUi.instance == null) {
      Thread.sleep(THREAD_SLEEP_DURATION);
    }

    // Add uis to uiList.
    this.uiList.addUi(new CommandLineUi());
    this.uiList.addUi(GraphicalUi.instance);

    // Try to initialize TaskList from disk if database exists.
    if (this.storage.exists()) {
      try {
        this.taskList = new TaskList(this.storage.load());
        this.uiList.displayMessage("Loaded tasks from disk.");
      } catch (StorageException e) {
        this.uiList.displayError(e.getMessage());
      }
    }

    // Set up event listeners
    this.uiList.newUserInputListener(this.controller);

    this.controller.newAddTaskCommandListener(this.taskList);
    this.controller.newDeleteTaskCommandListener(this.taskList);
    this.controller.newDoneTaskCommandListener(this.taskList);
    this.controller.newFindTaskCommandListener(this.taskList);
    this.controller.newListTaskCommandListener(this.taskList);
    this.controller.newByeCommandListener(this.uiList);

    this.taskList.newAddTaskListener(this.storage);
    this.taskList.newDeleteTaskListener(this.storage);
    this.taskList.newDoneTaskListener(this.storage);

    this.taskList.newAddTaskListener(this.uiList);
    this.taskList.newDeleteTaskListener(this.uiList);
    this.taskList.newDoneTaskListener(this.uiList);
    this.taskList.newFindTaskListener(this.uiList);
    this.taskList.newListTaskListener(this.uiList);

    // Greet user
    this.uiList.displayMessage(" ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|");
    this.uiList.displayMessage("Hello! I'm Duke!", "What can I do for you?");

    // Tell uis to start listening for user input.
    this.uiList.acceptUserInput();
  }
}
