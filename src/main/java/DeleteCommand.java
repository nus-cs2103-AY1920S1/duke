public class DeleteCommand extends Command {

    int indexToRemove = -1;

    public void setIndexToRemove(int indexToRemove) {
        this.indexToRemove = indexToRemove;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toBeRemoved = tasks.getTask(indexToRemove);
        tasks.removeTask(indexToRemove);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeRemoved);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        storage.updateChanges(tasks.getDukeTaskList());
    }
}
