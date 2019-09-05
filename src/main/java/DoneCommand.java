public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the index-th task to be done.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.markAsDone(index);
        storage.save(tasks);

        StringBuilder builder = new StringBuilder();
        builder.append("Nice! I've marked this task as done:\n");
        builder.append("  " + tasks.get(index) + "\n");
        return builder.toString();
    }
}
