package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;

import java.io.IOException;

public class UpdateCommand extends Command {

    private int taskNumber;
    private String newDescription;

    public UpdateCommand(String[] commandArray) throws NumberFormatException {
        try {
            this.taskNumber = Integer.parseInt(commandArray[0]);
            this.newDescription = commandArray[1];
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please specify the index of the task to be updated.");
        }
    }

    // should receive as update 4 eat food
    public static UpdateCommand process(String[] fullCommand) throws DukeException {
        // if command is only update
        if (fullCommand.length == 1) {
            throw new DukeException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        }
        String[] currArray = fullCommand[1].split("\\s+", 2);
        if (currArray.length == 1) {
            throw new DukeException("OOPS!!! Please enter in the format update [task number] [new "
                    + "description]");
        }
        return new UpdateCommand(currArray);
    }


    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = tasks.editTask(taskNumber, newDescription);
        try {
            storage.store(tasks);
        } catch (IOException e) {
            response += "OOPS!!! " + e.getMessage();
        }
        return response;
    }
}
