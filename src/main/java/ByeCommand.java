public class ByeCommand extends Command {
    public ByeCommand() {
        super("bye");
    }
    
    public String execute(TaskList tasks, Storage fileMgr) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean willTerminate() {
        return true;
    }
}
