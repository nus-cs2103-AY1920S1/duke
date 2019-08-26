package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

public class DoneCommand extends Command {
    private static final String DONE_TASK_STRING = "Nice! I've marked this task as done:";
    private static final String TOO_MANY_ARGUMENTS = "The done command needs only one argument.";
    private static final String WRONG_ARGUMENTS = "The done command needs an integer argument.";
    private static final String WRONG_INDEX = "There is no task with index: ";
    private static final String ALREADY_DONE = "This task is already done.";

    private String[] args;

    public static DoneCommand create(String input, String[] args) throws DukeException {
        int numArgs = args.length;
        if (numArgs > 2) {
            throw new DukeException(TOO_MANY_ARGUMENTS);
        } else if (numArgs == 1) {
            throw new DukeException(WRONG_ARGUMENTS);
        } else {
            return new DoneCommand(args);
        }
    }

    private DoneCommand(String[] args) {
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
            if (task.isDone()) {
                throw new DukeException(ALREADY_DONE);
            }
            tasklist.markDone(index);
            String message = String.format("%s\n\t%s", DONE_TASK_STRING, task);
            ui.say(message);
            storage.write(tasklist.getFormattedStrings());
        } catch (NumberFormatException e) {
            ui.sayError(WRONG_ARGUMENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
