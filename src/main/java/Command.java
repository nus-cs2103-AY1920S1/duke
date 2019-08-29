public interface Command {
    /**
     * Returns whether this is the last command.
     * 
     * @return true if this is the last command
     */
    public boolean isExit();

    /**
     * Executes command.
     * 
     * @param tasks List of task.
     * @param ui User Interface to interact with users.
     * @param storage Storage to write datas to hard disk.
     * @throws DukeException if there is an invalid command.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
