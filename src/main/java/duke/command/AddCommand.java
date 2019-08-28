package duke.command;

import duke.exception.FileSaveException;
import duke.exception.WrongDateFormatException;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.tasklist.MyList;
import duke.ui.UserInterface;

import java.io.IOException;

public class AddCommand extends Command {
    private String type;
    private String description;
    private String dateTime;

    public AddCommand(String type, String description, String dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(MyList taskList, UserInterface ui, Storage storage) throws FileSaveException,
            WrongDateFormatException {
        Task task;
        if (type.equals("todo")) {
            task = new TodoTask(description);
        } else if (type.equals(("deadline"))) {
            task = new DeadlineTask(description, dateTime);
        } else {
            task = new EventTask(description, dateTime);
        }
        taskList.add(task);
        try {
            storage.updateList(taskList);
        } catch (IOException e) {
            throw new FileSaveException();
        }
        ui.printAddTaskMsg(task, taskList);
    }
}
