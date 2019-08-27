package duke.command;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    Task newTask;
    public EventCommand(String description, String date, String timing){

    }
    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printGotIt(newTask);
        taskList.addTask(newTask);
        ui.printNumTasks();
    }

    public boolean isExit(){
        return false;
    }
}
