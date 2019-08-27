public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }
    
    public String execute(TaskList tasks, Storage fileMgr) {
        return tasks.toString();
    }
}
