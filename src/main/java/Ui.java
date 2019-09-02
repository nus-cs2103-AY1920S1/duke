import java.util.ArrayList;

/**
 * The Ui class handles the interaction with the users, mainly 
 * displaying the greeting and goodbye messages along with customised
 * error messages.
 */

public class Ui {

    /**
     * Creates a new Ui object.
     */
    public Ui() {

    }

    /**
     * Prints the greeting message from Duke.
     */
    public void showGreetingMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message from Duke.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints all the tasks loaded from the local file and 
     * new ones created by users as they are stored in the array list.
     * @param list The array list from which the tasks are being read.
     */
    public void printTasks(ArrayList<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + "." + task.toString());
            i += 1;
        }
    }

    /**
     * Validates whether a command entered by the user is legitimate and something
     * Duke can act upon.
     * @param detail Details of the user command broken down into chunks.
     * @throws DukeException When the command is either incomplete or an invalid command.
     */
    public static void validateDetail(String[] detail) throws DukeException {
        if (detail.length == 0) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (! detail[0].equals("todo") && ! detail[0].equals("event") && ! detail[0].equals("deadline")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (detail.length == 1) {
            throw new DukeException("OOPS!!! The description of a " + detail[0] + " cannot be empty.");
        }
    }

    /**
     * Checks whether the details being used to create a particular deadline are sufficient. 
     * @param detail The details entered by the user to create the deadline.
     * @throws DukeException When insufficient data is entered by the user to create the deadline.
     */
    public static void validateDeadlineDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("OOPS!!! The due date of a deadline must be specified.");
        }
    }

    /**
     * Checks whether the details being used to create a particular event are sufficient. 
     * @param detail The details entered by the user to create the event.
     * @throws DukeException When insufficient data is entered by the user to create the event.
     */
    public static void validateEventDetails(String[] detail) throws DukeException {
        if (detail.length != 2) {
            throw new DukeException("OOPS!!! The timeline of an event must be specified.");
        }
    }

    /**
     * Success message to be shown when a task is successfully created and added
     * into the file and list of tasks by Duke.
     * @param task The task which has been added successfully.
     */
    public static void addSuccess(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
    }

}