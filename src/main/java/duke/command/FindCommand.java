package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {
    private TaskList toBePrinted;
    private String input;
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
