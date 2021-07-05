package dukepkg.commands;

import dukepkg.Task;
import dukepkg.TaskList;
import dukepkg.Ui;

import java.util.ArrayList;

/**
 * The command used to find certain tasks using keyword matching.
 */
public class FindCommand extends Command {
    /**
     * The keywords used to search the commands.
     */
    private final String[] keywords;

    /**
     * Instantiates a new Find command.
     *
     * @param keywords the keywords
     */
    public FindCommand(String[] keywords) {
        this.keywords = keywords;
    }
    @Override
    public String execute(TaskList tasklist, Ui ui) {
        ArrayList<Task> copy = TaskList.tasks;
        ArrayList<Task> selected = new ArrayList<>();
        for(String s : keywords) {
            for(Task t : copy) {
                if(t.getTask().contains(s)) {
                    selected.add(t);
                }
            }
            for(Task t : selected) {
                copy.remove(t);
            }
        }
        if(selected.size() == 0) {
            return ui.showNoMatchingTask();
        } else {
            return ui.showMatchingTask(selected);
        }
    }
}
