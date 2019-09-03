package command;

import driver.Ui;
import task.Task;
import task.TaskList;
import java.util.ArrayList;

/**
 *
 */

public class SearchCommand extends Command {
    String searchStr;
    ArrayList<Task> mySearched;

    /**
     *
     */

    public SearchCommand(String x) {
        searchStr = x;
    }

    /**
     *
     */

    @Override
    public void executeCommand(TaskList reference, Ui printer) {
        this.reference = reference;
        this.printer = printer;
        mySearched = reference.searchTasks(searchStr);
        this.passToUI(this.formatOutput());
    }

    /**
     *
     */

    public String formatOutput() {
        return TextFormatter.searchFormat(mySearched);
    }

    /**
     *
     */

    public void passToUI(String input) {
        printer.printSearch(input);
    }
}