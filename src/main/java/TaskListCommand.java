/**
 * Represents a task list command
 * Types of command include "clear", "delete", "done", "find", "list"
 */
public class TaskListCommand extends Command {

    public TaskListCommand (String type, String command) {
        super(type, command);
    }

    /**
     * Executes the TaskList command
     * Deletes all tasks from task list: "clear"
     * Deletes a task from task list: "delete"
     * Mark a task as done: "done"
     * Search for a task: "find"
     * Displays the task list: "list"
     *
     *
     * @param ui  The Ui currently running
     * @param taskList The TaskList Class containing the task list
     * @param storage The Storage class containing the name of file the be read
     *
     */
    @Override
    public void execute (Ui ui, TaskList taskList, Storage storage) throws DukeException {
        switch (this.type) {
            case "clear":
                taskList.clearTaskList();
                break;

            case "delete":
                taskList.deleteTask(this.command);
                break;

            case "done":
                taskList.markAsDone(this.command);
                break;

            case "find":
                taskList.searchByKeyword(this.command);
                break;

            case "list":
                if (taskList.tasks.isEmpty()) {
                    System.out.println("    Congratulations! You have no tasks remaining!");
                } else {
                    taskList.printTaskList();
                }
                break;

            default:
                throw new DukeException("    TaskListCommand not identified.");

        }
    }
}
