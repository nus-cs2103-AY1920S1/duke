public class DoneCommand extends Command {

    /**
     * Constructor for Done command.
     *
     * @param action Description of the task.
     * @param variable Number of tasks that is completed.
     */
    public DoneCommand(String action, String variable) {
        super(action, variable);
    }

    /**
     * Executes the done command and prints out statements to
     * tell the user that the deadline tasks has been added to
     * the list of tasks.
     *
     * @param tasks Not needed in this case.
     * @param ui Not needed in this case.
     * @param storage Not needed in this case.
     * @return Returns String to print out to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null;
        assert ui != null;
        assert storage != null;
        int taskNum = Integer.parseInt(variable);
        TaskList.listOfTasks.get(taskNum - 1).markAsDone();
        String doneOutput = "Nice! I've marked this task as done:\n";
        doneOutput += TaskList.listOfTasks.get(taskNum - 1).toString();
        return doneOutput;
    }
}
