package weomucat.duke;

import java.util.Locale;
import weomucat.duke.exception.StorageException;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskList;
import weomucat.duke.ui.Message;
import weomucat.duke.ui.UiList;
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
    assert taskListPath != null;

    this.controller = new Controller();
    this.taskList = new TaskList();
    this.storage = new TaskListStorage(taskListPath);
    this.uiList = new UiList();
  }

  private void run() throws Exception {
    // Create a GUI
    GraphicalUi graphicalUi = GraphicalUi.create();

    // Add uis to uiList.
    this.uiList.addUi(new CommandLineUi());
    this.uiList.addUi(graphicalUi);

    // Try to initialize TaskList from disk if database exists.
    if (this.storage.exists()) {
      try {
        this.taskList = new TaskList(this.storage.load());
        this.uiList.displayMessage(new Message("Loaded tasks from disk."));
      } catch (StorageException e) {
        this.uiList.displayError(new Message(e.getMessage()));
      }
    }

    // Set up event listeners
    this.uiList.newUserInputListener(this.controller);

    this.controller.newAddTaskCommandListener(this.taskList);
    this.controller.newDeleteTaskCommandListener(this.taskList);
    this.controller.newDoneTaskCommandListener(this.taskList);
    this.controller.newEventAtCommandListener(this.taskList);
    this.controller.newFindTaskCommandListener(this.taskList);
    this.controller.newListTaskCommandListener(this.taskList);
    this.controller.newByeCommandListener(this.uiList);

    this.taskList.newSaveTaskListListener(this.storage);

    this.taskList.newModifyTaskListener(this.uiList);
    this.taskList.newTaskListSizeListener(this.uiList);
    this.taskList.newListTaskListener(this.uiList);

    // Greet user
    this.uiList.displayMessage(new Message(
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|"));
    this.uiList.displayMessage(new Message("Hello! I'm Duke!", "What can I do for you?"));

    // Tell uis to start listening for user input.
    this.uiList.acceptUserInput();
  }
}
