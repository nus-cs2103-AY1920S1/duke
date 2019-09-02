/**
 * This is a class for command dealing with additions of new deadline tasks.
 * @author Choong Yong Xin
 */

import java.io.IOException;

public class DeadlineCommand extends Command {

    DeadlineCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            //Check if description is empty (does not check when user input
            //multiple spaces as the description.
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
