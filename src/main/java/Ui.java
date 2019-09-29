import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Ui {
    private static final String DIVIDER = "________________________________________";
    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("    " + DIVIDER);
    }

    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println(DIVIDER);
    }

    public void showLoadingError() {
        System.out.println("No previous data found!");
    }

    public String readCommand() {
        String command = sc.nextLine();

        return command;
    }

    public void printList(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTaskList();

        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            System.out.println("    " + index + "." + taskList.get(i));
        }
    }

    public void doneTaskMessage(Task task) {
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void addTaskMessage(TaskList taskList, Task task) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void removeTaskMessage(TaskList taskList, Task task) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    " + task);
        System.out.println("    Now you have " + taskList.getSize() + " tasks in the list.");
    }

    public void findTaskMessage(TaskList taskList) {
        System.out.println("    Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int index = i + 1;
            System.out.println("    " + index + "." + taskList.getTask(i));
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void byeMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
