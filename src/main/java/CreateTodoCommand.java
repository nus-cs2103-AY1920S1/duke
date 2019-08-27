import duke.task.Todo;

public class CreateTodoCommand extends Command {
    public CreateTodoCommand(String commandInformation) {
        super(commandInformation);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        String todoText = commandInformation;
        tasks.addTask(new Todo(todoText),true);
        storage.writeToTasksFile(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
