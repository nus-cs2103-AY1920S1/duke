import java.util.Scanner;

/** Class to handle UI for the application. */
class UI {

    // scanner to take user input
    private Scanner sc;
    private static final String welcomeStr = "Hello! I'm Duke :)\n     What can I do for you?";
    private static final String endStr = "Bye. Hope to see you again soon!";

    // class constructor
    public UI() {
        sc = new Scanner(System.in);
    }

    // print welcome string
    public void printWelcome() {
        prettyPrint(welcomeStr);
    }

    // print exit string
    public void printExit() {
        prettyPrint(endStr);
    }

    // method to take input from user
    public String takeInput() {
        return sc.nextLine();
    }

    // method to print during task addition
    public void showTaskAddition(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("       " + task + "\n");
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        prettyPrint(sb.toString());
    }

    // method to show task deletion
    public void showTaskDeletion(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(String.format("     %s\n", task.toString()));
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        prettyPrint(sb.toString());
    }

    // method to show marking task as done
    public void showTaskMarkedDone(Task task) {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(String.format("     %s", task.toString()));
        prettyPrint(sb.toString());
    }

    // method to list tasks in TaskList
    public void showTaskList(TaskList tl) {
        prettyPrint(tl.listTasks());
    }

    // method to print error
    public void printError(DukeException e) {
        prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
    }

    // pretty print a string
    private static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}
