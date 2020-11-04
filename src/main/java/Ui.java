import java.util.List;

/**
 * Manages UI related methods.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Greetings when the program starts.
     */
    public static String greet() {
        return "\nHello I'm Duke\n" + "What can I do for you?\n";
    }

    /**
     * Prints message when the program ends.
     */
    public String exit() {
        return "\nBye. Hope to see you again soon!\n";
    }

    /**
     * Prints all the tasks that are inside the list, according to the format given.
     *
     * @param taskList The list that stores all the task
     */
    public String printList(TaskList taskList) {
        String stringBuilder = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            stringBuilder += i + 1 + ".[" + taskList.getTask(i).getType() + "]"
                + "[" + taskList.getTask(i).getStatusIcon() + "] "
                + taskList.getTask(i).getDescription() + taskList.getTask(i).getDate() + "\n";
        }
        return "\nHere are the tasks in your list:\n" + stringBuilder;
    }

    /**
     * Prints the matching results found in list.
     * @param findList The list to find.
     * @return
     */
    public String printTaskList(List<Task> findList) {
        String stringBuilder = "";
        for (int i = 0; i < findList.size(); i++) {
            stringBuilder += i + 1 + ".[" + findList.get(i).getType() + "]"
                + "[" + findList.get(i).getStatusIcon() + "] "
                + findList.get(i).getDescription() + findList.get(i).getDate() + "\n";
        }
        return "\nHere are the matching tasks in your list:\n" + stringBuilder;
    }


}
