import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 100;
    public static final String LONG_LINE = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_STR = "Bye. Hope to see you again soon!";
    public static final String LIST_STR = "Here are the tasks in your list:";
    public static final String DONE_STR = "Nice! I've marked this task as done:";
    public static final String BYE_CMD = "bye";
    public static final String DONE_CMD = "done";
    public static final String LIST_CMD = "list";
    public static final String TODO_CMD = "todo";
    public static final String EVENT_CMD = "event";
    public static final String DEADLINE_CMD = "deadline";
    public static final String BY_DELIM = "/by";
    public static final String AT_DELIM = "/at";

    private static Task[] taskList = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printGreeting();

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        while (!command.equals(BYE_CMD)) {
            processCommand(command);
            command = input.nextLine();
        }

        input.close();
        printGoodbye();
    }

    public static void processCommand(String command) {
        switch (command.split(" ")[0]) {
        case LIST_CMD:
            printList();
            break;
        case DONE_CMD:
            markTaskAsDone(taskList[Integer.parseInt(command.split(" ")[1]) - 1]);
            break;
        case DEADLINE_CMD:
            addDeadline(command);
            break;
        case EVENT_CMD:
            addEvent(command);
            break;
        case TODO_CMD:
            addTodo(command);
            break;
        default:
            break;
        }
    }

    public static void addDeadline(String command) {
        String[] deadlineArgs = command.split(DEADLINE_CMD)[1].split(BY_DELIM);
        Task newDeadline = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
        addTask(newDeadline);
    }

    public static void addEvent(String command) {
        String[] eventArgs = command.split(EVENT_CMD)[1].split(AT_DELIM);
        Task newEvent = new Event(eventArgs[0].trim(), eventArgs[1].trim());
        addTask(newEvent);
    }
    public static void addTodo(String command) {
        String todoArg = command.split(TODO_CMD)[1];
        Task newTodo = new Todo(todoArg); 
        addTask(newTodo);
    }

    public static void markTaskAsDone(Task doneTask) {
        doneTask.markAsDone();
        printWithLongLines(
            DONE_STR
            + "\n"
            + doneTask
        );
    }

    public static void printList() {
        String wholeList = LIST_STR + "\n";
        
        for (int i = 0; i < taskCount; i++) {
            wholeList += String.valueOf(i + 1)
                + "."
                + taskList[i];
            
            if (i < taskCount - 1) {
                wholeList += "\n";
            }
        }

        printWithLongLines(wholeList);
    }

    public static void addTask(Task newTask) {
        taskList[taskCount] = newTask;
        taskCount++;
        printWithLongLines(
            "Got it. I've added this task:\n"
            + newTask
            + "\nNow you have " 
            + taskCount
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
