import java.sql.SQLOutput;
import java.util.ArrayList;

public class Ui {
    private String border = "-------------------------------------";

    /**
     * Welcome message when program is executed.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Greeting message before user input is required.
     */
    public void greeting() {
        //Greetings before program
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?\n");
        sb.append(border + "\n");
        String greetings = sb.toString();
        System.out.println(greetings);
    }

    /**
     * Message when index given is out of range.
     * @return String message generated
     */
    public String indexError() {
        //error message for indexOutOfBound
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Invalid number. Number not listed. \n");
        sb.append(border + "\n");
        String indexError = sb.toString();
        System.out.println(indexError);
        return indexError;
    }

    /**
     * Message when no index is provided.
     * @return String message generated
     */
    public String emptyError() {
        //error message for empty input
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("No input detected. Please enter a number. \n");
        sb.append(border + "\n");
        String emptyError = sb.toString();
        System.out.println(emptyError);
        return emptyError;
    }

    /**
     * Ending message when user input bye.
     */
    public void conclusion() {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Bye. Hope to see you again soon!\n");
        sb.append(border + "\n");
        String conclude = sb.toString();
        System.out.println(conclude);
    }

    /**
     * Message notifying user when a task is marked done.
     * @param curr task that user wish to mark as done
     * @return String message notifying user
     */
    public String doneMessage(Task curr) {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(curr + "\n");
        sb.append(border + "\n");
        String done = sb.toString();
        System.out.println(done);
        return done;
    }

    /**
     * Message notifying user when a task is deleted.
     * @param curr Task that user wished to delete
     * @param size size of list after deletion
     * @return String message notifying user
     */
    public String deleteMessage(Task curr, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Noted! I've removed this task:\n");
        sb.append(curr + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        sb.append(border + "\n");
        String delete = sb.toString();
        System.out.println(delete);
        return delete;
    }

    /**
     * Listing all tasks stored in list.
     * @param items ArrayList containing current tasks
     */
    public void listTask(ArrayList<Task> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        //list out all items in arraylist items
        for (int i = 1; i <= items.size(); i++) {
            Task curr = items.get(i - 1);
            sb.append(i + "." + curr + "\n");
        }
        sb.append(border + "\n");
        System.out.println(sb.toString());
    }

    /**
     * generating message for individual task with border and indentation.
     * @param current current task
     * @param size size of list
     * @return message generated for selected task
     */
    public String generateMessage(Task current, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append(current + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        sb.append(border + "\n");
        return sb.toString();
    }
}
