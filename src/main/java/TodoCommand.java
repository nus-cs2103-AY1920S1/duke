package duke.command;

import duke.component.*;
import duke.exception.*;
import duke.task.Todo;

public class TodoCommand extends AddCommand {
    boolean isDone;
    String description;

    public TodoCommand(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;

    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        storage.appendToFile("T", null, this.description);

        Todo newTodo = new Todo(this.description);

        taskList.addTask(newTodo);

        int numTask = taskList.size();

        ui.printText("Got it. I've added this task: \n" + "  "
                + newTodo + "Now you have " +
                numTask + " tasks in the list.");


    }


}