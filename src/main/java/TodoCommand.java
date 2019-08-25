public class TodoCommand extends Command {
    private String command;
    public TodoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.checkErrorForTodoCommand(command, tasks);
        tasks.add(Parser.createTodo(command));
        ui.printAddedTask(tasks.get(tasks.size() - 1));
        ui.printNoOfTaskInList(tasks);
        storage.appendFile(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
