import java.lang.StringBuilder;

public class FindCommand extends Command{
    private String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int count = 1;
        StringBuilder searchResult = new StringBuilder();
        for (Task task : tasks.getWholeList()) {
            if (task.getTask().contains(this.word)) {
                searchResult.append(count).append(".").append(task).append("\n");
                count++;
            }
        }
        return searchResult.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
