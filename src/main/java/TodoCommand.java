public class TodoCommand extends NewTaskCommand {

    public TodoCommand(String description) {
        super(new Todo(description));
    }
}
