
import java.util.ArrayList;

public class Ui {

    public static final String BORDER = "-------------------------------------";

    /**
     * Welcome message when program is executed.
     */
    public String welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return  logo;
    }

    /**
     * Greeting message before user input is required.
     */
    public String greeting() {
        //Greetings before program
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?\n");
        sb.append(BORDER + "\n");
        String greetings = sb.toString();
        System.out.println(greetings);
        return greetings;
    }

    /**
     * Message when index given is out of range.
     * @return String message generated
     */
    public String indexError() {
        //error message for indexOutOfBound
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Invalid number. Number not listed. \n");
        sb.append(BORDER + "\n");
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
        sb.append(BORDER + "\n");
        sb.append("No input detected. Please enter a number. \n");
        sb.append(BORDER + "\n");
        String emptyError = sb.toString();
        System.out.println(emptyError);
        return emptyError;
    }

    /**
     * Message when input for event is invalid.
     * @return String message generated
     */
    public String eventError() {
        //error message for empty input
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Invalid Event's arguments \n");
        sb.append("Please follow the format below: \n");
        sb.append("event {description} /at {dd/mm/yyyy hh:mm - hh:mm}\n");
        sb.append(BORDER + "\n");
        String eventMessage = sb.toString();
        System.out.println(eventMessage);
        return eventMessage;
    }

    /**
     * Message when input for deadline is invalid.
     * @return String message generated
     */
    public String deadlineError() {
        //error message for empty input
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Invalid Deadline's arguments \n");
        sb.append("Please follow the format below: \n");
        sb.append("deadline {description} /by {dd/mm/yyyy hh:mm}\n");
        sb.append(BORDER + "\n");
        String deadlineMessage = sb.toString();
        System.out.println(deadlineMessage);
        return deadlineMessage;
    }

    /**
     * Message when no details is provided for todo.
     * @return String message generated
     */
    public String todoError() {
        //error message for empty input
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Todo must have valid description\n");
        sb.append(BORDER + "\n");
        String todoMessage = sb.toString();
        System.out.println(todoMessage);
        return todoMessage;
    }

    /**
     * Message when user does not input any term that is valid for system.
     * @return String message generated
     */
    public String invalidInput() {
        //error message for empty input
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Unable to understand. Invalid Input. \n");
        sb.append(BORDER + "\n");
        String invalidMessage = sb.toString();
        System.out.println(invalidMessage);
        return invalidMessage;
    }

    /**
     * Ending message when user input bye.
     */
    public String conclusion() {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Bye. Hope to see you again soon!\n");
        sb.append(BORDER + "\n");
        String conclude = sb.toString();
        System.out.println(conclude);
        return conclude;
    }

    /**
     * Message notifying user when a task is marked done.
     * @param curr task that user wish to mark as done
     * @return String message notifying user
     */
    public String doneMessage(Task curr) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(curr + "\n");
        sb.append(BORDER + "\n");
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
        sb.append(BORDER + "\n");
        sb.append("Noted! I've removed this task:\n");
        sb.append(curr + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        sb.append(BORDER + "\n");
        String delete = sb.toString();
        System.out.println(delete);
        return delete;
    }

    /**
     * Listing all tasks stored in list.
     * @param items ArrayList containing current tasks
     * @return String message collating all tasks
     */
    public String listTask(ArrayList<Task> items) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        //list out all items in arraylist items
        for (int i = 1; i <= items.size(); i++) {
            Task curr = items.get(i - 1);
            sb.append(i + "." + curr + "\n");
        }
        sb.append(BORDER + "\n");
        String output = sb.toString();
        System.out.println(sb.toString());
        return output;
    }

    /**
     * Listing all tasks stored in list that matched with search term.
     * @param items ArrayList containing current tasks
     * @param key search term provided by user
     */
    public String findTask(ArrayList<Task> items, String key) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Here are the matching tasks in your list: \n");
        //list out all items in arraylist items that matched with user input
        for (int i = 1; i <= items.size(); i++) {
            Task curr = items.get(i - 1);
            if (curr.getDescription().contains(key)) {
                sb.append(i + "." + curr + "\n");
            }
        }
        sb.append(BORDER + "\n");
        String output = sb.toString();
        System.out.println(output);
        return output;
    }

    /**
     * generating message for individual task with border and indentation.
     * @param current current task
     * @param size size of list
     * @return message generated for selected task
     */
    public String generateMessage(Task current, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append(current + "\n");
        sb.append("Now you have " + size + " tasks in the list.\n");
        sb.append(BORDER + "\n");
        return sb.toString();
    }

    /**
     * generating message for task which had already been added to list.
     * @param current task user is trying to create/add
     * @return message indicating the existed task
     */
    public String duplicateMessage(Task current) {
        StringBuilder sb = new StringBuilder();
        sb.append(BORDER + "\n");
        sb.append("This task had already been added: \n");
        sb.append(current + "\n");
        sb.append(BORDER + "\n");
        return sb.toString();
    }
}
