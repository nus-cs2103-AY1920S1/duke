package duke.command;

import duke.task.TasksController;

/***
 * <p>
 * Command to mark tasks as done.
 * </p>
 */
public class DoneCommand implements Command {
    private TasksController tasksController;
    private int completedTaskIndex;

    /***
     * <p>
     * DoneCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list that contains duke.task.
     * @param argument index of duke.task to be marked as done.
     */
    public DoneCommand(TasksController tasksController, String argument) {
        this.tasksController = tasksController;
        completedTaskIndex = Integer.valueOf(argument) - 1;
    }

    /***
     * <p>
     * Sets duke.task to done.
     * </p>
     * @return new ListenCommand.
     */
    @Override
    public void execute() {
        tasksController.setTaskToDone(completedTaskIndex);
    }
}
