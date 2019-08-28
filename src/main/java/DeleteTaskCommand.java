public class DeleteTaskCommand extends Command {
    int index;

    /**
     * The delete task command
     * @param index is the index from the list of the item to delete
     */
    DeleteTaskCommand(int index) {
        super(1);
        this.index = index-1;
    }

    /**
     * Delete the task from the list and print the relevant information
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task currTask = tasks.deleteTask(index);
        ui.printString("Noted. I've removed this task: ");
        ui.printString( "   " + currTask);
        ui.printString(tasks.listDetails());
    }
}
