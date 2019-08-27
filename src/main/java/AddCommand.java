public class AddCommand extends Command {
    private Task task;
    /**
     * Constructor
     * @param task - Task given to execute command
     */
    public AddCommand(Task task) {
        super(task);
        this.task = task;
    }

    /**
     * Adds given task into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.add(this.task);
        UI.printSuccessfulAddMessage(taskList.get(taskList.size()-1), taskList.size());
    }
}
