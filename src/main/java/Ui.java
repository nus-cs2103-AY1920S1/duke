import java.util.Scanner;

public class Ui {

    private String lastCommand;

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public static void println(String string) {
        System.out.println(string);
    }

    public String readCommand() {
        lastCommand = scanner.nextLine();
        return lastCommand;
    }

    public void showError(String string) {
        printLine(string);
    }

    public void notifyTaskAdded(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", listSize);
    }

    public void notifyTaskDeleted(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", listSize);
    }

    public void notifyMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public void print(String string) {
        System.out.print(string);
    }

    public void printLine(String string) {
        System.out.println(string);
    }

    public void greetings() {
        System.out.println("Hello! I am Jeong's Slave");
        System.out.println("What can I do for you?");
    }
}
