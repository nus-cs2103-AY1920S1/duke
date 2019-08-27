import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final String horizontalLine = "____________________________________________________________";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        System.out.print("Enter command: ");
        String fullCommand = in.nextLine();
        return fullCommand;
    }

    public void showLine() {
        System.out.println(horizontalLine);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void list(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        int count = 0;
        for (Task task: list) {
            count++;
            System.out.println(count + ". " + task.toString());
        }
    }

    public void addTask(Task task, int numberOfTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void removeTask(Task task, int numberOfTasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }
}