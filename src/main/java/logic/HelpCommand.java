package logic;

import model.Tasklist;
import storage.Storage;
import ui.UI;

public class HelpCommand implements Command {

    /**
     * Creates an instance of HelpCommand
     */
    public HelpCommand() {

    }

    /**
     * Parses the arguments of the Command and executes it
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "list      Usage: list                                     Lists out and saves all tasks\n"
                       + "todo      Usage: todo DESCRIPTION                         Adds a todo task\n"
                       + "event     Usage: event DESCRIPTION [/at DETAILS]          Adds an event task\n"
                       + "deadline  Usage: deadline DESCRIPTION [/by DETAILS]       Adds a deadline task\n"
                       + "find      Usage: find KEYWORD                             Searches for a task based on KEYWORD\n"
                       + "done      Usage: done TASK_NUMBER                         marks the task with TASK_NUMBER as done\n"
                       + "delete    Usage: delete TASK_NUMBER                       removes the task with TASK_NUMBER\n"
                       + "help      Usage: help                                     brings out the help menu\n"
                       + "bye       Usage: bye                                      Exits the program";
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
