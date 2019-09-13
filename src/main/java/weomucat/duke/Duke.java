package weomucat.duke;

import java.util.Locale;
import weomucat.duke.exception.StorageException;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskManager;
import weomucat.duke.ui.Message;
import weomucat.duke.ui.UiManager;
import weomucat.duke.ui.cli.CommandLineUi;
import weomucat.duke.ui.gui.GraphicalUi;

/**
 * Duke is a personal assistant chatbot that is able to remember tasks.
 */
public class Duke {

  // Amount of time in milliseconds to sleep, in any thread spawned by Duke.
  // Used when any thread needs to "poll" for events.
  public static final long THREAD_POLL_SLEEP_DURATION = 100;

  // Language that Duke understands.
  // Used in String.toLowerCase
  public static final Locale LOCALE = Locale.ENGLISH;

  private Controller controller;
  private TaskManager taskManager;
  private TaskListStorage storage;
  private UiManager uiManager;

  /**
   * Default constructor.
   *
   * @param taskListPath Relative path of taskList database.
   */
  private Duke(String taskListPath) {
    assert taskListPath != null;

    this.controller = new Controller();
    this.taskManager = new TaskManager();
    this.storage = new TaskListStorage(taskListPath);
    this.uiManager = new UiManager();
  }

  public static void main(String[] args) throws Exception {
    new Duke("data/tasks").run();
  }

  private void run() throws Exception {
    // Create a GUI
    GraphicalUi graphicalUi = GraphicalUi.create();

    // Add uis to uiList.
    this.uiManager.addUi(new CommandLineUi());
    this.uiManager.addUi(graphicalUi);

    // Try to initialize TaskList from disk if database exists.
    if (this.storage.exists()) {
      try {
        this.taskManager = new TaskManager(this.storage.load());
        this.uiManager.displayMessage(new Message("Loaded tasks from disk."));
      } catch (StorageException e) {
        this.uiManager.displayError(new Message(e.getMessage()));
      }
    }

    // Set up event listeners
    this.uiManager.newUserInputListener(this.controller);

    this.controller.newAddTaskCommandListener(this.taskManager);
    this.controller.newDeleteTaskCommandListener(this.taskManager);
    this.controller.newDoneTaskCommandListener(this.taskManager);
    this.controller.newEventAtCommandListener(this.taskManager);
    this.controller.newFindTaskCommandListener(this.taskManager);
    this.controller.newListTaskCommandListener(this.taskManager);
    this.controller.newSnoozeTaskCommandListener(this.taskManager);
    this.controller.newByeCommandListener(this.uiManager);

    this.taskManager.newSaveTaskListListener(this.storage);

    this.taskManager.newModifyTaskListener(this.uiManager);
    this.taskManager.newTaskListSizeListener(this.uiManager);
    this.taskManager.newListTaskListener(this.uiManager);

    // Greet user
    this.uiManager.displayMessage(new Message(
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|"));
    this.uiManager.displayMessage(new Message("Hello! I'm Duke!", "What can I do for you?"));

    // Tell uis to start listening for user input.
    this.uiManager.acceptUserInput();
  }
}
