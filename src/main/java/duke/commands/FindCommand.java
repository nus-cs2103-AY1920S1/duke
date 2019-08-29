package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(Ui ui, Storage storage, TaskList allTasks) throws DukeException {
        ArrayList<Task> allTasksArrList = allTasks.getArrayList();

        ArrayList<Task> filteredTasks = new ArrayList<Task>();
        for (Task t : allTasksArrList) {
            if (t.toString().contains(this.keyword)) {
                filteredTasks.add(t);
            }
        }

        if (filteredTasks.size() == 0) {
            ui.printLine();
            ui.printSentence("Sorry, no tasks match your search term!");
            ui.printLine();
        } else {
            int counter = 1;
            ui.printLine();
            ui.printSentence("Here are the tasks that contain your search term:");
            for (Task t : filteredTasks) {
                ui.printSentence("\t" + counter + ". " + t);
                counter++;
            }
            ui.printLine();
        }
    }
}
