package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) throws DukeException {
        if (keyword.equals("")) {
            throw new DukeException("Please enter a keyword to find!");
        }
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasksContainingKeyword = tasks.findTasksByKeyword(keyword);
        String[] tasksToPrint = new String[tasksContainingKeyword.size() + 1];
        tasksToPrint[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i <= tasksContainingKeyword.size(); i++) {
            tasksToPrint[i] = i + "." + tasksContainingKeyword.get(i - 1).toString();
        }
        ui.dukeEcho(tasksToPrint);
    }
}
