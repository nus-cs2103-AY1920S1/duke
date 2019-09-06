public abstract class AddTaskCommand implements Command {
    String restOfCommand;

    public AddTaskCommand(String restOfCommand) {
        this.restOfCommand = restOfCommand;
    }

    abstract String getDescription();
    abstract String getDeadline();
    abstract Task createTask();
}
