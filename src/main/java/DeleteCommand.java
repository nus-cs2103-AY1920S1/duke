public class DeleteCommand extends Command {
    private int idx;
    /**
     * Constructor
     * @param idx - Index of task to delete
     */
    public DeleteCommand(int idx) {
        super(null);
        this.idx = idx;
    }

    /**
     * Execute delete command on given task and save into tasklist
     * @param taskList - list containing all existing tasks
     */
    @Override
    public void execute(TaskList taskList) {
        Task task = taskList.get(this.idx);
        taskList.delete(idx);
        UI.printSuccessDeleteTaskMessage(task, taskList.size());
    }
}
