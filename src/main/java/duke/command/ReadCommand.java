package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents an executable action Read. A <code>ReadCommand</code> object corresponds to
 * a iteration of Tasks in the TaskList and prints it in appropriate format.
 */
public class ReadCommand extends Command {

    private String directive;
    private String keyword;

    public ReadCommand(String directive) {
        super();
        this.directive = directive;
    }

    /**
     * Creates an object of type ReadCommand to read task information.
     *
     * @param directive Specified if read should be of type list or find.
     * @param keyword Keyword to find tasks.
     */
    public ReadCommand(String directive, String keyword) {
        super();
        this.directive = directive;
        this.keyword = keyword;
    }

    /**
     * Performs operations of type Read on Task objects and prints them from the list of tasks.
     * Directs action of user interaction.
     *
     * @param taskList  List of Task objects.
     * @param ui Interface for user interaction.
     * @param storage Interface for read and write operations on file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output;
        if (directive.equals("list")) {
            output = ui.getPrintLine("Here are the tasks in your list:");
            output += printTaskList(taskList.getTasksList(), ui);
            return output;
        } else { //directive.equals("find")
            output = ui.getPrintLine("Here are the matching tasks in your list:");
            ArrayList<Task> tempList = new ArrayList<>();
            for (Task task : taskList.getTasksList()) {
                if (task.getDescription().contains(keyword)) {
                    tempList.add(task);
                }
            }
            output += printTaskList(tempList, ui);
        }
        return output;
    }

    private String printTaskList(ArrayList<Task> tasks, Ui ui) {
        String list = "";
        int i = 0;
        for (Task task : tasks) {
            list += ui.getPrintLine(String.format("%d. %s", (i + 1), task));
            i++;
        }
        return list;
    }

}