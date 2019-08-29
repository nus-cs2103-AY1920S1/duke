public class ToDoCommand extends Command {
    private String args;

    public ToDoCommand(String args) {
        this.args = args;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDo(false, args); //args is the description string
        tasks.addTask(task);
        storage.updateFile(tasks);
    }
}
