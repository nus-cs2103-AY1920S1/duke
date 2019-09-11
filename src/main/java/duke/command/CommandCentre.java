package duke.command;

import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.exception.DukeTaskException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Deadline;
import duke.ui.Ui;

import java.util.HashMap;
import java.util.Optional;

public class CommandCentre {
    private HashMap<String, Command> commands;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a CommandCentre and populate it with standard commands.
     *
     * @param taskList the TaskList that the commands can access.
     * @param ui the Ui that handles the String format of the output.
     */
    public CommandCentre(TaskList taskList, Ui ui) {
        commands = new HashMap<>();
        this.taskList = taskList;
        this.ui = ui;
        this.initialiseCommandList();
    }

    /**
     * Returns a Command after it has been found from a collection of Commands.
     *
     * @param key the Command's keyword.
     * @return the Command with it's implementation.
     * @throws DukeCommandException if the Command cannot be identified.
     */
    public Command get(String key) throws DukeCommandException {
        if (commands.containsKey(key)) {
            Command command = commands.get(key);
            return command;
        } else {
            throw new DukeCommandException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void initialiseCommandList() {
        this.addByeCommand();
        this.addListCommand();
        this.addDoneCommand();
        this.addTodoCommand();
        this.addDeadlineCommand();
        this.addEventCommand();
        this.addDeleteCommand();
        this.addFindCommand();
    }

    private void addByeCommand() {
        commands.put("bye", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isPresent()) {
                    throw new DukeTaskException("Bye should not have a description.");
                }
                return ui.showBye();
            }

            @Override
            public boolean isExit() {
                return true;
            }
        });
    }

    private void addListCommand() {
        commands.put("list", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isPresent()) {
                    throw new DukeTaskException("List should not have a description.");
                }
                return ui.showList(taskList.getTaskList());
            }
        });
    }

    private void addDoneCommand() {
        commands.put("done", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The task to be done is not specified.");
                }
                Task doneTask = taskList.doTask(input.get()[0]);
                return ui.showCompletedTask(doneTask);
            }
        });
    }

    private void addTodoCommand() {
        commands.put("todo", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The description of Todo cannot be empty.");
                }
                Todo todo = new Todo(input.get()[0]);
                taskList.addTask(todo);
                return ui.showAddedTask(todo, taskList);
            }
        });
    }

    private void addDeadlineCommand() {
        commands.put("deadline", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The description of a deadline cannot be empty.");
                } else if (input.get().length > 2) {
                    throw new DukeTaskException("There are too many /by in the description.");
                } else if (input.get().length < 2) {
                    throw new DukeTaskException("The description of the deadline is insufficient.");
                }
                Deadline deadline = new Deadline(input.get()[0], Parser.parseDate(input.get()[1]));
                taskList.addTask(deadline);
                return ui.showAddedTask(deadline, taskList);
            }
        });
    }

    private void addEventCommand() {
        commands.put("event", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The description of a event cannot be empty.");
                } else if (input.get().length > 2) {
                    throw new DukeTaskException("There are too many /at in the description.");
                } else if (input.get().length < 2) {
                    throw new DukeTaskException("The description of the deadline is insufficient.");
                }
                Event event = new Event(input.get()[0], Parser.parseDate(input.get()[1]));
                taskList.addTask(event);
                return ui.showAddedTask(event, taskList);
            }
        });
    }

    private void addDeleteCommand() {
        commands.put("delete", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The task to be deleted is not specified.");
                }
                Task deletedTask = taskList.deleteTask(input.get()[0]);
                return ui.showDeletedTask(deletedTask, taskList);
            }
        });
    }

    private void addFindCommand() {
        commands.put("find", new Command() {
            public String execute(Optional<String[]> input) throws DukeException {
                if (input.isEmpty()) {
                    throw new DukeTaskException("The keyword(s) is not specified.");
                }
                TaskList foundList = taskList.findTask(input.get()[0]);
                return ui.showFound(foundList);
            }
        });
    }
}
