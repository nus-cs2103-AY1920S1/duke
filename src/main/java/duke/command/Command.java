package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.parser.DataParser;
import duke.parser.DateParser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to be executed during the running of the Duke Application.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Constructs a new Command where it does not terminate the Duke Application.
     * @param isExit Represents whether the Application should terminate or not.
     */
    public Command(boolean isExit) {
        this.isExit =  isExit;
    }

    /**
     * Gets all the commands available.
     * @return a list of all commands available.
     */
    public static ArrayList<Command> getAllCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new AddDeadlineTaskCommand());
        commands.add(new AddEventTaskCommand());
        commands.add(new AddTagCommand());
        commands.add(new AddToDoTaskCommand());
        commands.add(new CompleteTaskCommand());
        commands.add(new DeleteTaskCommand());
        commands.add(new EditTaskDateCommand());
        commands.add(new EditTaskNameCommand());
        commands.add(new EndCommand());
        commands.add(new FindTaggedTaskCommand());
        commands.add(new FindTaskCommand());
        commands.add(new ListCommandsCommand());
        commands.add(new ListTaskCommand());
        return commands;
    }

    /**
     * Checks if the command signals the exit of a program or not.
     * @return whether the exit signal is given or not.
     */
    public boolean checkIfExit() {
        return this.isExit;
    }

    /**
     * Executes the specific command based on the type of the command.
     * @param taskList The List of tasks involved.
     * @param ui The Interface which deals with user input and interaction.
     * @param storage The storage to load and save task data into the output file.
     * @param dataParser Parses user and task data.
     * @param dateParser Parser date data.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage,
                        DataParser dataParser, DateParser dateParser) throws DukeException;

}
