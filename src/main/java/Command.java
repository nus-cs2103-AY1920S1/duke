public abstract class Command {
    protected String input;
    public Command(String message) {
        this.input = message;
    }

    public String getMessage() {
        return input;
    }

    public abstract void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception;
}
