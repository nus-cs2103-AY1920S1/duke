package seedu.duke;

import javafx.scene.Scene;

public class TutorialCommand extends Command {

    public TutorialCommand() {

    }

    public String execute(TaskList tasks, ExpenseList expenses, Ui ui, Storage taskStorage, Storage expenseStorage) throws Exception {
        return "tutorial";
    }

    public boolean isExit() {
        return false;
    }
}
