package duke.commands;

import java.io.IOException;

import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

import duke.tasks.ToDo;

public class AddToDoCommand extends Command{

    private String [] tokens;

    public AddToDoCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDTODO;
    }


    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        ToDo task = ToDo.createToDo(tokens);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }
    
}
