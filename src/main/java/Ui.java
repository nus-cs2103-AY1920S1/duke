import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private String LINES = "\n" + "____________________________________________________________" + "\n";

    public Ui() {

    }

    public void showWelcome() {
        System.out.println(display("Hello! I'm Duke\nWhat can I do for you?"));
    }

    private String display(String text) {
        return LINES + text + LINES;
    }

    public void showLoadingError() {
        System.out.println("Cannot load previous task");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINES);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printList(TaskList t) {
        try {
            System.out.println("Here are the task in your list:");
            for (int i = 0; i < t.list.size() - 1; i++) {
                System.out.println(i + 1 + "." + t.list.get(i));
            }
            System.out.print(t.list.size() + "." + t.list.get(t.list.size() - 1));
        } catch (IndexOutOfBoundsException e) {
            System.out.print("Your list is empty.");
        }
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddedTask(Task t, int n) {
        System.out.println("Got it. I've added this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    public void showDeletedTask(Task t, int n) {
        System.out.println("Noted. I've removed this task:\n  "
                + t
                + "\nNow you have " + n + " tasks in the list.");
    }

    public void showDoneTask(Task t) {
        System.out.println("Nice! I've marked this task as done:\n"
                + t);
    }
}
