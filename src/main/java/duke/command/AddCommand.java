package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor
     * @param task - duke.task.Task given to execute command
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds given task into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.add(this.task);
        this.printSuccessfulAddMessage(taskList.size());
    }

    /**
     * Prints out message after successful deletion of task
     * @param size - Current size of list
     */
    private void printSuccessfulAddMessage(int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
