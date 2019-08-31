import java.util.ArrayList;

/**
 * Command corresponding to user input starting with "find".
 * Executes the command input by using UI and adds the corresponding tasks to the tasklist.
 */
public class KeyCommand extends Command {
    protected String keyword;

    /**
     * Constructs a AddCommand object.
     * @param keyword indicates the keyword the user is searching for
     */
    public KeyCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Adds the program task inputted by the user to the task list.
     * and prints out corresponding response
     * @param tasks holds the list of tasks currently in the program
     * @param storage stores the added task to the specified file
     * @return String to be displayed as Duke response in GUI
     * @throws DukeException if task requirements is not met
     */
    public String execute(TaskList tasks, Storage storage) {
        String printable = "";
        printable = "Here are the matching tasks in your list: " + "\n";
        ArrayList<String> resultList = new ArrayList<String>();

        for (int i = 0; i < tasks.size(); i++) {
            String[] taskDesc = tasks.get(i).getDesc().split(" ");

            for (int j = 0; j < taskDesc.length; j++) {
                if (taskDesc[j].equals(this.keyword)) {
                    resultList.add(tasks.get(i).toString());
                    break;
                }
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            printable += i + 1 + ". " + resultList.get(i) + "\n";
        }

        return printable;
    }

    /**
     * Determines whether the program stops running.
     * @return boolean value false so the program continues to run
     */
    public boolean isExit() {
        return false;
    }
}