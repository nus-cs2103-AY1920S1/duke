/**
 * This is a class for command dealing with additions of new event tasks.
 * @author Choong Yong Xin
 */

import java.io.IOException;

public class EventCommand extends Command {

    EventCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, EmptyDescDukeException {
        try {
            String[] commandLine = commandDesc.substring(6).split(" /at ");
            Event newEvent = new Event(commandLine[0], commandLine[1]);
            storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newEvent.stringForAppend());
            tasks.addEvent(newEvent);
            String output = "Got it. I've added this task: \n";
            output += newEvent + "\n";
            output += "Now you have " + tasks.taskList.size() + " tasks in the list.\n";
            return output;
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("event");
        }
    }
}