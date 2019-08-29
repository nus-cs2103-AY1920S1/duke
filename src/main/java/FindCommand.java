public class FindCommand implements Command {
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Finds all task which description contains keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMessage("Here are the matching tasks in your list:");
        int found = 0;
        for (int i = 1; i <= tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                found++;
                ui.showMessage(found + "." + tasks.get(i));
            }
        }
    }
}
