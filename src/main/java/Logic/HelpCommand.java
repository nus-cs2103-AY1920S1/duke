package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class HelpCommand implements Command{

    /**
     * Creates an instance of HelpCommand
     */
    public HelpCommand(){

    }

    /**
     * Parses the arguments of the Command and executes it
     * @param tasks     the TaskList of Tasks
     * @param ui        The User Interface
     * @param storage   Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "list      Usage: list                                     Lists out and saves all tasks\n" +
                         "todo      Usage: todo <description>                       Adds a todo task\n" +
                         "event     Usage: event <description> /at <details>        adds an event task\n" +
                         "deadline  Usage: deadline <description> /by <details>     adds a deadline task\n" +
                         "find      Usage: find <name>                              searches for a task that matches the name\n" +
                         "done      Usage: done <id>                                marks the task at id as done\n" +
                         "delete    Usage: delete <id>                              removes the task at id\n" +
                         "help      Usage: help                                     brings out the help menu\n" +
                         "bye       Usage: bye                                      Exits the program";
        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
