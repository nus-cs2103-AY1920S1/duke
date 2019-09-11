/**
 * Represents a task list command.
 * Types of command include "clear", "delete", "done", "find", "list".
 */
public class TaskListCommand extends Command {

    public TaskListCommand(String type, String command) {
        super(type, command);
    }

    /**
     * Executes the TaskList command.
     * Deletes all tasks from task list: "clear".
     * Deletes a task from task list: "delete".
     * Mark a task as done: "done".
     * Search for a task: "find".
     * Displays the task list: "list".
     *
     * @param ui       The Ui currently running.
     * @param taskList The TaskList Class containing the task list.
     * @param storage  The Storage class containing the name of file the be read.
     * @return output The String output for GUI message.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String output = "";
        if (type.equals("clear")) {
            output = taskList.clearTaskList();
        } else if (type.equals("delete")) {
            output = taskList.deleteTask(this.command);
        } else if (type.equals("done")) {
            output = taskList.markAsDone(this.command);
        } else if (type.equals("find")) {
            output = taskList.searchByKeyword(this.command);
        } else if (type.equals("list")) {
            if (TaskList.tasks.isEmpty()) {
                System.out.println("    Congratulations! You have no tasks remaining!");
                output = "Congratulations! You have no tasks remaining!";
            } else {
                output = taskList.printTaskList();
            }
        } else {
            throw new DukeException("    TaskListCommand not identified.");
        }
    return output;
    }
}
