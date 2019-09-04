package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.task.Task;
import java.util.ArrayList;

/**
 * Class that represents command to find tasks.
 */
public class FindCommand extends Command {

    /**
     * The tasks to be printed.
     */
    private TaskList toBePrinted;

    /**
     * The keyword to find in the list.
     */
    private String input;

    /**
     * Constructor that takes in the keyword to find.
     * @param message The keyword.
     */
    public FindCommand(String message) {
        super(message);
        toBePrinted = new TaskList();
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (listOfTasks.size() == 0) {
            throw new DukeException("     The list is empty!");
        }
        ArrayList<Task> tempList = listOfTasks.getTasks();
        for (Task task : tempList) {
            if (task.toString().contains(getMessage())) {
                toBePrinted.addTask(task);
            }
        }
        if (toBePrinted.isEmpty()) {
            throw new DukeException("     The list contain no such keyword!");
        }
        UI printer = new UI(toBePrinted);
        printer.printList();
    }
}
