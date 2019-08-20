import java.util.ArrayList;
import java.util.Scanner;

enum Command {
    BYE     ("bye"),
    DONE    ("done"),
    LIST    ("list"),
    TODO    ("todo"),
    EVENT   ("event"),
    DEADLINE("deadline"),
    DELETE  ("delete");

    private String commandText;

    Command (String commandText) {
        this.commandText = commandText;
    }

    public String toString() {
        return commandText;
    }

    public static Command getFromString(String commandText) throws InvalidCommandException {
        Command[] allCommands = Command.values();

        for (Command cmd : allCommands) {
            if (commandText.equals(cmd.toString())) {
                return cmd;
            }
        }

        throw new InvalidCommandException(Duke.OOPS_STR + Duke.INVALID_COMMAND_STR);
    }
}

public class Duke {

    public static final int MAX_TASKS = 100;

    // Strings that Duke will output
    public static final String LONG_LINE = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_STR = "Bye. Hope to see you again soon!";
    public static final String LIST_STR = "Here are the tasks in your list:";
    public static final String DONE_STR = "Nice! I've marked this task as done:";
    public static final String OOPS_STR = "☹ OOPS!!! ";
    public static final String INVALID_COMMAND_STR = "I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_DESCRIPTION_STR_1 = "The description of a ";
    public static final String EMPTY_DESCRIPTION_STR_2 = " cannot be empty.";

    // Delimiters
    public static final String BY_DELIM = "/by";
    public static final String AT_DELIM = "/at";

    private static ArrayList<Task> taskList = new ArrayList<Task>(MAX_TASKS);

    public static void main(String[] args) {

        printGreeting();

        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        // Keep reading input until the bye command is received.
        while (!line.equals(Command.BYE.toString())) {

            try {
                processInputLine(line);
            }
            catch (DukeException e) {
                printWithLongLines(e.getMessage());
            }

            line = input.nextLine();
        }

        input.close();
        printGoodbye();
    }

    // Processes a single line of input by identifying the command that was given, then delegating it
    // to a subfunction to handle the command call.
    public static void processInputLine(String line) throws DukeException {

        Command command = Command.getFromString(line.split(" ")[0]);

        switch (command) {
        case LIST:
            printList();
            break;
        case DONE:
            markTaskAsDone(line);
            break;
        case DEADLINE:
            addDeadline(line);
            break;
        case EVENT:
            addEvent(line);
            break;
        case TODO:
            addTodo(line);
            break;
        case DELETE:
            deleteTask(line);
            break;
        default:
            throw new InvalidCommandException(OOPS_STR + INVALID_COMMAND_STR);
        }
    }

    public static void deleteTask(String line) {

        int index = Integer.parseInt(line.split(" ")[1]) - 1;
        Task taskToDelete = taskList.get(index); 
        taskList.remove(taskToDelete);

        printWithLongLines(
            "Noted. I've removed this task:\n"
            + taskToDelete
            + "\nNow you have " 
            + taskList.size()
            + " tasks in the list."
        );
    }

    public static void addDeadline(String line) throws EmptyDescriptionException {
        
        if (verifyArgsNotEmpty(line)) {
            String[] deadlineArgs = line.split(Command.DEADLINE.toString())[1].split(BY_DELIM);
            Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
            addTask(newDeadline);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR
                + EMPTY_DESCRIPTION_STR_1
                + Command.DEADLINE.toString()
                + EMPTY_DESCRIPTION_STR_2
            );
        }
    }

    public static void addEvent(String line) throws EmptyDescriptionException {

        if (verifyArgsNotEmpty(line)) {
            String[] eventArgs = line.split(Command.EVENT.toString())[1].split(AT_DELIM);
            Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
            addTask(newEvent);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR
                + EMPTY_DESCRIPTION_STR_1
                + Command.EVENT.toString()
                + EMPTY_DESCRIPTION_STR_2
            );
        }
    }
    public static void addTodo(String line) throws EmptyDescriptionException {

        if (verifyArgsNotEmpty(line)) {
            String todoArg = line.split(Command.TODO.toString())[1];
            Task newTodo = new Todo(todoArg); 
            addTask(newTodo);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR 
                + EMPTY_DESCRIPTION_STR_1
                + Command.TODO.toString()
                + EMPTY_DESCRIPTION_STR_2
            );
        }
    }

    public static boolean verifyArgsNotEmpty(String args) {
        return args.trim().split(" ").length > 1;
    }

    public static void markTaskAsDone(String line) {

        int index = Integer.parseInt(line.split(" ")[1]) - 1;
        Task doneTask = taskList.get(index);
        doneTask.markAsDone();

        printWithLongLines(
            DONE_STR
            + "\n"
            + doneTask
        );
    }

    public static void printList() {
        String wholeList = LIST_STR + "\n";
        
        for (int i = 0; i < taskList.size(); i++) {
            wholeList += String.valueOf(i + 1)
                + "."
                + taskList.get(i);
            
            if (i < taskList.size() - 1) {
                wholeList += "\n";
            }
        }

        printWithLongLines(wholeList);
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        
        printWithLongLines(
            "Got it. I've added this task:\n"
            + newTask
            + "\nNow you have " 
            + taskList.size()
            + " tasks in the list."
        );
    }

    public static void printGoodbye() {
        printWithLongLines(BYE_STR);
    }

    public static void printGreeting() {
        printWithLongLines(GREETING);
    }

    public static void printWithLongLines(String stringToPrint) {
        System.out.println(
            LONG_LINE
            + "\n"
            + stringToPrint
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }
}
