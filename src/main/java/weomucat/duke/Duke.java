package weomucat.duke;

import java.util.Locale;
import weomucat.duke.command.DisplayMessageCommand;
import weomucat.duke.command.LoadTasksCommand;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DisplayCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
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

    // Set up event listeners
    this.uiManager.newUserInputListener(this.controller);

    this.controller.addListener(AddTaskCommandListener.class, this.taskManager);
    this.controller.addListener(DeleteTaskCommandListener.class, this.taskManager);
    this.controller.addListener(DoneTaskCommandListener.class, this.taskManager);
    this.controller.addListener(EventAtCommandListener.class, this.taskManager);
    this.controller.addListener(FindTaskCommandListener.class, this.taskManager);
    this.controller.addListener(ListTaskCommandListener.class, this.taskManager);
    this.controller.addListener(SnoozeTaskCommandListener.class, this.taskManager);
    this.controller.addListener(ByeCommandListener.class, this.uiManager);
    this.controller.addListener(DisplayCommandListener.class, this.uiManager);

    this.taskManager.newTaskListStorage(this.storage);
    this.taskManager.newModifyTaskListener(this.uiManager);
    this.taskManager.newTaskListSizeListener(this.uiManager);
    this.taskManager.newListTaskListener(this.uiManager);

    // Tell uis to start accepting user input.
    this.uiManager.acceptUserInput();

    // Greet user
    this.controller.addCommand(new DisplayMessageCommand(new Message(
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|")));

    // Load tasks
    this.controller.addCommand(new LoadTasksCommand());

    this.controller.addCommand(new DisplayMessageCommand(
        new Message("Hello! I'm Duke!", "What can I do for you?")));

    // Block main thread to query for user input & commands.
    this.controller.run();
  }
}
