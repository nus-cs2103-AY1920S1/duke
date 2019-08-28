import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner sc;

    /***
     * Class constructor.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /***
     * Greet user.
     */
    public static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    /***
     * Read user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /***
     * Echo TaskList to user.
     * @param taskList TaskList to be echoed
     */
    public void echoList(TaskList taskList) {
        System.out.println("    ____________________________________________________________");
        if (taskList.getSize() == 0)
            System.out.println("     *** List is Empty ***");
        else {
            System.out.println("     Here are the tasks in your list: ");
            for (int i = 0; i < taskList.getSize(); i++) {
                System.out.println(String.format("     %d.%s",
                        i + 1, taskList.getTask(i).toString()));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /***
     * Echo matching Tasks to user.
     * @param matchingTasks Matching Tasks
     */
    public void echoMatchingTasks(List<Task> matchingTasks) {
        System.out.println("    ____________________________________________________________");
        if (matchingTasks.size() == 0)
            System.out.println("     *** List is Empty ***");
        else {
            System.out.println("     Here are the matching tasks in your list: ");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(String.format("     %d.%s",
                        i + 1, matchingTasks.get(i).toString()));
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /***
     * Echo added Task to user.
     * @param taskToAdd Added Task
     * @param taskListSize
     */
    public void echoAddedTask(Task taskToAdd, int taskListSize) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                String.format("       %s \n", taskToAdd.toString()) +
                String.format("     Now you have %d tasks in the list. \n", taskListSize) +
                "    ____________________________________________________________");
    }

    /***
     * Echo completed Task to user.
     * @param taskToComplete Completed Task
     */
    public void echoCompletedTask(Task taskToComplete){
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       %s\n" +
                        "    ____________________________________________________________",
                taskToComplete.toString()));
    }

    /***
     * Echo deleted Task to user.
     * @param taskToDelete Deleted Task
     * @param taskListSize Number of remaining Tasks in TaskList
     */
    public void echoDeletedTask(Task taskToDelete, int taskListSize) {
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Noted. I've removed this task: \n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________",
                taskToDelete.toString(), taskListSize));
    }

    /***
     * Echo Exception to user.
     * @param e Exception to be echoed
     */
    public void echoException(Exception e) {
        System.out.println(e.getMessage());
    }

    /***
     * Echo message to user.
     * @param msg Message to be echoed
     */
    public void echoMessage(String msg) {
        System.out.println(msg);
    }

    /***
     * Show exit message to user.
     */
    public static void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
