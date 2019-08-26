public abstract class Command {
    protected String taskDesc;
    protected String timeDesc;
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    /*public Command(String taskDesc, String timeDesc) {
        this.taskDesc = taskDesc;
        this.timeDesc = timeDesc;
    }*/

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}

