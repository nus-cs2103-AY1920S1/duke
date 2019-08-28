public interface Command {

    public void execute(TaskList task, Ui ui, Storage storage);

    public boolean isExit();
}
