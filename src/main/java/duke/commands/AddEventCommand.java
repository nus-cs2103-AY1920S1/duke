package duke.commands;

import java.io.IOException;
import java.util.Arrays;


import duke.core.TaskList;
import duke.core.Ui;

import duke.errors.DukeException;

import duke.tasks.Event;

public class AddEventCommand extends Command{

    String [] tokens;

    public AddEventCommand(String [] tokens) {
        this.tokens = tokens;
        this.commandType = CommandType.ADDEVENT;
    }

    public static AddEventCommand addEventIfValid(String [] tokens) {
        if (!Arrays.asList(tokens).contains("/at")) {
            throw new IllegalArgumentException("Missing deadline");
        } else {
            return new AddEventCommand(tokens);
        }
    }


    public void execute(TaskList taskList, Ui ui) throws DukeException, IOException {
        Event task = Event.createEvent(tokens);
        taskList.addToList(task);
        ui.printInput(task, taskList);
    }



}
