package duke.command;


import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Class representing user's command to add new To-do to list of Tasks.
 */
public class TodoCommand extends Command {
    private Todo newTask;

    /**
     * Class constructor specifying description of new To-do task.
     *
     * @param taskDescription Description of To-do task.
     */
    public TodoCommand(String taskDescription){
        newTask = new Todo(taskDescription);
    }

    /**
     * Executes the command to add new To-do task to list of Tasks.
     *
     * @param taskList List of Tasks to be modified by command.
     * @param ui Ui object to be called by the command.
     * @param storage Storage object to be called by the command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
        storage.setChangedTrue();
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return False as not exit command.
     */
    public boolean isExit(){
        return false;
    }
}
