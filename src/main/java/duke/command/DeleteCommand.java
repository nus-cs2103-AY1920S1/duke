package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int idx;
    private static final Commands DELETE_COMMAND_TYPE = Commands.DELETE;

    /**
     * Constructor.
     * @param idx - Index of task to delete
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Returns command type of command.
     * @return Command type
     */
    public Commands getCommandType() {
        return DELETE_COMMAND_TYPE;
    }

    /**
     * Execute delete command on given task and save into taskList.
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        Task task = taskList.get(this.idx);
        taskList.delete(idx);
        return this.getSuccessDeleteTaskMessage(task, taskList.size());
    }

    /**
     * Prints out message after successful deletion of task.
     * @param task - duke.task.Task that has been successfully deleted
     * @param size - Current size of list
     */
    private String getSuccessDeleteTaskMessage(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }
}
