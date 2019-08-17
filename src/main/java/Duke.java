import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "\t____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        dukePrint("Hello from\n" + logo + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (runCommand(sc.nextLine())) {

        }
    }

    private static void dukePrint(String s) {
        System.out.println(line);
        String[] arr = s.split("\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("\t " + arr[i]);
        }
        System.out.println(line + "\n");
    }

    private static boolean runCommand(String s) {
        String[] arg = s.split(" ", 2);
        String result = "";
        try {
            switch (arg[0]) {
                case "bye":
                    dukePrint("Bye. Hope to see you again soon!");
                    return false;
                case "list":
                    result = "Here are the tasks in your list:";
                    for (int i = 0; i < tasks.size(); i++) {
                        result += "\n" + (i + 1) + ". " + tasks.get(i);
                    }
                    dukePrint(result);
                    break;
                case "done":
                    if (arg.length < 2 || arg[1].trim().length() < 1) {
                        throw new DukeEmptyDescException(arg[0]);
                    }
                    int idx;
                    try {

                        idx = Integer.parseInt(arg[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidTaskException(arg[1]);
                    }
                    if (idx < 0 || idx >= tasks.size()) {
                        throw new DukeInvalidTaskException(arg[1]);
                    }
                    tasks.get(idx).markAsDone();
                    result = "Nice! I've marked this task as done:\n  ";
                    dukePrint(result + tasks.get(idx));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (arg.length < 2 || arg[1].trim().length() < 1) {
                        throw new DukeEmptyDescException(arg[0]);
                    }
                    Task t = new Task(arg[1]);
                    if (arg[0].equals("todo")) {
                        t = new Todo(arg[1]);
                    } else if (arg[0].equals("deadline")) {
                        String[] temp = arg[1].split(" /by ", 2);
                        if (temp.length < 2 || temp[1].trim().length() < 1) {
                            throw new DukeEmptyDateException(arg[0]);
                        }
                        t = new Deadline(temp[0], temp[1]);
                    } else if (arg[0].equals("event")) {
                        String[] temp = arg[1].split(" /at ", 2);
                        if (temp.length < 2 || temp[1].trim().length() < 1) {
                            throw new DukeEmptyDateException(arg[0]);
                        }
                        t = new Event(temp[0], temp[1]);
                    }
                    tasks.add(t);
                    result = "Got it. I've added this task:\n";
                    result += "  " + t + "\n";
                    result += "Now you have " + tasks.size() + " tasks in the list.";
                    dukePrint(result);
                    break;
                default:
                    throw new DukeException();
            }
        } catch (DukeException e) {
            dukePrint(e.toString());
        } finally {
            return true;
        }
    }
}
