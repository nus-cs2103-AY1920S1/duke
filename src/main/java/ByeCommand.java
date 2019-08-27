public class ByeCommand extends Command {
    /**
     *  Constructs a <code>ByeCommand</code> object.
     */
    public ByeCommand() {
        super("bye");
    }
    
    /**
     *  Executes this command with the supplied <code>TaskList</code> and <code>Storage</code> objects.
     *  @param tasks associated <code>TaskList</code> object to execute the command with.
     *  @param fileMgr associated <code>Storage</code> object to execute the command with.
     *  @return a <code>String</code> containing the output of executing this command.
     */
    public String execute(TaskList tasks, Storage fileMgr) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean willTerminate() {
        return true;
    }
}
