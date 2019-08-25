import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello I'm Duke\n" + "What can I do for you?\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void showList(LinkedList<Task> tasks) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            System.out.println(i + ". " + t);
            i++;
        }
    }
    public void showDelete(Task t, int i) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        System.out.println("Now you have " + i + " tasks in the list.");
    }

    public void showComplete(Task t) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(t);
    }

    public void showAddedTask(Task t, int i) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + i + " tasks in the list.");
    }
    public void showLine() {
        System.out.println("-----------------------------------------------------------------------------------\n");
    }
}
