import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    public UI() {
    }

    public String readCommand() {
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    public void printNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    public void printTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int number = 1;
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    public void printErrorMessage(String error) {
        System.out.println("OOPS!!! " + error );
    }

    public void printAddedMessage() {
        System.out.println("Got it. I've added this task:");
    }

    public void printDeletedMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    public void printListMessage() {
        System.out.println("Here are the tasks in your list: ");
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error, file not found");
    }
}
