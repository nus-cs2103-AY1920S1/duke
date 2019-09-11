package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;
import java.util.Arrays;

public class UpdateCommand extends Command {
    private static final String INVALID_NUM_ARGS = "Insufficient arguments! Usage: update (index) (task command)";
    private static final String WRONG_ARGUMENTS = "The update command needs an integer argument.";
    private static final String WRONG_INDEX = "There is no task with index: ";
    private static final String SUCCESS_STRING = "I've updated the following:";

    private String[] args;

    public static UpdateCommand create(String input, String[] args) throws DukeException {
        if(args.length < 4) {
            throw new DukeException(INVALID_NUM_ARGS);
        }
        return new UpdateCommand(args);
    }

    private UpdateCommand(String[] args) {
        this.args = args;
    }

    public void execute(Storage storage, Ui ui, TaskList tasklist) throws DukeException {
        int index;
        Task oldTask;
        Task newTask;
        int numTasks = tasklist.numTasks();
        String[] newArgs = Arrays.copyOfRange(args, 2, args.length);
        String input = Arrays.toString(newArgs);
        // Might be better to use StringBuilder
        input = input.substring(1, input.length() - 1).replace(",", "");

        try {
            index = Integer.parseInt(args[1]);
            if (index > numTasks || index < 1) {
                throw new DukeException(String.format("%s%d", WRONG_INDEX, index));
            }
            oldTask = tasklist.getTask(index);
            newTask = new AddCommand(input).getTask();
            tasklist.updateTask(index, newTask);
            String message = String.format("%s\n%s\nto:\n%s", SUCCESS_STRING, oldTask, newTask);
            ui.say(message);
            storage.write(tasklist.getFormattedStrings());
        } catch (NumberFormatException e) {
            ui.sayError(WRONG_ARGUMENTS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
