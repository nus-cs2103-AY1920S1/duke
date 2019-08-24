import java.sql.SQLOutput;
import java.util.ArrayList;

public class Ui {
    private String border = "-------------------------------------";

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

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

    public void conclusion() {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Bye. Hope to see you again soon!\n");
        sb.append(border + "\n");
        String conclude = sb.toString();
        System.out.println(conclude);
    }

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
     * Message generator for task with indentation and border.
     *
     *
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
