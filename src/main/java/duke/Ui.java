package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface.
 * A <code>Ui</code> object handles interaction between the user and the chatbot by reading and outputting messages.
 */
public class Ui {
    protected Scanner userInput = new Scanner(System.in);

    /**
     * Prints the Duke logo and greets the user for the first time the program is run.
     */
    public void greetUser() {
        String logo =
                "      ___             _____       ______       _____\n"
                + "   /            \\       /   ____|     |    ___    \\    |    ___|\n"
                + "  /    /    \\    \\     |    |              |   |___|   /    |   |___ \n"
                + " /    / __ \\    \\   |    |____     |    |      \\   \\    |   |___ \n"
                + "/ _ /         \\ _ \\  \\______|   | _ |        \\_ \\  |_____|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Pooh\nWhat can I do for you?");
    }

    /**
     * Prints the bye message when the user exits the program.
     */
    public void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Reads user input.
     * @return User input.
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Prints the input message in the console.
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints exception messages.
     * @param exception Exceptions that occur after processing the user input command.
     */
    public void printException(DukeException exception) {
        String message = exception.getMessage();
        System.out.println(message);
    }

    /**
     * Prints the message to inform user of a successful deletion.
     * @param task Task that has been deleted.
     * @param numberOfTasks Number of tasks left in the list.
     */
    public void printDeleteMessage(Task task, int numberOfTasks) {
        printMessage("Noted. I've removed this task: ");
        printMessage(task.toString());
        printMessage("Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.");
    }

    /**
     * Prints the message to inform user that a task has been successfully marked as done.
     * @param task Task that has been marked as done.
     */
    public void printDoneMessage(Task task) {
        printMessage("Nice! I've marked this task as done: ");
        printMessage(task.toString());
    }

    /**
     * Prints the message to inform user of a successful addition of a task to the list.
     * @param task Task that has been added.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public void printAddedMessage(Task task, int numberOfTasks) {
        printMessage("Got it. I've added this task: ");
        printMessage(task.toString());
        printMessage("Now you have " + numberOfTasks + (numberOfTasks == 1 ? " task" : " tasks") + " in the list.");
    }

    public void printTagMessage(Task task, String[] tags) {
        printMessage("Got it. I have tagged this task: ");
        printMessage(task.toString());
    }

    /**
     * Prints the error when the information in storage could not be loaded.
     */
    public void showLoadingError() {
        printMessage("OOPS!!! Something went wrong. I could not load the file :(");
    }

    /**
     * Prints the tasks matching the input tag.
     * @param tag Tag input by the user.
     * @param tasks Tasks that match the tag.
     */
    public void printTasksMatchingTag(String tag, ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            printMessage("There are no tasks that are #" + tag);
        } else {
            printMessage("These tasks are #" + tag + "!");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                printMessage(task.toString());
            }
        }
    }
}
