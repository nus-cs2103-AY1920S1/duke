package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private int idx;

    /**
     * Constructor
     * @param idx - Index of task to delete
     */
    public DoneCommand(int idx) {
        super(null);
        this.idx = idx;
    }

    /**
     * Execute done command on given task and save into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public void execute(TaskList taskList) {
        Task task = taskList.get(idx);
        taskList.done(this.idx);
        this.printSuccessfulDoneMessage(task);
    }

    /**
     * Prints out message after successful marking of task
     * @param task - duke.task.Task that has been successfully marked done
     */
    private void printSuccessfulDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }
}
