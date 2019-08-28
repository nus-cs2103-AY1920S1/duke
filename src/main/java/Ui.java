import java.util.Scanner;

public class Ui {
    Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    public String readCommand() {
        String command = input.nextLine().trim(); //trim leading/trailing whitespace
        return command;
    }

    public void showWelcome() {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);
    }

    public void showLoadingError() {
        System.out.println("File cannot be loaded");
    }

    public void showAddTask(Task newTask, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void showList(TaskList tasks) {
        tasks.printAllTasks();
    }

    public void showFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public void showDeletedTask(Task deletedTask, int numTasks) {
        System.out.println("Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + String.format("\nNow you have %d tasks in the list.", numTasks));
    }

    public void promptList() {
        System.out.println("OOPS!!! That task is not on the list, please check the list again by calling 'list'.");
    }

    public void promptDoneCompletion() {
        System.out.println("Which task on the list have you completed? (Eg 'done 2')");
    }

    public void promptDoneFormat() {
        System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'done 2')");
    }

    public void promptDeleteCompletion() {
        System.out.println("Which task on the list would you like to delete? (Eg. 'delete 2')");
    }

    public void promptDeleteFormat() {
        System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'delete 2')");
    }
}