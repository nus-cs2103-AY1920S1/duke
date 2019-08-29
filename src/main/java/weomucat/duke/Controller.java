package weomucat.duke;

import java.util.ArrayList;
import java.util.HashMap;
import weomucat.duke.command.ByeCommand;
import weomucat.duke.command.Command;
import weomucat.duke.command.DeadlineCommand;
import weomucat.duke.command.DeleteCommand;
import weomucat.duke.command.DoneCommand;
import weomucat.duke.command.EventCommand;
import weomucat.duke.command.FindCommand;
import weomucat.duke.command.ListCommand;
import weomucat.duke.command.TodoCommand;
import weomucat.duke.command.listener.AddTaskCommandListener;
import weomucat.duke.command.listener.ByeCommandListener;
import weomucat.duke.command.listener.DeleteTaskCommandListener;
import weomucat.duke.command.listener.DoneTaskCommandListener;
import weomucat.duke.command.listener.FindTaskCommandListener;
import weomucat.duke.command.listener.ListTaskCommandListener;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.UnknownCommandException;
import weomucat.duke.task.Task;
import weomucat.duke.ui.listener.UserInputListener;

/**
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller A Controller accepts user
 * input and converts it to commands for the model or view.
 */
public class Controller implements UserInputListener {

  private static final String COMMAND_DEADLINE = "deadline";
  private static final String COMMAND_EVENT = "event";
  private static final String COMMAND_TODO = "todo";
  private static final String COMMAND_DELETE = "delete";
  private static final String COMMAND_DONE = "done";
  private static final String COMMAND_FIND = "find";
  private static final String COMMAND_LIST = "list";
  private static final String COMMAND_BYE = "bye";

  private HashMap<String, Command> commands;
  private ArrayList<AddTaskCommandListener> addTaskCommandListeners;
  private ArrayList<DeleteTaskCommandListener> deleteTaskCommandListeners;
  private ArrayList<DoneTaskCommandListener> doneTaskCommandListeners;
  private ArrayList<FindTaskCommandListener> findTaskCommandListeners;
  private ArrayList<ListTaskCommandListener> listTaskCommandListeners;
  private ArrayList<ByeCommandListener> byeCommandListeners;

  public Controller() {
    this.addTaskCommandListeners = new ArrayList<>();
    this.deleteTaskCommandListeners = new ArrayList<>();
    this.doneTaskCommandListeners = new ArrayList<>();
    this.findTaskCommandListeners = new ArrayList<>();
    this.listTaskCommandListeners = new ArrayList<>();
    this.byeCommandListeners = new ArrayList<>();

    this.commands = new HashMap<>();
    this.commands.put(COMMAND_DEADLINE, new DeadlineCommand() {
      @Override
      public void updateListeners(Task task) throws DukeException {
        for (AddTaskCommandListener listener : addTaskCommandListeners) {
          listener.addTaskCommandUpdate(task);
        }
      }
    });
    this.commands.put(COMMAND_EVENT, new EventCommand() {
      @Override
      public void updateListeners(Task task) throws DukeException {
        for (AddTaskCommandListener listener : addTaskCommandListeners) {
          listener.addTaskCommandUpdate(task);
        }
      }
    });
    this.commands.put(COMMAND_TODO, new TodoCommand() {
      @Override
      public void updateListeners(Task task) throws DukeException {
        for (AddTaskCommandListener listener : addTaskCommandListeners) {
          listener.addTaskCommandUpdate(task);
        }
      }
    });
    this.commands.put(COMMAND_DELETE, new DeleteCommand() {
      @Override
      public void updateListeners(int i) throws DukeException {
        for (DeleteTaskCommandListener listener : deleteTaskCommandListeners) {
          listener.deleteTaskCommandUpdate(i);
        }
      }
    });
    this.commands.put(COMMAND_DONE, new DoneCommand() {
      @Override
      public void updateListeners(int i) throws DukeException {
        for (DoneTaskCommandListener listener : doneTaskCommandListeners) {
          listener.doneTaskCommandUpdate(i);
        }
      }
    });
    this.commands.put(COMMAND_FIND, new FindCommand() {
      @Override
      public void updateListeners(String keyword) {
        for (FindTaskCommandListener listener : findTaskCommandListeners) {
          listener.findTaskCommandUpdate(keyword);
        }
      }
    });
    this.commands.put(COMMAND_LIST, new ListCommand() {
      @Override
      public void updateListeners() {
        for (ListTaskCommandListener listener : listTaskCommandListeners) {
          listener.listTaskCommandUpdate();
        }
      }
    });
    this.commands.put(COMMAND_BYE, new ByeCommand() {
      @Override
      public void updateListeners() {
        for (ByeCommandListener listener : byeCommandListeners) {
          listener.byeCommandUpdate();
        }
      }
    });
  }

  /**
   * Add a AddTaskCommandListener to the Controller. When a AddTaskCommand is received, this
   * listener will be notified.
   *
   * @param listener AddTaskCommand listener
   */
  public void newAddTaskCommandListener(AddTaskCommandListener listener) {
    this.addTaskCommandListeners.add(listener);
  }

  /**
   * Add a DeleteTaskCommandListener to the Controller. When a DeleteTaskCommand is received, this
   * listener will be notified.
   *
   * @param listener DeleteTaskCommand listener
   */
  public void newDeleteTaskCommandListener(DeleteTaskCommandListener listener) {
    this.deleteTaskCommandListeners.add(listener);
  }

  /**
   * Add a DoneTaskCommandListener to the Controller. When a DoneTaskCommand is received, this
   * listener will be notified.
   *
   * @param listener DoneTaskCommand listener
   */
  public void newDoneTaskCommandListener(DoneTaskCommandListener listener) {
    this.doneTaskCommandListeners.add(listener);
  }

  /**
   * Add a FindTaskCommandListener to the Controller. When a FindTaskCommand is received, this
   * listener will be notified.
   *
   * @param listener FindTaskCommand listener
   */
  public void newFindTaskCommandListener(FindTaskCommandListener listener) {
    this.findTaskCommandListeners.add(listener);
  }

  /**
   * Add a ListTaskCommandListener to the Controller. When a ListTaskCommand is received, this
   * listener will be notified.
   *
   * @param listener ListTaskCommand listener
   */
  public void newListTaskCommandListener(ListTaskCommandListener listener) {
    this.listTaskCommandListeners.add(listener);
  }

  /**
   * Add a ByeCommandListener to the Controller. When a ByeCommand is received, this listener will
   * be notified.
   *
   * @param listener ByeCommand listener
   */
  public void newByeCommandListener(ByeCommandListener listener) {
    this.byeCommandListeners.add(listener);
  }

  @Override
  public void userInputUpdate(String userInput) throws DukeException {
    // Initialize parser for this line of user input.
    Parser parser = new Parser(userInput);

    // Get the command of the user input.
    String commandString = parser.getCommand();

    // Resolve the string command to a Command object.
    Command command = commands.get(commandString);

    // Command not known, throw an exception.
    if (command == null) {
      throw new UnknownCommandException();
    }

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
