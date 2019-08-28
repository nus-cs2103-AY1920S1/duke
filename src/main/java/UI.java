import java.util.List;
import java.util.Scanner;

public class UI {
    private Scanner sc;

    public UI() {
        sc = new Scanner(System.in);
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void printList(TaskList taskList) {
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

    public void echoAddedTask(String input, int taskListSize) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                String.format("       %s \n", input) +
                String.format("     Now you have %d tasks in the list. \n", taskListSize) +
                "    ____________________________________________________________");
    }

    public void echoCompletedTask(Task taskToComplete){
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       %s\n" +
                        "    ____________________________________________________________",
                taskToComplete.toString()));
    }

    public void echoDeletedTask(Task taskToDelete, int taskListSize) {
        System.out.println(String.format("    ____________________________________________________________\n" +
                        "     Noted. I've removed this task: \n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________",
                taskToDelete.toString(), taskListSize));
    }

    public void echoException(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void exit() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
