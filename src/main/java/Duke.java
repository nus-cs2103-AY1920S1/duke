import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String INDENT_COMMENT = "    ";
    public static final String INDENT_TASK = "      ";
    public static List<Task> task = new ArrayList<>();
    public static int itemNo = 0;

    public static void printLine() {
        System.out.println("   ________________________________________________________________________");
    }

    public static void printGreet() {
        printLine();
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(INDENT_COMMENT + "Hello! I'm Duke");
        System.out.println(INDENT_COMMENT + "What can I do for you?");
        printLine();
        System.out.println("");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String line = input.nextLine();

            String command = "";
            String rest = "";

            if (!line.contains(" ")) {
                command = line;
            } else {
                String[] data = line.split(" ", 2);
                command = data[0];
                rest = data[1];
            }

            try {
                switch (command) {
                    case "list":
                        commandList();
                        break;

                    case "done":
                        commandDone(rest);
                        break;

                    case "todo":
                        commandTodo(rest);
                        break;

                    case "deadline":
                        commandDeadline(rest);
                        break;

                    case "event":
                        commandEvent(rest);
                        break;

                    case "bye":
                        bye();
                        break;

                    case "delete":
                        delete(rest);
                        break;

                    default:
                        throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                printLine();
                System.out.println(ex.getMessage());
                printLine();
                System.out.println("");
            }
        }
    }

    public static void commandList() throws DukeException {
        if (itemNo == 0) {
            throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
        }
        int index = 1;
        printLine();
        System.out.println(INDENT_COMMENT + "Here are the tasks in your list:");
        for (int i = 0; i < task.size(); i++) {
            System.out.println("    " + index++ + "." + task.get(i));
        }
        printLine();
        System.out.println("");
    }

    public static void commandDone(String data) throws DukeException {
        try {

            if (data.isEmpty()) {
                if (itemNo == 0) {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "Index of task are needed.");
                }
            }

            int item = Integer.parseInt(data);

            if (item > itemNo) {
                if (itemNo == 0) {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "Number enter can only be less than or equal number of task.");
                }
            }

            printLine();
            System.out.println(INDENT_COMMENT + "Nice! I've marked this task as done:");
            Task t = task.get(--item);
            t.markAsDone();
            System.out.println(INDENT_TASK + t);
            printLine();
            System.out.println("");
        } catch (NumberFormatException ex) {
            printLine();
            System.out.println(INDENT_COMMENT + "\u2639 OOPS !!! " + "Only Integer is allowed after done.");
            printLine();
            System.out.println("");
        }
    }

    public static void commandTodo(String data) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of a todo cannot be empty.");
        }

        task.add(new Todo(data));
        printLine();
        System.out.println(INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(INDENT_TASK + task.get(itemNo));
        itemNo++;
        System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
        printLine();
        System.out.println("");
    }

    public static void commandDeadline(String data) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of a deadline cannot be empty.");
        }

        String[] result = data.split("/by");

        if (result.length <= 1) {
            throw new DukeException(INDENT_COMMENT +"\u2639 OOPS !!! " + "Deadline is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();
        task.add(new Deadline(achieve, timeline));
        printLine();
        System.out.println(INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(INDENT_TASK + task.get(itemNo));
        itemNo++;
        System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
        printLine();
        System.out.println("");
    }

    public static void commandEvent(String data) throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException(INDENT_COMMENT +"\u2639 OOPS !!! " + "The description of an event cannot be empty.");
        }

        String[] result = data.split("/at");

        if (result.length <= 1) {
            throw new DukeException(INDENT_COMMENT +"\u2639 OOPS !!! " + "Event time is needed.");
        }

        String achieve = result[0].trim();
        String timeline = result[1].trim();
        task.add(new Event(achieve, timeline));
        printLine();
        System.out.println(INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(INDENT_TASK + task.get(itemNo));
        itemNo++;
        System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
        printLine();
        System.out.println("");
    }

    public static void bye() {
        printLine();
        System.out.println(INDENT_COMMENT + "Bye. Hope to see you again soon!");
        printLine();
        System.out.println("");
        System.exit(0);
    }

    public static void delete(String data) throws DukeException {
        try {
            if (data.isEmpty()) {
                if (itemNo == 0) {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "Index of task are needed.");
                }
            }

            int item = Integer.parseInt(data);

            if (item > itemNo) {
                if (itemNo == 0) {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "The task list are currently empty.");
                } else {
                    throw new DukeException(INDENT_COMMENT + "\u2639 OOPS !!! " + "Number enter can only be less than or equal number of task.");
                }
            }
            printLine();
            System.out.println(INDENT_COMMENT + "Noted. I've removed this task: ");
            System.out.println(INDENT_TASK + task.remove(--item));
            itemNo--;
            System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
            printLine();
            System.out.println("");
        } catch (NumberFormatException ex) {
            printLine();
            System.out.println(INDENT_COMMENT + "\u2639 OOPS !!! " + "Only Integer is allowed after done.");
            printLine();
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        printGreet();
        readInput();
    }
}