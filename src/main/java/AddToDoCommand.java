public class AddTodoCommand extends AddCommand {

    public AddTodoCommand(String line) {
        super(line);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(line);
        tasks.add(todo);
        ui.showAddInformation(todo.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }

}
