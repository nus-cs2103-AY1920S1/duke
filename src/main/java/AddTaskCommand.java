public abstract class AddTaskCommand implements Command {
    String restOfCommand;

    public AddTaskCommand(String restOfCommand) {
        this.restOfCommand = restOfCommand;
    }

    abstract String getDescription();
    abstract String getDeadline();

    // to abstract out creating the task vs adding it to list
    abstract Task createTask();
}
