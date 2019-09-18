import java.util.ArrayList;

/**
 * Command corresponding to user input starting with "find".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class KeyCommand extends Command {
    private String[] keyword;

    /**
     * Constructs a AddCommand object.
     * @param keyword indicates the keyword the user is searching for
     */
    public KeyCommand(String... keyword) {
        this.keyword = keyword;
    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param storage stores the added task to the specified file
     * @return String to be displayed as Duke response in GUI
     * @throws DukeException if keywords do not match any existing tasks
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String printable;
        printable = "Here are the matching tasks in your list: " + "\n";
        ArrayList<String> resultList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            String description = tasks.get(i).getDesc();

            for (int k = 0; k < keyword.length; k++) {
                if (description.contains(keyword[k])) {
                    String toAdd = tasks.get(i).toString();

                    if (!resultList.contains(toAdd)) {
                        resultList.add(toAdd);
                    }
                }
            }
        }

        if (resultList.size() == 0) {
            throw new DukeException("Sorry, there are no tasks matching your keywords!");
        }

        for (int i = 0; i < resultList.size(); i++) {
            printable += i + 1 + ". " + resultList.get(i) + "\n";
        }

        return printable;
    }
}