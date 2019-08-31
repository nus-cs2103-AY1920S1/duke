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

    private void run() {

        ui = new Ui();
        dateParser = new DateParser(DATE_FORMAT);
        storage = new Storage(dateParser, "C:\\Users\\" + USER_NAME + "\\Documents\\GitHub\\duke\\data.dat");
        parser = new Parser();

        ui.printGreeting();
        taskList = storage.readDataFile();

        String line = ui.nextLine();

        // Keep reading input until the bye command is received.
        while (parser.isNotByeCommand(line)) {
            try {
                processInputLine(line);
            } catch (DukeException e) {
                ui.printException(e);
            } catch (ParseException e) {
                ui.adviseDateFormat(DATE_FORMAT);
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBoundsException();
            }

            line = ui.nextLine();
        }

        storage.writeDataFile(taskList);
        ui.printGoodbye();
    }

    // Processes a single line of input by identifying the command that was given, then delegating it
    // to a subfunction to handle the command call.
    private void processInputLine(String line) throws DukeException, ParseException {

        Command command = parser.getCommandFromLine(line);
        String commandText = command.toString();

        switch (command) {
        case LIST:
            ui.printList(taskList);
            break;
        case DONE:
            markTaskAsDone(
                parser.getIndexFromLine(line)
            );
            break;
        case DEADLINE:
            addDeadline(
                parser.getBeforeDelim(line, commandText, DELIM_BY),
                parser.getAfterDelim(line, commandText, DELIM_BY)
            );
            break;
        case EVENT:
            addEvent(
                parser.getBeforeDelim(line, commandText, DELIM_AT),
                parser.getAfterDelim(line, commandText, DELIM_AT)
            );
            break;
        case TODO:
            addTodo(
                parser.getArg(line, commandText)
            );
            break;
        case DELETE:
            deleteTask(
                parser.getIndexFromLine(line)
            );
            break;
        case FIND:
            findTask(
                parser.getArg(line, commandText)
            );
            break;
        default:
            throw new InvalidCommandException(PRINTED_OOPS + PRINTED_INVALID_COMMAND);
        }
    }

    private void findTask(String searchStr) {
        ui.displaySearchResults(
            taskList.search(searchStr),
            searchStr
        );
    }

    private void deleteTask(int index) {

        Task taskToDelete = taskList.get(index); 
        taskList.remove(taskToDelete);

        ui.ackDeletion(taskToDelete, taskList.size());
    }

    private void addDeadline(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newDeadline = new Deadline(desc, dateParser.parse(date));
            addTask(newDeadline);
        } else {
            throwEmptyDescriptionException(Command.DEADLINE);
        }
    }

    private void addEvent(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newEvent = new Event(desc, dateParser.parse(date));
            addTask(newEvent);
        } else {
            throwEmptyDescriptionException(Command.EVENT);
        }
    }

    private void addTodo(String desc) throws EmptyDescriptionException {
        if (desc.length() != 0) {
            Task newTodo = new Todo(desc); 
            addTask(newTodo);
        } else {
            throwEmptyDescriptionException(Command.TODO);
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

    private void markTaskAsDone(int index) {
        Task doneTask = taskList.get(index);
        doneTask.markAsDone();
        ui.ackDone(doneTask);
    }

    private void addTask(Task newTask) {
        taskList.add(newTask);
        ui.ackAddition(newTask, taskList.size());
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
