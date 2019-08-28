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

    private TaskList taskList;
    private Ui ui;
    private DateParser dateParser;
    private Storage storage;
    private Parser parser;

    public void run() {

        ui = new Ui();
        dateParser = new DateParser();
        storage = new Storage(dateParser);
        parser = new Parser();

        ui.printGreeting();

        taskList = storage.readDataFile();

        String line = ui.nextLine();

        // Keep reading input until the bye command is received.
        while (!line.equals(Command.BYE.toString())) {

            try {
                processInputLine(line);
            }
            catch (DukeException e) {
                ui.printException(e);
            }
            catch (ParseException e) {
                ui.adviseDateFormat(DateParser.DATE_FORMAT);
            }

            line = ui.nextLine();
        }

        storage.writeDataFile(taskList);
        ui.printGoodbye();
    }

    // Processes a single line of input by identifying the command that was given, then delegating it
    // to a subfunction to handle the command call.
    public void processInputLine(String line) throws DukeException, ParseException {

        Command command = parser.getCommandFromLine(line);
        String commandText = command.toString();

        switch (command) {
        case LIST:
            ui.printList(taskList);
            break;
        case DONE:
            markTaskAsDone(parser.getIndexFromLine(line));
            break;
        case DEADLINE:
            addDeadline(parser.getBeforeDelim(line, commandText, BY_DELIM), parser.getAfterDelim(line, commandText, BY_DELIM));
            break;
        case EVENT:
            addEvent(parser.getBeforeDelim(line, commandText, AT_DELIM), parser.getAfterDelim(line, commandText, AT_DELIM));
            break;
        case TODO:
            addTodo(parser.getArg(line, commandText));
            break;
        case DELETE:
            deleteTask(parser.getIndexFromLine(line));
            break;
        default:
            throw new InvalidCommandException(OOPS_STR + INVALID_COMMAND_STR);
        }
    }

    public void deleteTask(int index) {

        Task taskToDelete = taskList.get(index); 
        taskList.remove(taskToDelete);

        ui.ackDeletion(taskToDelete, taskList.size());
    }

    public void addDeadline(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newDeadline = new Deadline(desc, parseDate(date));
            addTask(newDeadline);
        }
        else {
            throwEmptyDescriptionException(Command.DEADLINE);
        }
    }

    public void addEvent(String desc, String date) throws EmptyDescriptionException, ParseException {
        if (desc.length() != 0) {
            Task newEvent = new Event(desc, parseDate(date));
            addTask(newEvent);
        }
        else {
            throwEmptyDescriptionException(Command.EVENT);
        }
    }

    public void addTodo(String desc) throws EmptyDescriptionException {

        if (desc.length() != 0) {
            Task newTodo = new Todo(desc); 
            addTask(newTodo);
        }
        else {
            throwEmptyDescriptionException(Command.TODO);
        }
    }

    public void throwEmptyDescriptionException(Command cmd) throws EmptyDescriptionException {
        throw new EmptyDescriptionException(
            OOPS_STR 
            + EMPTY_DESCRIPTION_STR_1
            + cmd.toString()
            + EMPTY_DESCRIPTION_STR_2
        );
    }

    public boolean verifyArgsNotEmpty(String args) {
        return args.trim().split(" ").length > 1;
    }

    public void markTaskAsDone(int index) {

        Task doneTask = taskList.get(index);
        doneTask.markAsDone();

        ui.ackDone(doneTask);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        
        ui.ackAddition(newTask, taskList.size());
    }

    public Date parseDate(String dateStr) throws ParseException {
        return dateParser.parse(dateStr);
    }
    
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

}
