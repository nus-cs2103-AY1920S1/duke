package duke.commands;

import duke.Duke;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DeleteCommand extends Command {
    private static final String DELETE_TASK_STRING = "Noted. I've removed this task:";
    private static final String TOO_MANY_ARGUMENTS = "The delete command needs only one argument.";
    private static final String WRONG_ARGUMENTS = "The delete command needs an integer argument.";
    private static final String WRONG_INDEX = "There is no task with index: ";

    private String[] args;

    public static DeleteCommand create(String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        } else if (numArgs == 1) {
            throw new DukeException(WRONG_ARGUMENTS);
        } else {
            return new DeleteCommand(args);
        }
    }

    private DeleteCommand(String[] args) {
        super();
        this.args = args;
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        int index;
        Task task;
        int numTasks = tasklist.numTasks();

        try {
            index = Integer.parseInt(args[1]);
            if (index > numTasks || index < 1) {
                throw new DukeException(
                        String.format("%s%d", WRONG_INDEX, index)
                );
            }
            task = tasklist.getTask(index);
            tasklist.deleteTask(index);
            String message = String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                    DELETE_TASK_STRING, task, tasklist.numTasks());
            ui.say(message);
            storage.write(tasklist.getFormattedStrings());
        } catch (NumberFormatException e) {
            ui.sayError(WRONG_ARGUMENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
