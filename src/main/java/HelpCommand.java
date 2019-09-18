/**
 * Represent user command to display the user command list.
 */

public class HelpCommand extends Command {

    private String userCommands;

    /**
     * Represents an action to display user command list.
     * @param command Type of task.
     */
    public HelpCommand(String command) {
        super(command);
    }

    /**
     * Shows user commands.
     *
     * @param tasks List of tasks.
     * @param ui User Interface.
     * @param storage Storage of tasks.txt files.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        StringBuilder userCommands = new StringBuilder();
        userCommands.append("User commands list:\n");
        userCommands.append("1. [help] - to view user commands.\n");
        userCommands.append("2. [list] - to view all tasks.\n");
        userCommands.append("3. [todo <description>] - to add a ToDo task.\n");
        userCommands.append("4. [deadline <description> /by <date & time>] - to add a Deadline task.\n");
        userCommands.append("5. [event <description> /at <date & time>] - to add a Event task.\n");
        userCommands.append("6. [find <keyword>] - to find your task using words written in descriptions.\n");
        userCommands.append("7. [done <index of task on list>] - to mark a task as done.\n");
        userCommands.append("8. [delete <index of task on list>] - to delete a task.\n");
        userCommands.append("9. [view <date>] - to view schedule by date.\n");
        userCommands.append("10. [bye] - to exit the program.");
        this.userCommands = userCommands.toString();
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return userCommands;
    }
}
