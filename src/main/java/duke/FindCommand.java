package duke;

public class FindCommand extends Command {
    private String word;

    FindCommand(String word) {
        this.word = word;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append(ui.printFindMessage());
        int counter = 0;
        for (Task t: tasks.taskList) {
            if (t.description.contains(word)) {
                counter++;
                sb.append(counter).append(". ").append(t).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
