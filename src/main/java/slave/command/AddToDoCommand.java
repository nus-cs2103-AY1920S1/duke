package slave.command;

import slave.elements.Ui;
import slave.elements.TaskList;

import slave.exception.DukeException;

import slave.task.ToDo;

public class AddToDoCommand extends Command {

    private String task;

    public AddToDoCommand(String task) {
        this.commandType = CommandType.ADDTODO;
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        ToDo toDoTask = new ToDo(this.task, taskList.getSize() + 1);
        taskList.addToList(toDoTask);
        ui.printAddToDoCommand(toDoTask, taskList);
    }
}
