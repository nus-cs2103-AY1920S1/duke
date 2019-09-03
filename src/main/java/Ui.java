/**
 * The Ui class provides methods to deal with user interactions
 */
public class Ui {

    /**
     * Prints a string of welcome text to the user.
     */
    public String showWelcome() {
        return "Hi. I am Duke!";
    }

    /**
     * Prints a sentence bidding goodbye to user.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a series of statements whenever a task is added.
     *
     * @param t A task that will be added to a list of tasks.
     */
    public String printAdd(Task t, TaskList tasks) {
        String s = "Got it. I've added this task:" + "\n"
                + "  " + t + "\n" + "Now you have " + tasks.size() + " "
                + (tasks.size() == 1 ? "task" : "tasks") + " in the list.";
        return s;
    }

    /**
     * Prints a series of statements whenever a task is deleted.
     *
     * @param toDelete A task that will be deleted from the list of tasks.
     */
    public String printDelete(Task toDelete, TaskList tasks) {
        String s = "Noted. I've removed this task:" + "\n"
                + "  " + toDelete + "\n"
                + "Now you have " + tasks.size() + " "
                + (tasks.size() == 1 ? "task" : "tasks") + " in the list.";
        return s;
    }

    /**
     * Prints a series of statements whenever a task is done.
     *
     * @param number An int representing which task to be marked as done.
     * @param tasks A TaskList object that contains an ArrayList of task.
     */
    public String printDone(int number, TaskList tasks) {
        String s = "Nice! I've marked this task as done:" + "\n"
                + "  " + tasks.getList().get(number - 1);
        return s;
    }

    /**
     * Prints the list of tasks stored in the ArrayList
     *
     * @param tasks A TaskList object that contains an ArrayList of Task.
     */
    public String listTask(TaskList tasks) {
        String s = "Here are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:";
        for (int i = 1; i <= tasks.getList().size(); i++) {
            s = s + "\n" + i + "." + tasks.getList().get(i - 1);
        }
        return s;
    }

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

