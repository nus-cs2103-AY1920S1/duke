package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor.
     * @param task - duke.task.Task given to execute command
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the command type.
     * @return Command type
     */
    public Commands getCommandType() {
        return this.task.getTaskType();
    }

    /**
     * Adds given task into tasklist.
     * @param taskList - list containing all existing tasks
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.add(this.task);
        return this.getSuccessfulAddMessage(taskList.size());
    }

    /**
     * Prints out message after successful deletion of task.
     * @param size - Current size of list
     */
    private String getSuccessfulAddMessage(int size) {
        return "Got it. I've added this task: \n"
                + "  " + this.task + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /* Returns stored task of comment */
    public Task getTask() {
        return this.task;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand addCommand = (AddCommand) obj;
            if (addCommand.getTask().equals(this.task)) {
                return true;
            }
        }
        return false;
    }
}
