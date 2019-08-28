import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /** Indentation for comment */
    public static final String INDENT_COMMENT = "    ";

    /** Indentation for task description */
    public static final String INDENT_TASK = "      ";

    /** Array list of task */
    public static List<Task> task = new ArrayList<>();

    /** Total number of task */
    public static int itemNo = 0;

    /**
     * Print the horizontal line.
     */
    public static void printLine() {
        System.out.println("   ________________________________________________________________________");
    }

    /**
     * Print the greet of the duke program.
     */
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

    /**
     * Read the user input to Duke.
     */
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

    /**
     * "list" command to list all the task.
     * @throws DukeException if number of items = 0.
     */
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

    /**
     * "done" command to check the finish task.
     * @param data Command and item index of the task.
     * @throws DukeException if number of items = 0 and index enter > total number of task.
     */
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

    /**
     * "to-do" command to enter the task description.
     * @param data to-do command and description of task.
     * @throws DukeException If description of data is empty.
     */
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

    /**
     * "deadline" command to enter deadline description and deadline time.
     * @param data deadline description and deadline of task.
     * @throws DukeException If description and time of deadline is empty.
     */
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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");
        Date date = null;
        String formatted_Date = null;
        try {
            date= df.parse(timeline);
            formatted_Date = outputformat.format(date);
        } catch (ParseException pe) {
            System.out.println("Invalid Data and Time Format");
        }

        task.add(new Deadline(achieve, formatted_Date));
        printLine();
        System.out.println(INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(INDENT_TASK + task.get(itemNo));
        itemNo++;
        System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
        printLine();
        System.out.println("");
    }

    /**
     * "event" command to enter event description and event time.
     * @param data event description and time of event.
     * @throws DukeException If description and time of event is empty.
     */
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputformat = new SimpleDateFormat("d MMMM yyyy',' h:mm a");
        Date date = null;
        String formatted_Date = null;
        try {
            date= df.parse(timeline);
            formatted_Date = outputformat.format(date);
        } catch (ParseException pe) {
            System.out.println("Invalid Data and Time Format");
        }

        task.add(new Deadline(achieve, formatted_Date));
        printLine();
        System.out.println(INDENT_COMMENT + "Got it. I've added this task: ");
        System.out.println(INDENT_TASK + task.get(itemNo));
        itemNo++;
        System.out.println(INDENT_COMMENT + "Now you have " + itemNo + " tasks in the list.");
        printLine();
        System.out.println("");
    }

    /**
     * "bye" command to exit Duke Program.
     * @throws DukeException If update of file fails.
     */
    public static void bye() throws DukeException {
        printLine();
        System.out.println(INDENT_COMMENT + "Bye. Hope to see you again soon!");
        printLine();
        System.out.println("");
        Database.updateFile(task);
        System.exit(0);
    }

    /**
     * "delete" command to delete task in Duke Program.
     * @param data delete command and task index.
     * @throws DukeException If list of items is zero.
     */
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

    /**
     * To start the Duke program.
     */
    public static void startDuke() {
        printGreet();
        readInput();
    }

    /**
     * To start the Database of Duke Program.
     */
    public static void startDatabase() {
        Database.initialise();
        task = Database.getSavedTask();
        itemNo = task.size();
        startDuke();
    }

    /**
     * Main function to start Duke Database.
     * @param args Arguments enter by user.
     */
    public static void main(String[] args) {
        startDatabase();
    }
}