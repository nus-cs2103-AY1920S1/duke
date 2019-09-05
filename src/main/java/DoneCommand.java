/**
 * This is a class for command where tasks are marked as done.
 * @author Choong Yong Xin
 */

import java.io.IOException;

public class DoneCommand extends Command {

    DoneCommand(String commandDesc) {
        super(commandDesc);
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return false as command is not an exit command.
     */
    boolean isExit() {
        return false;
    }

    /**
     * Returns a string response by Quack when the command is executed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed
     */
    @Override
    String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        try {
            //Error if user inputs spaces
            if (commandDesc.substring(5).split(" ")[0].equals("")) {
                throw new InvalidTaskNumberDukeException("empty");
            }
            int taskNumber = Integer.parseInt(commandDesc.substring(5).split(" ")[0]);
            //Check if task number is valid
            if (taskNumber > 0 && taskNumber <= tasks.taskList.size()) {
                storage.editsFile(System.getProperty("user.dir"), tasks.taskList.get(taskNumber - 1).stringForAppend());
                tasks.taskList.get(taskNumber - 1).markAsDone();
                String output = "Nice! I've marked this task as done: \n";
                output += tasks.taskList.get(taskNumber - 1) + "\n";
                return output;
            } else {
                throw new InvalidTaskNumberDukeException("invalid");
            }
        } catch (IndexOutOfBoundsException err) {
            //If user input for task number is empty
            throw new InvalidTaskNumberDukeException("empty");
            //If non-numeric input given for task number
        } catch (NumberFormatException err) {
            throw new InvalidTaskNumberDukeException("invalid");
        }
    }
}
