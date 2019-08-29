public abstract class Command {
    protected boolean isExit;
    
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
