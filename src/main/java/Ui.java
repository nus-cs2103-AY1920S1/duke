import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible of taking in User inputs and to display information
 * after the User's command.
 */
public class Ui {
    Scanner scanner;

    /**
     * Instantiate a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Display a welcome message for the User.
     */
    public void showWelcome() {
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
    public String entercommand() {
        return scanner.nextLine();
    }

    /**
     * Display the list of tasks to the User.
     * @param tasklist The tasks list.
     */
    public void showlist(ArrayList<Task> tasklist) {
        System.out.println("____________________________________________________________");
        for (int x = 0; x < tasklist.size(); x++) {
            System.out.println(x + 1 + ". " + tasklist.get(x));
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Display the error when there is an empty .txt file.
     * @param e
     */
    public void showloadingerror(DukeException e) {
        System.out.println(e);
    }

    /**
     * Display message when a task is added.
     * @param tasklist The tasks list.
     */
    public void addedmessage(ArrayList<Task> tasklist) {
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size()-1)
                + "\n Now you have " + tasklist.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    /**
     * Display message when a task is deleted.
     * @param tasklist The tasks list.
     * @param deleted The task deleted.
     */
    public void deletedmessage(ArrayList<Task> tasklist, String deleted) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task: \n"
                + "  " + deleted + "Now you have " + tasklist.size()
                + " tasks in the list "
                + "\n____________________________________________________________");
    }

    /**
     * Display message and also the change in status icon to show the task is done.
     * @param taskdonestr String of the task done.
     */
    public void donemessage (String taskdonestr) {
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this tasked as done:\n"
                + taskdonestr
                + "\n____________________________________________________________");
    }

    /**
     * Display a goodbye message when User exits the Duke application.
     */
    public void byemessage() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                + "____________________________________________________________");
    }

    /**
     * Display the error message to User when User inputs an invalid command.
     * @param errormsg
     */
    public void illegalcommanderror(IllegalCommandException errormsg) {
        System.err.println(errormsg);
    }
}
