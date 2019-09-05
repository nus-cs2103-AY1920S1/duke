import java.text.ParseException;
import java.util.Date;

/**
 * Main class of the application.
 */
public class Duke {
    
    public static final String PRINTED_OOPS = "OOPS!!! ";
    public static final String PRINTED_INVALID_COMMAND = "I'm sorry, but I don't know what that means";
    public static final String PRINTED_DESCRIPTION_EMPTY_1 = "The description of a ";
    public static final String PRINTED_DESCRIPTION_EMPTY_2 = " cannot be empty.";
    
    // Delimiters
    public static final String DELIM_BY = "/by";
    public static final String DELIM_AT = "/at";

    public static final String DATE_FORMAT = "dd-MM-yy HHmm";

    public static final String USER_NAME = System.getProperty("user.name");

    public static final int MAX_TASKS = 100;

    private TaskList taskList;
    private Ui ui;
    private DateParser dateParser;
    private Storage storage;
    private Parser parser;
    private Exiter exiter;

    public Duke() {
        this.ui = new Ui();
        this.dateParser = new DateParser(DATE_FORMAT);
        this.storage = new Storage(dateParser, "C:\\Users\\" + USER_NAME + "\\Documents\\GitHub\\duke\\data.dat");
        this.parser = new Parser();
        this.exiter = new Exiter();

        this.taskList = storage.readDataFile();
    }

    public String printGreeting() {
        return this.ui.printGreeting();
    }
    
    public void run() {

        printGreeting();
        String line = ui.nextLine();

        // Keep reading input until the bye command is received.
        while (true) {
            processInputLine(line);
            line = ui.nextLine();
        }
    }

    // Processes a single line of input by identifying the command that was given, then delegating it
    // to a subfunction to handle the command call.
    public String processInputLine(String line) {

        try {
            Command command = parser.getCommandFromLine(line);
            String commandText = command.toString();
            String response = "";

            switch (command) {
            case LIST:
                response = ui.printList(taskList);
                break;
            case DONE:
                response = markTaskAsDone(
                    parser.getIndexFromLine(line)
                );
                break;
            case DEADLINE:
                response = addDeadline(
                    parser.getBeforeDelim(line, commandText, DELIM_BY),
                    parser.getAfterDelim(line, commandText, DELIM_BY)
                );
                break;
            case EVENT:
                response = addEvent(
                    parser.getBeforeDelim(line, commandText, DELIM_AT),
                    parser.getAfterDelim(line, commandText, DELIM_AT)
                );
                break;
            case TODO:
                response = addTodo(
                    parser.getArg(line, commandText)
                );
                break;
            case DELETE:
                response = deleteTask(
                    parser.getIndexFromLine(line)
                );
                break;
            case FIND:
                response = findTask(
                    parser.getArg(line, commandText)
                );
                break;
            case BYE:
                response = exitAfter(1000);
                break;
            default:
                throw new InvalidCommandException(PRINTED_OOPS + PRINTED_INVALID_COMMAND);
            }

            return response;
        } catch (DukeException e) {
            return ui.printException(e);
        } catch (ParseException e) {
            return ui.adviseDateFormat(DATE_FORMAT);
        } catch (IndexOutOfBoundsException e) {
            return ui.printIndexOutOfBoundsException();
        }
    }

    private String exitAfter(int ms) {
        exiter.exitAfter(ms);
        storage.writeDataFile(taskList);
        return ui.printGoodbye();
    }

    private String findTask(String searchStr) {
        return ui.displaySearchResults(
            taskList.search(searchStr),
            searchStr
        );
    }

    private String deleteTask(int index) {

        Task taskToDelete = taskList.get(index); 
        taskList.remove(taskToDelete);

        return ui.ackDeletion(taskToDelete, taskList.size());
    }

    private String addDeadline(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newDeadline = new Deadline(desc, dateParser.parse(date));
            return addTask(newDeadline);
        } else {
            throwEmptyDescriptionException(Command.DEADLINE);
            return "";
        }
    }

    private String addEvent(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newEvent = new Event(desc, dateParser.parse(date));
            return addTask(newEvent);
        } else {
            throwEmptyDescriptionException(Command.EVENT);
            return "";
        }
    }

    private String addTodo(String desc) throws EmptyDescriptionException {
        if (desc.length() != 0) {
            Task newTodo = new Todo(desc); 
            return addTask(newTodo);
        } else {
            throwEmptyDescriptionException(Command.TODO);
            return "";
        }
    }

    private void throwEmptyDescriptionException(Command cmd) throws EmptyDescriptionException {
        throw new EmptyDescriptionException(
            PRINTED_OOPS 
            + PRINTED_DESCRIPTION_EMPTY_1
            + cmd.toString()
            + PRINTED_DESCRIPTION_EMPTY_2
        );
    }

    private String markTaskAsDone(int index) {
        Task doneTask = taskList.get(index);
        doneTask.markAsDone();
        return ui.ackDone(doneTask);
    }

    private String addTask(Task newTask) {
        taskList.add(newTask);
        return ui.ackAddition(newTask, taskList.size());
    }
}
