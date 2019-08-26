package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Command which prompts a printing of a list of tasks with a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand which prompts the printing of a list of tasks with a given keyword.
     * @param keyword a string representing the keyword to be searched within the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prompts Ui to print a list of tasks which contain a keyword.
     * @param tasks TaskList which stores the list of tasks.
     * @param ui Ui which feedbacks to user about success of command.
     * @param storage Storage which saves the task into the text file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> temp = new ArrayList<>();
        boolean isFound = false;
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
                isFound = true;
            }
        }
        if (isFound) {
            ui.print(new TaskList(temp).toString());
        } else {
            ui.print("No task found with the \"" + keyword + "\" keyword.");
        }
    }

    public boolean isExit() {
        return false;
    }
}