package weomucat.duke;

import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.StorageException;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskList;
import weomucat.duke.ui.Ui;

public class Duke implements ByeCommandListener {

  private static final String LOGO = " ____        _        \n"
      + "	|  _ \\ _   _| | _____ \n"
      + "	| | | | | | | |/ / _ \\\n"
      + "	| |_| | |_| |   <  __/\n"
      + "	|____/ \\__,_|_|\\_\\___|\n";

  public static final String DATETIME_PARSE_PATTERN = "dd/MM/yy HHmm";
  public static final String DATETIME_FORMAT_PATTERN = "dd MMMM yyyy, hh:mma, O";

  private boolean running;

  private Controller controller;
  private TaskList taskList;
  private TaskListStorage storage;
  private Ui ui;

  public static void main(String[] args) {
    new Duke("data/tasks").run();
  }

  public Duke(String taskListPath) {
    this.controller = new Controller();
    this.taskList = new TaskList();
    this.storage = new TaskListStorage(taskListPath);

    // Read from user input from stdin.
    this.ui = new Ui(System.in);
  }

  private void run() {
    // Try to initialize TaskList from disk if database exists.
    if (this.storage.exists()) {
      try {
        this.taskList = new TaskList(this.storage.load());
        this.ui.displayMessage("Loaded tasks from disk.");
      } catch (StorageException e) {
        this.ui.displayError(e.getMessage());
      }
    }

    // Set up event listeners
    this.ui.newUserInputListener(this.controller);

    this.controller.newAddTaskCommandListener(this.taskList);
    this.controller.newDeleteTaskCommandListener(this.taskList);
    this.controller.newDoneTaskCommandListener(this.taskList);
    this.controller.newListTaskCommandListener(this.taskList);
    this.controller.newByeCommandListener(this);

    this.taskList.newAddTaskListener(this.storage);
    this.taskList.newDeleteTaskListener(this.storage);
    this.taskList.newDoneTaskListener(this.storage);

    this.taskList.newAddTaskListener(this.ui);
    this.taskList.newDeleteTaskListener(this.ui);
    this.taskList.newDoneTaskListener(this.ui);
    this.taskList.newListTaskListener(this.ui);

    // Greet user
    this.ui.displayMessage(LOGO, "Hello! I'm Duke", "What can I do for you?");

    this.running = true;
    while (this.running) {
      try {
        // Handle next user input
        this.ui.nextUserInput();
      } catch (DukeException e) {
        this.ui.displayError(e.getMessage());
      }
    }

    // Farewell user
    this.ui.displayMessage("Bye. Hope to see you again soon!");
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
  }
}
