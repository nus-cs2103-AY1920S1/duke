package Logic;

import Model.Tasklist;
import Storage.Storage;
import UI.UI;

public class HelpCommand implements Command{
    public HelpCommand(){

    }

    @Override
    public void execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "list      Usage: list                                     Lists out and saves all tasks\n" +
                         "todo      Usage: todo <description>                       Adds a todo task\n" +
                         "event     Usage: event <description> /at <details>        adds an event task\n" +
                         "deadline  Usage: deadline <description> /by <details>     adds a deadline task\n" +
                         "find      Usage: find <name>                              searches for a task that matches the name\n" +
                         "done      Usage: done <id>                                marks the task at id as done\n" +
                         "delete    Usage: delete <id>                              removes the task at id\n" +
                         "help      Usage: help                                     brings out the help menu\n" +
                         "bye       Usage: bye                                      Exits the program";
        ui.printData(content);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
