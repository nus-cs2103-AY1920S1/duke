public interface Command {

    void execute(TaskList task, Ui ui, Storage storage);

    boolean isExit();
}
