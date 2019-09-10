/**
 * The Ui class provides methods to deal with user interactions.
 */
public class Ui {

    /**
     * Generates a string of welcome text to the user.
     *
     * @return A string of welcome text.
     */
    public String showWelcome() {
        return "Hi. I am Duke!";
    }

    /**
     * Generates a sentence bidding goodbye to user.
     *
     * @return A string of farewell text.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Generates a string of text when an object is added.
     *
     * @param t The task to be added to the list.
     * @param tasks A TaskList object that contains an ArrayList of task.
     * @return A string of text when an object is added.
     */
    public String printAdd(Task t, TaskList tasks) {
        String s = "Got it. I've added this task:" + "\n"
                + "  " + t + "\n" + "Now you have " + tasks.size() + " "
                + (tasks.size() == 1 ? "task" : "tasks") + " in the list.";
        return s;
    }

    /**
     * Generates a string of text when an object is deleted from the list.
     *
     * @param toDelete The task to be deleted from the list
     * @param tasks A TaskList object that contains an ArrayList of task.
     * @return A string of text when an object is deleted.
     */
    public String printDelete(Task toDelete, TaskList tasks) {
        String s = "Noted. I've removed this task:" + "\n"
                + "  " + toDelete + "\n"
                + "Now you have " + tasks.size() + " "
                + (tasks.size() == 1 ? "task" : "tasks") + " in the list.";
        return s;
    }

    /**
     * Generates a string of text when an object is done.
     *
     * @param number An int representing which task to be marked as done.
     * @param tasks A TaskList object that contains an ArrayList of task.
     * @return A string of text when an object is done.
     */
    public String printDone(int number, TaskList tasks) {
        String s = "Nice! I've marked this task as done:" + "\n"
                + "  " + tasks.getList().get(number - 1);
        return s;
    }

    /**
     * Generates the list of tasks stored in the ArrayList in string.
     *
     * @param tasks A TaskList object that contains an ArrayList of Task.
     * @return A string of tasks stored in the ArrayList.
     */
    public String listTask(TaskList tasks) {
        String s = "Here are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:";
        for (int i = 1; i <= tasks.getList().size(); i++) {
            s = s + "\n" + i + "." + tasks.getList().get(i - 1);
        }
        return s;
    }

    /**
     * Find the tasks in the list based on the keyword.
     *
     * @param keyword A string used to search for the relevant tasks.
     * @param tasks A TaskList object that contains an ArrayList of Task.
     * @return A string of tasks based on the keyword.
     */
    public String printFind(String keyword, TaskList tasks) {
        String s = "Here are the matching tasks in your list:";
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getList().get(i).getDescription().contains(keyword)) {
                s = s + "\n" + count + "." + tasks.getList().get(i);
                count++;
            }
        }
        return s;
    }
}

