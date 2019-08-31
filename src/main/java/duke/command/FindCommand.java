package duke.command;

import duke.io.Parser;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException {
        ui.showLine();
        TaskList searchList = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).toString().contains(input)) {
                searchList.add(taskList.get(i));
            }
        }
        if (searchList.isEmpty()) {
            ui.out("No items match your search.");
        } else {
            ui.out("Here are the matching tasks in your list:");
            ui.list(searchList);
        }
        ui.showLine();
    }
}
