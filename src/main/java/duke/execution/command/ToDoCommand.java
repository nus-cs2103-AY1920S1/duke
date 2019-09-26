package duke.execution.command;

import java.io.IOException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

import duke.models.Task;
import duke.models.Todo;

public class ToDoCommand extends Command {

    public ToDoCommand(String action) {
        super(action);
    }

    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        String todoOutput = ui.printGI() + "\n";
        Task assignmentToDo = new Todo(action);
        TaskList tasks = new TaskList();
        tasks.addToTaskList(assignmentToDo);
        errands.addToCompleteList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        todoOutput += "  " + assignmentToDo.toString() + "\n";
        todoOutput += Ui.printNumOfTasks();
        return todoOutput;
    }
}
