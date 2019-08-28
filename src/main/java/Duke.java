import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;

public class Duke {
    
    public static final String OOPS_STR = "OOPS!!! ";
    public static final String INVALID_COMMAND_STR = "I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_DESCRIPTION_STR_1 = "The description of a ";
    public static final String EMPTY_DESCRIPTION_STR_2 = " cannot be empty.";
    
    // Delimiters
    public static final String BY_DELIM = "/by";
    public static final String AT_DELIM = "/at";

    public static final int MAX_TASKS = 100;

    private static ArrayList<Task> taskList;

    public static void main(String[] args) {

        Ui.printGreeting();

        taskList = Storage.readDataFile();

        String line = Ui.nextLine();

        // Keep reading input until the bye command is received.
        while (!line.equals(Command.BYE.toString())) {

            try {
                processInputLine(line);
            }
            catch (DukeException e) {
                Ui.printException(e);
            }
            catch (ParseException e) {
                Ui.adviseDateFormat(DateParser.DATE_FORMAT);
            }

            line = Ui.nextLine();
        }

        Storage.writeDataFile(taskList);
        Ui.printGoodbye();
    }

    // Processes a single line of input by identifying the command that was given, then delegating it
    // to a subfunction to handle the command call.
    public static void processInputLine(String line) throws DukeException, ParseException{

        Command command = Command.getFromString(line.split(" ")[0]);

        switch (command) {
        case LIST:
            Ui.printList(taskList);
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

        Ui.ackDeletion(taskToDelete, taskList.size());
    }

    public static void addDeadline(String line) throws EmptyDescriptionException, ParseException {
        
        if (verifyArgsNotEmpty(line)) {
            String[] deadlineArgs = line.split(Command.DEADLINE.toString())[1].split(BY_DELIM);
            Task newDeadline = new Deadline(deadlineArgs[0].trim(), parseDate(deadlineArgs[1].trim()));
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

    public static void addEvent(String line) throws EmptyDescriptionException, ParseException {

        if (verifyArgsNotEmpty(line)) {
            String[] eventArgs = line.split(Command.EVENT.toString())[1].split(AT_DELIM);
            Task newEvent = new Event(eventArgs[0].trim(), parseDate(eventArgs[1].trim()));
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

        Ui.ackDone(doneTask);
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        
        Ui.ackAddition(newTask, taskList.size());
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return DateParser.parse(dateStr);
    }
}
