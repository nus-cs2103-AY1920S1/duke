public class UI {
    protected String line = "    __________________________________";
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Displays horizontal line.
     */
    public String showLine() {
        return line;
    }

    /**
     * Displays greeting on start of the program.
     */
    public String showGreeting() {
        return "    Yo! I'm Duke"
                + "    What can I do for you?";
    }

    /**
     * Displays goodbye message on exit.
     */
    public String showGoodbye() {
        return "    Bye. Hope to see you again soon!";
    }

    /**
     * Displays all the items in the tasklist.
     * @param tasks refers to the tasklist
     */
    public String showTasksInList(TaskList tasks) {
        String output = "    Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.getListSize(); i++) {
            output += "    " + (i + 1) + ". " + tasks.getItemAtIndex(i) + "\n";
        }
        return output;
    }

    /**
     * Displays message on marking a task as done.
     * @param task refers to the task marked as done
     */
    public String showTaskIsDoneMsg(Task task) {
        return "    Nice! I've marked this task as done: \n" + "     " + task.toString();

    }

    /**
     * Displays message on deleting a task.
     * @param task refers to the task deleted.
     * @param taskListSize refers to the size of the tasklist after deletion.
     */
    public String showTaskDeletedMsg(Task task, int taskListSize) {
        return "    Noted. I've removed this task: \n"
                + "     " + task.toString() + "\n" + "    Now you have "
                + taskListSize + " tasks in the list.";
    }

    /**
     * Displays message on adding a task to the tasklist.
     * @param task refers to the task added to the list.
     * @param taskListSize refers to the size of the tasklist after the addition
     */
    public String showTaskAddedMsg(Task task, int taskListSize) {
        return "    Got it. I've added this task:\n"
                + "     " + task
                + "    Now you have " + taskListSize + " in the list.";
    }

    /**
     * Displays message on encoutering an error.
     * @param errMsg refers to the error message to be shown to the user.
     */
    public String showErrorMsg(String errMsg) {
        return "    " + errMsg;
    }

    /**
     * Displays tasks which contain the given keyword.
     * @param toFind refers to the keyword to be found.
     * @param tasks refers to the list of tasks.
     */
    public String showFoundItems(String toFind, TaskList tasks) {
        String output = "    Here are the matching tasks in your list: \n";
        int counter = 1;
        for (int i = 0; i < tasks.getListSize(); i++) {
            if (tasks.getItemAtIndex(i).getDescription().contains(toFind)) {
                output += "    " + counter + ". " + tasks.getItemAtIndex(i) + "\n";
                counter++;
            }
        }
        return output;
    }

}
