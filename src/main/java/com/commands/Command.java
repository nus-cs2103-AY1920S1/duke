package com.commands;

import com.TaskList;
import com.util.Storage;
import com.util.ui.Ui;
import gui.GUIUi;
import com.exceptions.*;

/**
 * Contains information given by user about command.
 * Represents a command. A <code>Command</code>
 * corresponds to a main command word (e.g. <code>list</code>, <code>deadline</code> etc.)
 * and details required for it to be execute (e.g. description of event, index of task to be
 * deleted from list, details of subcommands like <code>/by</code>).
 */
public class Command {

    protected String command; // e.g. list, done, bye, todo, deadline, event
    protected boolean continuesProgram;

    public Command(String commandWord) {
        this.command = commandWord;
        continuesProgram = true;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
    }

    public boolean continuesProgram() {
        return continuesProgram;
    }

    public String getCommandWord() {
        return command;
    }

    /**
     * To help with debugging.
     */
    public void print() {
        System.out.println("Command: " + command);
    }

    /**
     * For testing and assertions.
     * @param o Object to compare to.
     * @return Whether object is equivalent/equal.
     */
    @Override
    public boolean equals(Object o) {
        // Not even same class
        if (!(o instanceof Command)) {
            return false;
        }
        // Same object
        if (o == this) {
            return true;
        }
        Command c = (Command) o;
        return command.equals(c.command);
    }

}
