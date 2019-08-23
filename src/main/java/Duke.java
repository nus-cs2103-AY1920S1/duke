import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<Task>();

    static void list(String s) throws DukeException {
        if (!s.isBlank()) {
            throw new DukeException("OOPS!!! No input is needed after 'list'");
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    static void done(String number) throws DukeException {
        try {
            Task doneTask = tasks.get(Integer.parseInt(number) - 1);
            doneTask.markDone();
            System.out.println("Nice! I've marked this task as done: \n" + doneTask.toString());
        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'done'",
                    tasks.size()));
        }
    }

    static void delete(String number) throws DukeException {
        try {
            Task deleted = tasks.remove(Integer.parseInt(number) - 1);
            System.out.println(String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                deleted.toString(), tasks.size()));
        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'delete'",
                    tasks.size()));
        }
    }

    static void deadline(String s) throws DukeException {
        try {
            String[] split = s.split(" /by ");
            if (split[0].isBlank() || split[1].isBlank()) {
                throw new Exception();
            }
            Deadline dl = new Deadline(split[0], split[1]);
            tasks.add(dl);
            System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                    dl.toString(), tasks.size()));
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input in this format: 'deadline <task> /by <time>'");
        }
    }

    static void event(String s) throws DukeException {
        try {
            String[] split = s.split(" /at ");
            if (split[0].isBlank() || split[1].isBlank()) {
                throw new Exception();
            }
            Event ev = new Event(split[0], split[1]);
            tasks.add(ev);
            System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                    ev.toString(), tasks.size()));
        }
        catch (Exception e) {
            throw new DukeException("OOPS!!! Please input in this format: 'event <task> /at <time>'");
        }
    }

    static void todo(String s) throws DukeException {
        if (s.isBlank()) {
            throw new DukeException("OOPS!!! Please add task description after 'todo'");
        }
        Todo td = new Todo(s);
        tasks.add(td);
        System.out.println(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                td.toString(), tasks.size()));
    }

    static void output(Scanner sc) {
        while (sc.hasNextLine()) {
            try {
                String cmmd = sc.next();
                String next = sc.nextLine().trim();
                if (cmmd.equals("bye")) {
                    break;
                } else if (cmmd.equals("list")) {
                    list(next);
                } else if (cmmd.equals("done")) {
                    done(next);
                } else if (cmmd.equals("delete")) {
                    delete(next);
                } else if (cmmd.equals("deadline")) {
                    deadline(next);
                } else if (cmmd.equals("event")) {
                    event(next);
                } else if (cmmd.equals("todo")) {
                    todo(next);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-()");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
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
