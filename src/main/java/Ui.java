import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showInitialListMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You do not have any stored tasks");
        } else {
            System.out.println("This is your current list of tasks");
            for (int i = 0; i < tasks.size(); i++) {
                int currentItemNumber = i + 1;
                Task currentTask = tasks.get(i);
                System.out.println(currentItemNumber + "." + currentTask);
            }
        }
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
