package weomucat.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;
import weomucat.duke.command.ByeCommand;
import weomucat.duke.command.Command;
import weomucat.duke.command.DeadlineCommand;
import weomucat.duke.command.DeleteCommand;
import weomucat.duke.command.DoneCommand;
import weomucat.duke.command.EventAtCommand;
import weomucat.duke.command.EventCommand;
import weomucat.duke.command.FindCommand;
import weomucat.duke.command.ListCommand;
import weomucat.duke.command.SnoozeCommand;
import weomucat.duke.command.TodoCommand;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.EventAtCommandListener;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.command.listener.SnoozeTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.UnknownCommandException;
import weomucat.duke.parser.CommandParser;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 * A Controller accepts user input and converts it to commands for the model or view.
 */
public class Controller implements UserInputListener {

  private static final String COMMAND_DEADLINE = "deadline";
  private static final String COMMAND_EVENT = "event";
  private static final String COMMAND_TODO = "todo";
  private static final String COMMAND_DELETE = "delete";
  private static final String COMMAND_DONE = "done";
  private static final String COMMAND_EVENT_AT = "event_at";
  private static final String COMMAND_FIND = "find";
  private static final String COMMAND_LIST = "list";
  private static final String COMMAND_SNOOZE = "snooze";
  private static final String COMMAND_BYE = "bye";

  private HashMap<String, Supplier<Command>> commands;
  private ArrayList<AddTaskCommandListener> addTaskCommandListeners;
  private ArrayList<DeleteTaskCommandListener> deleteTaskCommandListeners;
  private ArrayList<DoneTaskCommandListener> doneTaskCommandListeners;
  private ArrayList<EventAtCommandListener> eventAtCommandListeners;
  private ArrayList<FindTaskCommandListener> findTaskCommandListeners;
  private ArrayList<ListTaskCommandListener> listTaskCommandListeners;
  private ArrayList<SnoozeTaskCommandListener> snoozeTaskCommandListeners;
  private ArrayList<ByeCommandListener> byeCommandListeners;

  /**
   * Default constructor.
   */
  public Controller() {
    this.addTaskCommandListeners = new ArrayList<>();
    this.deleteTaskCommandListeners = new ArrayList<>();
    this.doneTaskCommandListeners = new ArrayList<>();
    this.eventAtCommandListeners = new ArrayList<>();
    this.findTaskCommandListeners = new ArrayList<>();
    this.listTaskCommandListeners = new ArrayList<>();
    this.snoozeTaskCommandListeners = new ArrayList<>();
    this.byeCommandListeners = new ArrayList<>();

    this.commands = new HashMap<>();
    this.commands.put(COMMAND_DEADLINE, () -> new DeadlineCommand(this.addTaskCommandListeners));
    this.commands.put(COMMAND_EVENT, () -> new EventCommand(this.addTaskCommandListeners));
    this.commands.put(COMMAND_TODO, () -> new TodoCommand(this.addTaskCommandListeners));
    this.commands.put(COMMAND_DELETE, () -> new DeleteCommand(this.deleteTaskCommandListeners));
    this.commands.put(COMMAND_DONE, () -> new DoneCommand(this.doneTaskCommandListeners));
    this.commands.put(COMMAND_EVENT_AT, () -> new EventAtCommand(this.eventAtCommandListeners));
    this.commands.put(COMMAND_FIND, () -> new FindCommand(this.findTaskCommandListeners));
    this.commands.put(COMMAND_LIST, () -> new ListCommand(this.listTaskCommandListeners));
    this.commands.put(COMMAND_SNOOZE, () -> new SnoozeCommand(this.snoozeTaskCommandListeners));
    this.commands.put(COMMAND_BYE, () -> new ByeCommand(this.byeCommandListeners));
  }

  /**
   * Adds a AddTaskCommandListener.
   * When a AddTaskCommand is received, this listener will be notified.
   *
   * @param listener AddTaskCommand listener
   */
  void newAddTaskCommandListener(AddTaskCommandListener listener) {
    this.addTaskCommandListeners.add(listener);
  }

  /**
   * Adds a DeleteTaskCommandListener.
   * When a DeleteTaskCommand is received, this listener will be notified.
   *
   * @param listener DeleteTaskCommand listener
   */
  void newDeleteTaskCommandListener(DeleteTaskCommandListener listener) {
    this.deleteTaskCommandListeners.add(listener);
  }

  /**
   * Adds a DoneTaskCommandListener.
   * When a DoneTaskCommand is received, this listener will be notified.
   *
   * @param listener DoneTaskCommand listener
   */
  void newDoneTaskCommandListener(DoneTaskCommandListener listener) {
    this.doneTaskCommandListeners.add(listener);
  }

  /**
   * Adds a EventAtCommandListener to the Controller.
   * When a FindTaskCommand is received, this listener will be notified.
   *
   * @param listener EventAtCommand listener
   */
  void newEventAtCommandListener(EventAtCommandListener listener) {
    this.eventAtCommandListeners.add(listener);
  }

  /**
   * Adds a FindTaskCommandListener to the Controller.
   * When a FindTaskCommand is received, this listener will be notified.
   *
   * @param listener FindTaskCommand listener
   */
  void newFindTaskCommandListener(FindTaskCommandListener listener) {
    this.findTaskCommandListeners.add(listener);
  }

  /**
   * Adds a ListTaskCommandListener to the Controller.
   * When a ListTaskCommand is received, this listener will be notified.
   *
   * @param listener ListTaskCommand listener
   */
  void newListTaskCommandListener(ListTaskCommandListener listener) {
    this.listTaskCommandListeners.add(listener);
  }

  /**
   * Adds a SnoozeTaskCommandListener to the Controller.
   * When a SnoozeTaskCommand is received, this listener will be notified.
   *
   * @param listener SnoozeTaskCommand listener
   */
  void newSnoozeTaskCommandListener(SnoozeTaskCommandListener listener) {
    this.snoozeTaskCommandListeners.add(listener);
  }

  /**
   * Adds a ByeCommandListener to the Controller.
   * When a ByeCommand is received, this listener will be notified.
   *
   * @param listener ByeCommand listener
   */
  void newByeCommandListener(ByeCommandListener listener) {
    this.byeCommandListeners.add(listener);
  }

  @Override
  public void byeUpdate() throws DukeException {
    commands.get(COMMAND_BYE).get().run();
  }

  @Override
  public void userInputUpdate(String userInput) throws DukeException {
    assert userInput != null;

    // Initialize parser for this line of user input.
    CommandParser parser = new CommandParser(userInput);

    // Get the command of the user input.
    String commandString = parser.getCommand();

    // Resolve the string command to a Supplier<Command> object.
    Supplier<Command> supplier = commands.get(commandString);

    // Command not known, throw an exception.
    if (supplier == null) {
      throw new UnknownCommandException();
    }

    // Get command from supplier.
    Command<?> command = supplier.get();

    // Get what parameters the command accepts.
    String[] parameterOptions = command.getParameterOptions();

    // Parse the parameters from user input.
    HashMap<String, String> parameters = parser.parseParameters(parameterOptions);

    // Set the parameters to the Command object.
    command.setParameters(parser.getBody(), parameters);

    // Finally, run the command.
    command.run();
  }
}
