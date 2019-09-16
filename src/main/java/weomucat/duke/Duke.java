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
import weomucat.duke.command.listener.LoadTasksCommandListener;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.storage.TaskListStorage;
import weomucat.duke.task.TaskManager;
import weomucat.duke.task.listener.ListTaskListener;
import weomucat.duke.task.listener.ModifyTaskListener;
import weomucat.duke.task.listener.TaskListSizeListener;
import weomucat.duke.task.listener.TaskListStorageListener;
import weomucat.duke.ui.UiManager;
import weomucat.duke.ui.cli.CommandLineUi;
import weomucat.duke.ui.gui.GraphicalUi;
import weomucat.duke.ui.message.Message;

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
    this.controller.addListener(ByeCommandListener.class, this.uiManager);
    this.controller.addListener(DeleteTaskCommandListener.class, this.taskManager);
    this.controller.addListener(DisplayCommandListener.class, this.uiManager);
    this.controller.addListener(DoneTaskCommandListener.class, this.taskManager);
    this.controller.addListener(EventAtCommandListener.class, this.taskManager);
    this.controller.addListener(FindTaskCommandListener.class, this.taskManager);
    this.controller.addListener(ListTaskCommandListener.class, this.taskManager);
    this.controller.addListener(LoadTasksCommandListener.class, this.taskManager);
    this.controller.addListener(SnoozeTaskCommandListener.class, this.taskManager);

    this.taskManager.addListener(ListTaskListener.class, this.uiManager);
    this.taskManager.addListener(ModifyTaskListener.class, this.uiManager);
    this.taskManager.addListener(TaskListSizeListener.class, this.uiManager);
    this.taskManager.addListener(TaskListStorageListener.class, this.storage);

    // Tell uis to start accepting user input.
    this.uiManager.acceptUserInput();

    // Greet user
    this.controller.commandUpdate(new DisplayMessageCommand(
        new Message().addBody(
            " ____        _        ",
            "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\",
            "| |_| | |_| |   <  __/",
            "|____/ \\__,_|_|\\_\\___|")));

    // Load tasks
    this.controller.commandUpdate(new LoadTasksCommand());

    this.controller.commandUpdate(new DisplayMessageCommand(
        new Message().addBody(
            "Hello! I'm Duke! What can I do for you?",
            "Type 'help /all' to see all commands.")));

    // Block main thread to query for user input & commands.
    this.controller.run();
  }
}
