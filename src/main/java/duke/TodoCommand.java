package duke;

public class TodoCommand extends Command {
    TodoCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        String todoDescription;
        try {
            todoDescription = fullCommand.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(todoDescription);
        tasks.addTask(todo);
        ui.showAddTask(todo, tasks.numberOfTasks());
    }
}
