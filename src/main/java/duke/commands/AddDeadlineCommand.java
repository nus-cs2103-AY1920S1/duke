package duke.commands;

import java.io.IOException;


import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

import duke.tasks.Deadline;

public class AddDeadlineCommand extends Command {


    private String description;
    private String time;

    public AddDeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
        this.commandType = CommandType.ADDDEADLINE;
    }




    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        Deadline task = new Deadline(this.description,this.time);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }





}
