package slave.command;

import slave.elements.TaskList;
import slave.elements.Ui;
import slave.exception.DukeException;
import slave.exception.TaskAlreadyDoneException;
import slave.exception.TaskNotFoundException;
import slave.task.Task;

public class DoneCommand extends Command{

    int index;

    public DoneCommand(int index){
        this.commandType = CommandType.DONE;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            taskList.getTaskByIndex(this.index);
        } catch (IndexOutOfBoundsException error) {
            throw new TaskNotFoundException("Task " + this.index );
        }
        if (taskList.getTaskByIndex(this.index).getIsDone()){
            throw new TaskAlreadyDoneException("Task " + this.index );
        }
        taskList.setDoneInList(this.index);
        Task task = taskList.getTaskByIndex(this.index);
        ui.printDoneCommand(task, taskList);
    }
}
