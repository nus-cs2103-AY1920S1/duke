package com.commands;

import java.io.IOException;
import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(Duke duke) throws DukeException, IOException {
        TaskList taskList = duke.getTaskList();
        Ui ui = duke.getUi();
        if (taskList.getNumTasks() == 0) {
            ui.showMessage("    You have no tasks yet!");
        } else {
            ui.showListCommandResponse(taskList.getTaskArr());
        }
    }

    /**
     * To help with debugging.
     */
    public void print() {
        super.print();
    }
}