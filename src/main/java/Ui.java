import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner sc;

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showMessage(String message) {
        System.out.println("---------------------------------------------------------------------");
        System.out.println(message);
        System.out.println("---------------------------------------------------------------------");
    }

    public void introduction() {
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }
    public void init() {
        this.sc = new Scanner(System.in);
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void closeScanner() {
        this.sc.close();
    }
    public void showLine() {
        System.out.println("---------------------------------------------------------------------");
    }
    public String readCommand() {
        return sc.nextLine();
    }
    public static void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasks.size(); i++) {
            Task curr_task = tasks.get(i-1);
            System.out.println(i + "." + curr_task);
        }
    }
    public static void printAdd(Task task, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    public static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + task);
    }
    public static void printDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}