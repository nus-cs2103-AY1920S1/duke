package duke.command;

import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Command to guide users on how to use Duke.
 */
public class HelpCommand extends Command {

    private StringBuilder sb = new StringBuilder();

    /**
     * Executes the help command with instructions for all possible commands.
     *
     * @param tasks Unused.
     * @param storage Unused.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        sb.append("Here is how you can use me:\n");
        print("bye", "to exit");
        print("list or l", "to show the tasks you have on your list");
        print("todo or t [description]", "to add a task of type todo");
        print("deadline or d [description] /by [date and time]", "to add a task of type deadline");
        print("event or e [description] /at [date and time]", "to add a task of type event");
        print("remove or r [task number]", "to delete the task of that specified index number from the task"
                + " list");
        print("complete or c [task number]", "to mark the task of that specified index number from the task"
                + " list as completed");
        return sb.toString();
    }

    /**
     * Creates a printing format for the instructions.
     *
     * @param command Command to be explained.
     * @param description Explanation of command.
     */
    public void print(String command, String description) {
        sb.append(command + " - " + description + "\n");
    }
}
