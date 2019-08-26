package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> temp = new ArrayList<>();
        boolean isFound = false;
        for(Task task : tasks.getList()) {
            if(task.getDescription().contains(keyword)) {
                temp.add(task);
                isFound = true;
            }
        }
        if(isFound) {
            ui.print(new TaskList(temp).toString());
        } else {
            ui.print("No task found with the \"" + keyword + "\" keyword.");
        }
    }

    public boolean isExit() {
        return false;
    }

}