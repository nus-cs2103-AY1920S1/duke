public class FindCommand extends Command {

    protected String keyword;
    private TaskList tasks = new TaskList();

    public FindCommand(String command, String keyword) {
        super(command);
        this.keyword = keyword;
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Finds all the tasks that contains the word provided.
     *
     * @param tasks List of tasks.
     * @param ui User Interface.
     * @param storage Storage of tasks.txt files.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        String word = keyword.trim();
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task task = tasks.getTask(i);
            String desc = task.getDescription();
            if (desc.contains(word)) {
                this.tasks.addTask(tasks.getTask(i));
            } else {
                continue;
            }
        }
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder printStr = new StringBuilder();
        printStr.append("Here are the matching tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            printStr.append(index + ". " + tasks.getTask(i) + "\n");
        }
        return printStr.toString();
    }
}
