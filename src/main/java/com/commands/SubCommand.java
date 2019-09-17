package com.commands;

import com.TaskList;
import com.exceptions.DukeException;
import com.tasks.*;
import com.util.StaticStrings;
import com.util.Storage;

import java.util.ArrayList;

import com.exceptions.command.*;
import com.util.ui.*;
import gui.GUIUi;
import com.util.stats.DukeStatistics;

/**
 * Commands which adds a task to list,
 * where task has meta information.
 */
public class SubCommand extends AddCommand {

    protected String subCommand, subDescription;

    public SubCommand(String commandWord, String description,
                      String subCommandWord, String subDescription) {
        super(commandWord, description, true);
        this.subCommand = subCommandWord;
        this.subDescription = subDescription;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask;
        switch (command) {
        case "deadline":
            newTask = new Deadline(description, subDescription);
            break;
        case "event":
            newTask = new Event(description, subDescription);
            break;
        default:
            throw new DukeExecuteException(StaticStrings.NO_SUCH_COMMAND);
        }

        taskList.addTask(newTask);
        ArrayList<Task> taskArr = taskList.getTaskArr();
        storage.save(taskArr);
        new DukeStatistics().addLog(this, newTask);
        ui.showAddTaskResponse(newTask, taskArr);
    }

    public void print() {
        super.print();
        System.out.println("Subcommand: " + subCommand);
        System.out.println("Subcommand description: " + subDescription);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        // Not even the same class
        if (!(o instanceof SubCommand)) {
            return false;
        }
        SubCommand sc = (SubCommand) o;
        return sc.subCommand == subCommand &&
                sc.subDescription == subDescription;
    }

}