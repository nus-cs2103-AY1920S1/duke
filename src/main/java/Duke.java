import java.util.ArrayList;
import java.util.Scanner;

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

    // All recognized commands.
    public static final String BYE_CMD = "bye";
    public static final String DONE_CMD = "done";
    public static final String LIST_CMD = "list";
    public static final String TODO_CMD = "todo";
    public static final String EVENT_CMD = "event";
    public static final String DEADLINE_CMD = "deadline";
    public static final String DELETE_CMD = "delete";

    // Delimiters
    public static final String BY_DELIM = "/by";
    public static final String AT_DELIM = "/at";

    private static ArrayList<Task> taskList = new ArrayList<Task>(MAX_TASKS);

    public static void main(String[] args) {

        printGreeting();

        Scanner input = new Scanner(System.in);
        String line = input.nextLine();

        // Keep reading input until the bye command is received.
        while (!line.equals(BYE_CMD)) {

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

        String command = line.split(" ")[0];

        switch (command) {
        case LIST_CMD:
            printList();
            break;
        case DONE_CMD:
            markTaskAsDone(line);
            break;
        case DEADLINE_CMD:
            addDeadline(line);
            break;
        case EVENT_CMD:
            addEvent(line);
            break;
        case TODO_CMD:
            addTodo(line);
            break;
        case DELETE_CMD:
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
            String[] deadlineArgs = line.split(DEADLINE_CMD)[1].split(BY_DELIM);
            Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
            addTask(newDeadline);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR
                + EMPTY_DESCRIPTION_STR_1
                + DEADLINE_CMD
                + EMPTY_DESCRIPTION_STR_2
            );
        }
    }

    public static void addEvent(String line) throws EmptyDescriptionException {

        if (verifyArgsNotEmpty(line)) {
            String[] eventArgs = line.split(EVENT_CMD)[1].split(AT_DELIM);
            Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
            addTask(newEvent);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR
                + EMPTY_DESCRIPTION_STR_1
                + EVENT_CMD
                + EMPTY_DESCRIPTION_STR_2
            );
        }
    }
    public static void addTodo(String line) throws EmptyDescriptionException {

        if (verifyArgsNotEmpty(line)) {
            String todoArg = line.split(TODO_CMD)[1];
            Task newTodo = new Todo(todoArg); 
            addTask(newTodo);
        }
        else {
            throw new EmptyDescriptionException(
                OOPS_STR 
                + EMPTY_DESCRIPTION_STR_1
                + TODO_CMD
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
