package weomucat.duke;

import static weomucat.duke.Duke.THREAD_POLL_SLEEP_DURATION;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Supplier;
import weomucat.duke.command.ByeCommand;
import weomucat.duke.command.Command;
import weomucat.duke.command.DeadlineCommand;
import weomucat.duke.command.DeleteCommand;
import weomucat.duke.command.DisplayErrorCommand;
import weomucat.duke.command.DoneCommand;
import weomucat.duke.command.EventAtCommand;
import weomucat.duke.command.EventCommand;
import weomucat.duke.command.FindCommand;
import weomucat.duke.command.ListCommand;
import weomucat.duke.command.SnoozeCommand;
import weomucat.duke.command.TodoCommand;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.listener.CommandListener;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.DukeRuntimeException;
import weomucat.duke.exception.UnknownCommandException;
import weomucat.duke.parser.CommandParser;
import weomucat.duke.ui.Message;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 * A Controller accepts user input and converts it to commands for the model or view.
 */
public class Controller implements ByeCommandListener, UserInputListener {

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

  private HeterogeneousContainers<CommandListener> commandListeners;

  private boolean running;
  private LinkedList<String> userInputQueue;
  private LinkedList<Command<?>> commandQueue;

  /**
   * Default constructor.
   */
  public Controller() {
    this.commands = new HashMap<>();
    this.commands.put(COMMAND_DEADLINE, DeadlineCommand::new);
    this.commands.put(COMMAND_EVENT, EventCommand::new);
    this.commands.put(COMMAND_TODO, TodoCommand::new);
    this.commands.put(COMMAND_DELETE, DeleteCommand::new);
    this.commands.put(COMMAND_DONE, DoneCommand::new);
    this.commands.put(COMMAND_EVENT_AT, EventAtCommand::new);
    this.commands.put(COMMAND_FIND, FindCommand::new);
    this.commands.put(COMMAND_LIST, ListCommand::new);
    this.commands.put(COMMAND_SNOOZE, SnoozeCommand::new);
    this.commands.put(COMMAND_BYE, ByeCommand::new);

    this.commandListeners = new HeterogeneousContainers<>();

    this.userInputQueue = new LinkedList<>();
    this.commandQueue = new LinkedList<>();

    // Add self to bye listener to stop running.
    this.commandListeners.add(ByeCommandListener.class, this);
  }

  /**
   * Adds a CommandListener.
   * When a Command is received, all listeners will be notified.
   *
   * @param c        class of CommandListener
   * @param listener CommandListener
   */
  <T extends CommandListener> void addListener(Class<T> c, T listener) {
    this.commandListeners.add(c, listener);
  }

  @Override
  public void byeCommandUpdate() {
    this.running = false;
  }

  @Override
  public void userInputUpdate(String userInput) {
    this.userInputQueue.addLast(userInput);
  }

  @Override
  public void commandUpdate(Command<?> command) {
    this.commandQueue.addLast(command);
  }

  /**
   * Block main thread to query for commands & user input.
   */
  public void run() {
    this.running = true;

    // Main loop to get user input.
    while (this.running) {
      try {
        if (!this.commandQueue.isEmpty()) {
          Command<?> command = this.commandQueue.removeFirst();

          // Finally, run the command.
          command.run(this.commandListeners);
        } else if (!this.userInputQueue.isEmpty()) {
          String userInput = this.userInputQueue.removeFirst();

          // Initialize parser for this line of user input.
          CommandParser commandParser = new CommandParser(userInput);

          // Get the command of the user input.
          String commandString = commandParser.getCommand();

          // Resolve the string command to a Supplier<Command> object.
          Supplier<Command> supplier = commands.get(commandString);

          // Command not known, throw an exception.
          if (supplier == null) {
            throw new UnknownCommandException();
          }

          // Get command from supplier.
          Command<?> command = supplier.get();

          // Get the parameter options of the command.
          ParameterOptions options = command.getParameterOptions();

          // Parse userInput and set the parameters.
          commandParser.parse(options);

          // Add command to back of queue.
          commandUpdate(command);
        } else {
          Thread.sleep(THREAD_POLL_SLEEP_DURATION);
        }
      } catch (DukeException | DukeRuntimeException e) {
        // Add error command to front of queue.
        this.commandQueue.addFirst(
            new DisplayErrorCommand(new Message("☹ OOPS!!! " + e.getMessage())));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
