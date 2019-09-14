package duke.commands;

import java.io.IOException;


import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

import duke.tasks.Event;

public class AddEventCommand extends Command{



    private String description;
    private String time;

    public AddEventCommand(String description, String time) {
        this.description = description;
        this.time = time;
        this.commandType = CommandType.ADDEVENT;
    }


    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        Event task = new Event(this.description,this.time);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }


}
