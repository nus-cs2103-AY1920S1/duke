package duke;

public class FindCommand extends Command {
    String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFindMessage();
        int counter = 0;
        for (Task t: tasks.taskList) {
            if (t.description.contains(word)) {
                counter++;
                System.out.println(counter + ". " + t);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
