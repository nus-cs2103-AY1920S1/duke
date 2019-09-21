package weomucat.doko;

import java.util.Locale;
import weomucat.doko.command.DisplayMessageCommand;
import weomucat.doko.command.LoadTasksCommand;
import weomucat.doko.command.listener.AddTaskCommandListener;
import weomucat.doko.command.listener.ByeCommandListener;
import weomucat.doko.command.listener.DeleteTaskCommandListener;
import weomucat.doko.command.listener.DisplayCommandListener;
import weomucat.doko.command.listener.DoneTaskCommandListener;
import weomucat.doko.command.listener.EventAtCommandListener;
import weomucat.doko.command.listener.FindTaskCommandListener;
import weomucat.doko.command.listener.ListTaskCommandListener;
import weomucat.doko.command.listener.LoadTasksCommandListener;
import weomucat.doko.command.listener.SnoozeTaskCommandListener;
import weomucat.doko.storage.TaskListStorage;
import weomucat.doko.task.TaskManager;
import weomucat.doko.task.listener.ListTaskListener;
import weomucat.doko.task.listener.ModifyTaskListener;
import weomucat.doko.task.listener.TaskListSizeListener;
import weomucat.doko.task.listener.TaskListStorageListener;
import weomucat.doko.ui.UiManager;
import weomucat.doko.ui.cli.CommandLineUi;
import weomucat.doko.ui.gui.GraphicalUi;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.MessageContent;
import weomucat.doko.ui.message.element.MessageText;

/**
 * Doko is a personal assistant chatbot that is able to remember tasks.
 */
public class Doko {

  // Amount of time in milliseconds to sleep, in any thread spawned by Doko.
  // Used when any thread needs to "poll" for events.
  public static final long THREAD_POLL_SLEEP_DURATION = 100;

  // Language that Doko understands.
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
  private Doko(String taskListPath) {
    assert taskListPath != null;

    this.controller = new Controller();
    this.taskManager = new TaskManager();
    this.storage = new TaskListStorage(taskListPath);
    this.uiManager = new UiManager();
  }

  public static void main(String[] args) throws Exception {
    new Doko("data/tasks").run();
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

    // Load tasks
    this.controller.commandUpdate(new LoadTasksCommand());

    // Greet user
    this.controller.commandUpdate(new DisplayMessageCommand(
        new Message()
            .addBody("Hello! I'm Doko! What can I do for you?")
            .addBody(new MessageContent()
                .addText("Type '")
                .addText("help /all", MessageText.Type.SECONDARY)
                .addText("' to see all commands."))));

    // Block main thread to query for user input & commands.
    this.controller.run();
  }
}
