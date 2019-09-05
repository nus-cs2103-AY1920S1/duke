import java.io.IOException;

/**
 * This is a class for command dealing with additions of new deadline tasks.
 * @author Choong Yong Xin
 */

public class DeadlineCommand extends Command {

    DeadlineCommand(String commandDesc) {
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
            //Check if description is empty.
            String[] commandLine = commandDesc.substring(9).split(" /by ");
            Deadline newDeadline = new Deadline(commandLine[0], commandLine[1]);
            storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newDeadline.stringForAppend());
            tasks.addDeadline(newDeadline);
            String output = "Got it. I've added this task: \n";
            output += newDeadline + "\n";
            output += "Now you have " + tasks.taskList.size() + " tasks in the list.\n";
            return output;
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("deadline");
        }
    }
}
