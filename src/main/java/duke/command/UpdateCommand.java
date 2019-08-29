package duke.command;

import duke.component.DukeDatabase;
import duke.component.TaskList;
import duke.component.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class UpdateCommand extends Command {
    private UpdateType updateType;

    public enum UpdateType {
        DONE;
    }

    public UpdateCommand(UpdateType type, String input) {
        super(input);
        updateType = type;
    }

    public void execute(TaskList tasks, Ui ui, DukeDatabase database) throws DukeException {
        initialise(tasks, ui, database);

        if (UpdateType.DONE.equals(updateType)) {
            markTaskAsDone();
        }
    }

    // Mark a task in the task list as done.
    private void markTaskAsDone() throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(4).trim());

            Task task = taskList.getTask(index - 1);
            task.markAsDone();

            ui.echo(() -> {
                System.out.printf("%sNice! I've marked this task as done:\n", Ui.INDENTATION_LVL1);
                System.out.printf(ui.indentAndSplit(task.toString(), Ui.INDENTATION_LVL2)); // task details
            });
        } catch (NumberFormatException e) {
            ui.echo(String.format("%s There can only be an integer after the word \"done\"", DukeException.PREFIX));
        }
    }
}
