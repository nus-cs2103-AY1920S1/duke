public class AddCommand implements Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds task to the list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);

        StringBuilder builder = new StringBuilder();
        builder.append("Got it. I've added this task:\n");
        builder.append("  " + task + "\n");
        printTotalTask(tasks, builder);

        return builder.toString();
    }

    private void printTotalTask(TaskList tasks, StringBuilder builder) {
        boolean isPlural = tasks.size() > 1;
        builder.append("Now you have " + tasks.size() + " task" + (isPlural ? "s" : "") + " in the list.\n");
    }
}
