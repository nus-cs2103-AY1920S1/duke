package com.commands;

import com.TaskList;
import com.exceptions.DukeException;
import com.util.Storage;

public class AddCommand extends Command {

    protected String description;
    // Whether command has meta info (like /by, /at)
    protected boolean hasSubCommand;

    /**
     * Constructor for commands adding tasks to list.
     * @param commandWord keyword
     * @param description description of task to add
     * @param hasSubCommand whether it has a subcommand like '/by', '/at'
     */
    public AddCommand(String commandWord, String description, boolean hasSubCommand) {
        super(commandWord);
        this.description = description;
        this.hasSubCommand = hasSubCommand;
    }

    public void execute(TaskList taskList, Storage storage) throws DukeException {
    }

    public void print() {
        super.print();
        System.out.println("Command description: " + description);
        System.out.println("Has subcommand: " + hasSubCommand);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        // Not even the same class
        if (!(o instanceof DoneCommand)) {
            return false;
        }
        AddCommand c = (AddCommand) o;
        return description == c.description &&
                hasSubCommand == c.hasSubCommand;
    }

}
