public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the index-th task from list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        storage.save(tasks);

        StringBuilder builder = new StringBuilder();
        builder.append("Noted. I've removed this task:\n");
        builder.append("  " + task + "\n");
        printTotalTask(tasks, builder);

        return builder.toString();
    }

    private void printTotalTask(TaskList tasks, StringBuilder builder) {
        boolean isPlural = tasks.size() > 1;
        builder.append("Now you have " + tasks.size() + " task" + (isPlural ? "s" : "") + " in the list.\n");
    }
}
