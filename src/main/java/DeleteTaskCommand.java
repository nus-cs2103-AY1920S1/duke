public class DeleteTaskCommand extends Command {
    int index;

    /**
     * The delete task command
     * @param index is the index from the list of the item to delete
     */
    DeleteTaskCommand(int index) {
        super(1);
        assert index > 0: "Index should be more than 1";
        this.index = index-1;
    }

    /**
     * Delete the task from the list and print the relevant information
     * @param tasks
     * @param ui
     * @param storage
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.deleteTask(index);
        String output = "";
        output = "Noted. I've removed this task: \n";
        output = output + "   " + currTask.getTaskDetails() + "\n";
        output = output + tasks.listDetails();
        return output;
    }
}
