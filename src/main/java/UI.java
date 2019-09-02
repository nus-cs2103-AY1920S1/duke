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
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Displays greeting on start of the program.
     */
    public void showGreeting() {
        System.out.println(logo);
        showLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays goodbye message on exit.
     */
    public void showGoodbye() {
        System.out.println(line);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Displays all the items in the tasklist.
     * @param tasks refers to the tasklist
     */
    public void showTasksInList(TaskList tasks) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < tasks.getListSize(); i++) {
            System.out.println("    " + (i + 1) + ". " + tasks.getItemAtIndex(i));
        }
    }

    /**
     * Displays message on marking a task as done.
     * @param task refers to the task marked as done
     */
    public void showTaskIsDoneMsg(Task task) {
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("     " + task);
    }

    /**
     * Displays message on deleting a task.
     * @param task refers to the task deleted.
     * @param taskListSize refers to the size of the tasklist after deletion.
     */
    public void showTaskDeletedMsg(Task task, int taskListSize) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("     " + task);
        System.out.println("    Now you have " + taskListSize + " tasks in the list.");
    }

    /**
     * Displays message on adding a task to the tasklist.
     * @param task refers to the task added to the list.
     * @param taskListSize refers to the size of the tasklist after the addition
     */
    public void showTaskAddedMsg(Task task, int taskListSize) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("     " + task);
        System.out.println("    Now you have " + taskListSize + " in the list.");
    }

    /**
     * Displays message on encoutering an error.
     * @param errMsg refers to the error message to be shown to the user.
     */
    public void showErrorMsg(String errMsg) {
        System.out.println("    " + errMsg);
    }

    /**
     * Displays tasks which contain the given keyword.
     * @param toFind refers to the keyword to be found.
     * @param tasks refers to the list of tasks.
     */
    public void showFoundItems(String toFind, TaskList tasks) {
        System.out.println("    Here are the matching tasks in your list: ");
        int counter = 1;
        for (int i = 0; i < tasks.getListSize(); i++) {
            if (tasks.getItemAtIndex(i).getDescription().contains(toFind)) {
                System.out.println("    " + counter + ". " + tasks.getItemAtIndex(i));
                counter++;
            }
        }
    }

}
