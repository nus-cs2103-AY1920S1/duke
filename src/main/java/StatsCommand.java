/**
 * This is a class for stats command.
 * @author Choong Yong Xin
 */

public class StatsCommand extends Command {

    StatsCommand (String commandDesc) {
        super(commandDesc);
    }

    /**
     * Returns a boolean to indicate whether the command is an exit command.
     *
     * @return false as command is not an exit command.
     */
    boolean isExit() {
        return false;
    }

    /**
     * Returns a string response by Quack when the command is executed.
     * Which include total tasks, total done tasks, number of each type of tasks
     * and number of each type of tasks that are completed.
     *
     * @param tasks TaskList containing the tasks.
     * @param storage Storage to save the tasks.
     * @return string to be displayed
     */
    @Override
    String execute(TaskList tasks, Storage storage) {
        String output = "Number of tasks in total: " + tasks.taskList.size() + "\n";
        output += "Number of Deadline tasks: " + tasks.getNumDeadline() + "\n";
        output += "Number of Event tasks: " + tasks.getNumEvent() + "\n";
        output += "Number of Todo tasks: " + tasks.getNumTodo() + "\n";
        output += "\n";
        output += "Number of [Done] tasks: " + (tasks.getNumDoneDeadline() + tasks.getNumDoneEvent()
                + tasks.getNumDoneTodo()) + "\n";
        output += "Number of [Done] Deadline tasks: " + tasks.getNumDoneDeadline() + "\n";
        output += "Number of [Done] Event tasks: " + tasks.getNumDoneEvent() + "\n";
        output += "Number of [Done] Todo tasks: " + tasks.getNumDoneTodo() + "\n";
        return output;
    }
}
