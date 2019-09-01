import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    protected Ui() {
        scanner = new Scanner(System.in);
    }

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

    protected String enterCommand() {
        return scanner.nextLine();
    }

    protected void showList(ArrayList<Task> taskList) {
        System.out.println("____________________________________________________________");
        for (int x = 0; x < taskList.size(); x++) {
            System.out.println(x + 1 + ". " + taskList.get(x));
        }
        System.out.println("____________________________________________________________");
    }

    protected void showLoadingError(DukeException e) {
        System.err.println(e);
    }

    protected void getAddedMessage(ArrayList<Task> taskList) {
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n" + "   " + taskList.get(taskList.size()-1)
                + "\n Now you have " + taskList.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    protected void getDeletedMessage(ArrayList<Task> taskList, String deleted) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task: \n"
                + "  " + deleted + "Now you have " + taskList.size()
                + " tasks in the list "
                + "\n____________________________________________________________");
    }

    protected void getDoneMessage(String taskDoneStr) {
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this tasked as done:\n"
                + taskDoneStr
                + "\n____________________________________________________________");
    }

    protected void getByeMessage() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                + "____________________________________________________________");
    }

    protected void getIllegalCommandError(IllegalCommandException errorMsg) {
        System.err.println(errorMsg);
    }
}
