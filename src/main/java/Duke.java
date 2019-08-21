import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<Task>();

    static void list() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    static void done(String number) {
        Task doneTask = tasks.get(Integer.parseInt(number) - 1);
        doneTask.markDone();
        System.out.println("Nice! I've marked this task as done: \n" + doneTask.toString());
    }

    static void output(Scanner sc) {
        while (sc.hasNextLine()) {
            String cmmd = sc.nextLine();
            String[] splitCmmd = cmmd.split(" ");
            if (cmmd.equals("bye")) {
                break;
            }
            else if (cmmd.equals("list")) {
                list();
            }
            else if (splitCmmd[0].equals("done")) {
                done(splitCmmd[1]);
            }
            else {
                System.out.println("added: " + cmmd);
                tasks.add(new Task(cmmd));
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String opening = logo + "\nHello! I'm Duke\nWhat can I do for you?";
        String closing = "Bye. Hope to see you again soon!";

        System.out.println(opening);
        Scanner sc = new Scanner(System.in);
        output(sc);
        System.out.println(closing);
        System.exit(0);
    }
}
