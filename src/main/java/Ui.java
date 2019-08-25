import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scan;
    public String separationLine;

    public Ui() {
        scan = new Scanner(System.in);
        separationLine = "    ____________________________________________________________";
    }

    public void showWelcome() {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(separationLine + "\n" + logo + "\n     Hello! I'm Duke\n     What can I do for you?\n"
                + separationLine + "\n");
    }

    public String readLineInput() {
        return scan.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading from file. Initiating with empty Task list.");
    }

    public void printError(String message) {
        System.err.println(message);
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println(separationLine + "\n     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " + (tasks.indexOf(task) + 1) + "." + task.toString());
        }
        System.out.println(separationLine + "\n");
    }

    public void printDoneNotification(String doneTask) {
        System.out.println(separationLine + "\n     Nice! I've marked this task as done:\n       "
                + doneTask + "\n" + separationLine + "\n");
    }

    public void printAddNotification(String addTask, int listSize) {
        System.out.println(separationLine + "\n     Got it. I've added this task:\n       " + addTask
                + "\n     Now you have " + listSize + " tasks in the list." + "\n" + separationLine + "\n");
    }

    public void printDeleteNotification(String delTask, int listSize) {
        System.out.println(separationLine + "\n     Noted. I've removed this task:\n       " + delTask
                + "\n     Now you have " + listSize + " tasks in the list." + "\n" + separationLine + "\n");
    }

    public void printExitMessage() {
        System.out.println(separationLine +"\n     Bye. Hope to see you again soon!\n"+separationLine);
    }
}
