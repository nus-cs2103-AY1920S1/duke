/**
 * This is a class for command dealing with deletions of any tasks.
 * @author Choong Yong Xin
 */

import java.io.IOException;

public class DeleteCommand extends Command {

    DeleteCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            //Error if user inputs spaces
            if (commandDesc.substring(7).split(" ")[0].equals("")) {
                throw new InvalidTaskNumberDukeException("empty");
            }
            int taskNumber = Integer.parseInt(commandDesc.substring(7).split(" ")[0]);
            //Check if task number is valid
            if (taskNumber > 0 && taskNumber <= tasks.taskList.size()) {
                storage.deleteFromFile(System.getProperty("user.dir"),
                        tasks.taskList.get(taskNumber - 1).stringForAppend());
                String output = "Noted. I've removed this task: \n";
                output += tasks.taskList.get(taskNumber - 1) + "\n";
                tasks.deleteTask(taskNumber);
                output += "Now you have " + tasks.taskList.size() + " tasks in the list.\n";
                return output;
            } else {
                throw new InvalidTaskNumberDukeException("invalid");
            }
        } catch (IndexOutOfBoundsException err) {
            //If user input for task number is empty
            throw new InvalidTaskNumberDukeException("empty");
        } catch (NumberFormatException err) {
            //If non-numeric input given for task number
            throw new InvalidTaskNumberDukeException("invalid");
        }
    }
}
