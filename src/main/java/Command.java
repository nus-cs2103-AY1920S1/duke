public abstract class Command {

    protected String command;

    public Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }

    public abstract void execute();
}
