public abstract class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract String execute(TaskList tasks, Storage fileMgr) throws DukeException;

    public String getCommand() {
        return this.command;
    }
    
    public boolean willTerminate() {
        return false;
    }
}
