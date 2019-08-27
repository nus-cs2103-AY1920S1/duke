package duke.command;

import duke.DateUtil;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class AddCommand extends Command {
    String commandName;
    String argument;
    String optionName;
    String optionArgument;

    /**
     * Constructs a command to add a task.
     *
     * @param commandName the name of the command.
     * @param argument the argument supplied to the command.
     */
    public AddCommand(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    /**
     * Constructs a command to add a task with an option.
     *
     * @param commandName the name of the command.
     * @param argument the argument supplied to the command.
     * @param optionName the name of the option supplied to the command.
     * @param optionArgument the argument of the option supplied to the command.
     */
    public AddCommand(String commandName, String argument, String optionName, String optionArgument) {
        this.commandName = commandName;
        this.argument = argument;
        this.optionName = optionName;
        this.optionArgument = optionArgument;
    }

    /**
     * Executes an add command using the given task list, UI and file storage.
     *
     * @param tasks the task list supplied.
     * @param ui the UI supplied.
     * @param storage the file storage supplied.
     * @throws DukeException if the command fails to execute.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task myTask;
        switch (this.commandName) {
        case "todo":
            myTask = new Todo(this.argument);
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this duke.task: \n  " + myTask
                    + "\nNow you have " + Ui.pluralize("duke/task", tasks.getSize()) + " in the list.");
            break;
        case "deadline":
            if (!this.optionName.equals("by") || this.optionArgument.equals("")) {
                throw new DukeException("The date/time of a deadline cannot be empty.");
            }
            myTask = new Deadline(this.argument, DateUtil.parseDate(optionArgument));
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this duke.task: \n  " + myTask
                    + "\nNow you have " + Ui.pluralize("duke/task", tasks.getSize()) + " in the list.");
            break;
        case "event":
            if (!this.optionName.equals("at") || this.optionArgument.equals("")) {
                throw new DukeException("The date/time of an event cannot be empty.");
            }
            myTask = new Event(this.argument, DateUtil.parseDate(optionArgument));
            tasks.addTask(myTask);
            ui.printMessage("Got it. I've added this duke.task: \n  " + myTask
                    + "\nNow you have " + Ui.pluralize("duke/task", tasks.getSize()) + " in the list.");
            break;
        default:
            break;
        }
        storage.save(tasks);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddCommand)) {
            return false;
        }
        AddCommand command = (AddCommand) other;
        if (this.commandName.equals("todo")) {
            return this.commandName.equals(command.commandName)
                    && this.argument.equals(command.argument);
        } else {
            return this.commandName.equals(command.commandName)
                    && this.argument.equals(command.argument)
                    && this.optionArgument.equals(command.optionArgument)
                    && this.optionName.equals(command.optionName);
        }
    }
}
