package duke.execution.command;

import java.io.IOException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.Ui;

import duke.models.Task;
import duke.models.Todo;

public class ToDoCommand extends Command {

    /**
     * Constructor for To Do Command.
     *
     * @param action Description of To Do task.
     */
    public ToDoCommand(String action) {
        super(action);
    }

    /**
     * Executes the To do command. Adds the to do task to
     * a list and prints out the task again to confirm that
     * task has been added to the list.
     *
     * @param errands Adds the deadline to the list of tasks.
     * @param ui Prints out statements to indicate to user what
     *           has happened.
     * @param storage Stores the tasks inside another file so that
     *                the task will be available the next time the
     *                bot starts up.
     * @return Returns String to print out to the user.
     * @throws IOException If the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        Task assignmentToDo = new Todo(action);
        TaskList tasks = new TaskList();
        tasks.addToTaskList(assignmentToDo);
        errands.addToCompleteList(assignmentToDo);
        storage.addToFile(Storage.file, assignmentToDo.toString());
        String todoOutput = ui.printGI() + "\n";
        todoOutput += "  " + assignmentToDo.toString() + "\n";
        todoOutput += Ui.printNumOfTasks();
        return todoOutput;
    }
}
