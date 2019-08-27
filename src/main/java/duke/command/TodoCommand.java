package duke.command;


import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    Task newTask;
    public TodoCommand(String taskDescription){
        newTask = new Todo(taskDescription);
    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
        storage.setChangedTrue();
    }

    public boolean isExit(){
        return false;
    }
}
