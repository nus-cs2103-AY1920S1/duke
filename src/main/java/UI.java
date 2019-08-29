import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor
     */
    public UI() {
    }

    /**
     * Returns the user string that is inputted.
     * @return user string.
     */
    public String readCommand() {
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    /**
     * Prints out the number of tasks in the taskList
     * @param taskList
     */
    public void printNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
    }

    /**
     * Prints out task t
     * @param t
     */
    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    /**
     * Prints out all the tasks in taskList
     * @param taskList
     */
    public void printTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int number = 1;
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    /**
     * Prints out the error message
     * @param error
     */
    public void printErrorMessage(String error) {
        System.out.println("OOPS!!! " + error );
    }

    /**
     * Prints out the pre-message for add
     */
    public void printAddedMessage() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints out the pre-message for delete
     */
    public void printDeletedMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints out the pre-message for done
     */
    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * Prints out the pre-message for list
     */
    public void printListMessage() {
        System.out.println("Here are the tasks in your list: ");
    }

    /**
     * Prints out the greetings
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints out the bye message
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the loading message
     */
    public void showLoadingError() {
        System.out.println("Error, file not found");
    }
}
