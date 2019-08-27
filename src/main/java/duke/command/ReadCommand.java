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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (directive.equals("list")) {
            ui.printLine("Here are the tasks in your list:");
            printTaskList(taskList.getTasksList(), ui);
        }
        else { //directive.equals("find")
            ui.printLine("Here are the matching tasks in your list:");
            ArrayList<Task> tempList = new ArrayList<>();
            for (Task task : taskList.getTasksList()) {
                if (task.getDescription().contains(keyword)) {
                    tempList.add(task);
                }
            }
            printTaskList(tempList, ui);
        }
    }

    private void printTaskList(ArrayList<Task> tasks, Ui ui) {
        int i = 0;
        for (Task task : tasks) {
            ui.printLine(String.format("%d. %s", (i + 1), task));
            i++;
        }
    }

}