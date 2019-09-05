import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import java.util.concurrent.TimeUnit;

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
     * Creates the greeting message from Duke in the 
     * appropriate dialogbox form.
     */
    public void showGreetingMessage(VBox dialogContainer, Image image) {
        String dukeGoodbyeText = "Hello! I'm Duke" + "\n" + "What can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeGoodbyeText, image)
        );
    }

    /**
     * Returns the goodbye message by duke upon closing.
     * @return The goodbye message.
     */
    public static String showGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a stringbuilder object with all the tasks in the
     * arraylist of tasks returned in a list form.
     * @param list The arraylist of tasks to be printed.
     * @return Stringbuilder object with tasks in string form as a list.
     */
    public StringBuilder printTasks(ArrayList<Task> list) {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:" + "\n");
        for (Task task : list) {
            sb.append(index + ". " + task.toString());
            sb.append("\n");
            index++;
        }
        return sb;
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
     * Returns a Stringbuilder object with the success message to be shown by Duke
     * upon successfully adding a task.
     * @param task
     * @return
     */
    public static StringBuilder addSuccess(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n");
        sb.append("\t" + task.toString());
        return sb;
    }

}