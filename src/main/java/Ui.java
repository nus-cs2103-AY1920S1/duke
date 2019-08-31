import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    void showLine() {
        System.out.println("____________________________________________________________");
    }

    String readCommand() {
        return scanner.nextLine();
    }

    void showLoadingError() {
        System.out.println("There is some error in the file");
    }

    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    void showLeaving() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void listTasks(TaskList tasks) {
        ArrayList<Task> list = tasks.getList();
        for (int i = 0; i < list.size(); i++) {
            String todo = String.format("%d. %s", i + 1, list.get(i).toString());
            System.out.println(todo);
        }
    }

    void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(task.toString());
    }

    void showDelete(Task task, int listSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", listSize));
    }

    void showAdd(Task task, int listSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", listSize));
    }
}
