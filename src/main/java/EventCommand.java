import java.io.IOException;

/**
 * This is a class for command dealing with additions of new event tasks.
 * @author Choong Yong Xin
 */

public class EventCommand extends Command {

    EventCommand(String commandDesc) {
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
    String execute(TaskList tasks, Storage storage) throws IOException, EmptyDescDukeException, WrongDateFormatDukeException {
        try {
            String[] commandLine = commandDesc.substring(6).split(" /at ");
            Event newEvent = new Event(commandLine[0], commandLine[1]);
            storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newEvent.stringForAppend());
            tasks.addEvent(newEvent);
            return getOutput(tasks, newEvent);
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("event");
        }
    }

    /**
     * Forms the output string.
     *
     * @param tasks TaskList containing the tasks
     * @param newEvent new event to be added
     * @return output string
     */
    private String getOutput(TaskList tasks, Event newEvent) {
        String output = "Got it. I've added this task: \n";
        output += newEvent + "\n";
        output += "Now you have " + tasks.taskList.size() + " tasks in the list.\n";
        return output;
    }
}