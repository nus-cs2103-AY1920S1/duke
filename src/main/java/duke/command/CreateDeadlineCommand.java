package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.exception.DukeException;
import duke.task.Deadline;

import java.text.ParseException;


/**
 * Inherits from abstract Command class.
 * Handles execution of commands in this format:
 * <code>deadline taskName /by DD/MM/YYYY HHmm</code>
 */
public class CreateDeadlineCommand extends Command {
    public CreateDeadlineCommand(String commandInformation) {
        super(commandInformation);
    }

    /**
     * Executes commands in this format: <code>deadline taskName /by DD/MM/YYYY HHmm</code>
     * and reads result of executed command into preset task.txt file
     *
     * @param tasks   <code>TaskList</code> object which holds the taskList
     *                and various methods to operate on the taskList
     * @param ui      <code>UI</code> object which handles console output
     * @param storage <code>Storage</code> object which allows for reading
     *                result of executed command into preset task.txt file
     * @throws DukeException if error related to Duke commands occurs
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String deadline = this.commandInformation;
        String[] deadlineParts = deadline.split(" /by ");
        String deadLineText = deadlineParts[0];
        String by = deadlineParts[1];

        try {
            tasks.addTask(new Deadline(deadLineText, by), true);
        } catch (ParseException error) {
            System.out.println("\t " + error.getMessage() + ". Please enter date in this format DD/MM/YYYY HHMM");
        }

        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
