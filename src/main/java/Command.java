interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public default boolean isExit() {
        return false;
    }
}