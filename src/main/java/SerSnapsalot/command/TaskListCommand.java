package SerSnapsalot.command;

import SerSnapsalot.ui.Ui;
import SerSnapsalot.taskList.TaskList;
import SerSnapsalot.storage.Storage;
import SerSnapsalot.exception.DukeException;
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
     * @throws DukeException If unable to identify command/
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
        } else if (type.equals("snap")) {
            int size = taskList.tasks.size();
            for (int i = size/2; i < size; i++) {
                output = taskList.deleteTask(Integer.toString(i));
            }
        } else if (type.equals("list")) {
            if (TaskList.tasks.isEmpty()) {
                output = "Well done kid, you've cleared all your tasks.";
            } else {
                output = taskList.printTaskList();
            }
        } else {
            String exceptionMessage = "TaskListCommand Exception: Command not identified.";
            System.out.println(exceptionMessage);
            throw new DukeException("Use the help function, Underoo.");
        }
    return output;
    }
}
