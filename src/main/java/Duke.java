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

    static void deadline(String s) {
        String[] split = s.split(" /by ");
        Deadline dl = new Deadline(split[0], split[1]);
        tasks.add(dl);
        System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                dl.toString(), tasks.size()));
    }

    static void event(String s) {
        String[] split = s.split(" /at ");
        Event ev = new Event(split[0], split[1]);
        tasks.add(ev);
        System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                ev.toString(), tasks.size()));
    }

    static void todo(String s) {
        Todo td = new Todo(s);
        tasks.add(td);
        System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                td.toString(), tasks.size()));
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
            else if (splitCmmd[0].equals("deadline")) {
                deadline(cmmd.substring(9));
            }
            else if (splitCmmd[0].equals("event")) {
                event(cmmd.substring(6));
            }
            else {
                todo(cmmd.substring(5));
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
