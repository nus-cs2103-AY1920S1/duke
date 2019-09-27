package command;

import task.Task;
import task.TaskList;
import java.util.ArrayList;

    /**
    *<h1> SearchCommand</h1>
    * The SearchCommand class
    * 1) Instructs the relevant TaskList to search for the user specified String
    * 2) Instructs the Textformatter to return a message for the user
    *
    */

public class SearchCommand extends Command {
    String searchStr;
    ArrayList<Task> mySearched;

        /**
         * Constructor for SearchCommand Object
         * Stores user specified String as <param>searchStr</param>
         *
         * @param x String to be searched for
         */

    public SearchCommand(String x) {
        searchStr = x;
    }

        /**
         * Searches for the specified String and returns a list of matching tasks
         * Also formats the search String
         *
         * @param reference is the tasklist being used by the program
         * @return String the formatted output, after running through formatOutput()
         */



        @Override
    public String executeCommand(TaskList reference) {
        this.reference = reference;
        mySearched = reference.searchTasks(searchStr);
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.searchFormat(mySearched);
    }


}