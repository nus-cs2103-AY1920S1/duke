package taskchick.command;

import taskchick.tasklist.TaskList;
import taskchick.storage.Storage;

/**
 * Command to guide users on how to use Task Chick.
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
        sb.append("Here is how you can use me:\n\n");
        sb.append("VIEWING\n\n");
        print("list (or l)", "to see the tasks you have on your list");
        print("schedule (or s) [date]", "to see the tasks you have scheduled on that specific date");
        print("find (or f) [search term]", "to see the tasks containing that search term");
        sb.append("\nADDING TASKS\n\n");
        print("todo (or -t) [description]", "to add a task of type todo");
        print("deadline (or -d) [description] /by [DD/MM/YYYY HHMM]", "to add a task of type deadline");
        print("event (or -e) )description] /at [DD/MM/YYYY HHMM]", "to add a task of type event");
        sb.append("\nMODIFYING TASKS\n\n");
        print("delete (or d) [task number]", "to delete the task of that specified index number from the task"
                + " list");
        print("complete (or c) [task number]", "to mark the task of that specified index number from the task"
                + " list as completed");
        print("update [task number] [new description]", "to update the description of a task");
        print("undo (or u)", "to undo a modification to the task list");
        return sb.toString();
    }

    /**
     * Creates a printing format for the instructions.
     *
     * @param command Command to be explained.
     * @param description Explanation of command.
     */
    public void print(String command, String description) {
        sb.append(command + " - " + description + "\n\n");
    }
}
