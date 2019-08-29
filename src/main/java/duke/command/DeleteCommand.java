package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command that deletes a task from the task list of Duke.
 */
public class DeleteCommand extends Command {
    private DeleteType deleteType;

    /**
     * The type of delete command.
     */
    public enum DeleteType {
        INDEX;
    }

    /**
     * Constructs a DeleteCommand object.
     *
     * @param type type of delete command.
     * @param input user's input.
     */
    public DeleteCommand(DeleteType type, String input) {
        super(input);
        deleteType = type;
    }

    /**
     * Executes the delete command accordingly.
     *
     * @param tasksList the tasks list of Duke.
     * @param ui the ui of Duke.
     * @param database the database of Duke.
     * @throws DukeException if the user's input is incorrect.
     */
    public void execute(TaskList tasksList, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasksList, ui, database);

        if (DeleteType.INDEX.equals(deleteType)) {
            deleteTask();
        }
    }

    /**
     * Deletes a task from the taskList.
     */
    private void deleteTask() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(6).trim());

            Task task = taskList.removeTask(index - 1);

            ui.echo(() -> {
                System.out.printf("%sNoted. I've removed this task:\n", Ui.INDENTATION_LVL1);
                System.out.printf(ui.indentAndSplit(task.toString(), Ui.INDENTATION_LVL2)); // task details
                System.out.printf("%sNow you have %s in the list.\n", Ui.INDENTATION_LVL1, ui.getTaskPhrase(taskList.size()));
            });
        } catch (NumberFormatException e) {
            ui.echo(String.format("%s There can only be an integer after the word \"delete\"", DukeException.PREFIX ));
        }
    }
}
