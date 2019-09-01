import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible of taking in User inputs and to display information
 * after the User's command.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Instantiate a Ui object.
     */
    protected Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display a welcome message for the User.
     */
    protected void showWelcome() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        String welcome = "____________________________________________________________\n"
                + logo
                + "\n Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";


        System.out.println(welcome);
    }

    /**
     * Prompt the user to enter command.
     * @return the Users command.
     */
    protected String enterCommand() {
        return scanner.nextLine();
    }

    /**
     * Display the list of tasks to the User.
     * @param taskList The tasks list.
     */
    protected void showList(ArrayList<Task> taskList) {
        System.out.println("____________________________________________________________");
        for (int x = 0; x < taskList.size(); x++) {
            System.out.println(x + 1 + ". " + taskList.get(x));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the error when there is an empty .txt file.
     * @param e
     */
    protected void showLoadingError(DukeException e) {
        System.err.println(e);
    }

    /**
     * Display message when a task is added.
     * @param taskList The tasks list.
     */
    protected void getAddedMessage(ArrayList<Task> taskList) {
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n" + "   " + taskList.get(taskList.size()-1)
                + "\n Now you have " + taskList.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    /**
     * Display message when a task is deleted.
     * @param taskList The tasks list.
     * @param deleted The task deleted.
     */
    protected void getDeletedMessage(ArrayList<Task> taskList, String deleted) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task: \n"
                + "  " + deleted + "Now you have " + taskList.size()
                + " tasks in the list "
                + "\n____________________________________________________________");
    }

    /**
     * Display message and also the change in status icon to show the task is done.
     * @param taskDoneStr String of the task done.
     */
    protected void getDoneMessage(String taskDoneStr) {
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this tasked as done:\n"
                + taskDoneStr
                + "\n____________________________________________________________");
    }

    /**
     * Display a goodbye message when User exits the Duke application.
     */
    protected void getByeMessage() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                + "____________________________________________________________");
    }

    /**
     * Display the error message to User when User inputs an invalid command.
     * @param errorMsg
     */
    protected void getIllegalCommandError(IllegalCommandException errorMsg) {
        System.err.println(errorMsg);
    }

    protected void showFoundMessage(ArrayList<Task> foundTasklist) {
        System.out.println("____________________________________________________________");
        for (int x = 0; x < foundTasklist.size(); x++) {
            System.out.println(x + 1 + ". " + foundTasklist.get(x));
        }
        System.out.println("____________________________________________________________");
    }

}
