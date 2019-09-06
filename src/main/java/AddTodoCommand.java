import java.util.NoSuchElementException;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String restOfCommand) {
        super(restOfCommand);
    }

    @Override
    public String getDescription() {
        return this.restOfCommand.strip();
    }

    @Override
    public String getDeadline() {
        return "no deadline";
    }

    @Override
    public Task createTask() {
        return null;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.addToList(TaskList.TaskType.TODO, getDescription(), getDeadline());
        } catch (NoSuchElementException e) {
            // user imput after task type is blank
            System.out.println("Oops! You did not enter a description!");
        }
    }
}
