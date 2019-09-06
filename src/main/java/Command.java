public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
    // implementation varies for each subclass of Command

    default boolean isExit() {
        return false;
    }
}
