import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

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

    public String entercommand() {
        return scanner.nextLine();
    }

    public void showlist(ArrayList<Task> tasklist) {
        System.out.println("____________________________________________________________");
        for (int x = 0; x < tasklist.size(); x++) {
            System.out.println(x + 1 + ". " + tasklist.get(x));
        }
        System.out.println("____________________________________________________________");
    }

    public void showloadingerror(DukeException e) {
        System.out.println(e);
    }

    public void addedmessage(ArrayList<Task> tasklist) {
        System.out.println("____________________________________________________________\n"
                + " Got it. I've added this task:\n" + "   " + tasklist.get(tasklist.size()-1)
                + "\n Now you have " + tasklist.size() + " tasks in the list."
                + "\n____________________________________________________________");
    }

    public void deletedmessage(ArrayList<Task> tasklist, String deleted) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task: \n"
                + "  " + deleted + "Now you have " + tasklist.size()
                + " tasks in the list "
                + "\n____________________________________________________________");
    }

    public void donemessage (String taskdonestr) {
        System.out.println("____________________________________________________________\n"
                + " Nice! I've marked this tasked as done:\n"
                + taskdonestr
                + "\n____________________________________________________________");
    }

    public void byemessage() {
        System.out.println("____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n\n"
                + "____________________________________________________________");
    }

    public void illegalcommanderror(IllegalCommandException errormsg) {
        System.err.println(errormsg);
    }
}
