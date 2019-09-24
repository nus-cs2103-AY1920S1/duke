import java.text.ParseException;

public class Parser {

    // Messages for DukeExceptions

    static final String EMPTY_INDEX = "Index for done cannot be empty!!"
            + "\n Type 'help' for a list of commands";
    static final String EMPTY_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    static final String EMPTY_DEADLINE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    static final String NO_DUE_DATE = "☹ OOPS!!! There is no due date specified.";
    static final String INCORRECT_DEADLINE = "Incorrect format for due date of Deadline!";
    static final String EMPTY_EVENT = "☹ OOPS!!! The description of an event cannot be empty.";
    static final String INCORRECT_EVENT = "Incorrect format for the date of Event!!";
    static final String INDICATE_SEARCH = "Please indicate what you would like to search for."
            + "\nType 'help' for a list of instructions.";
    static final String CANNOT_UNDERSTAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public Parser() {

    }

    /**
     * Parses the sentence entered by the user and returns various commands
     * @param input by the user
     * @return other commands
     */
    // parse the command string keyed in by the user
    public static Command parse (String input) throws DukeException {
        String[] userInput = input.split(" ", 2);
        String firstWord = userInput[0];
            switch (firstWord) {
                case "bye":
                case "exit":
                case "quit":
                    return new ExitCommand();
                case "list":
                case "tasks":
                    return new ListCommand();
                case "funfact":
                case "funFact":
                case "facts":
                case "fun":
                    return new FunFactCommand();
                case "help" :
                    return new HelpCommand();
                case "stat" :
                case "stats":
                case "statistics":
                    return new StatisticsCommand();
                case "done":
                    int indexDone = 0;
                    try {
                        indexDone = Integer.parseInt(userInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(EMPTY_INDEX);
                    }
                    return new DoneCommand(indexDone);
                case "delete":
                case "del":
                    int indexDelete = 0;
                    try {
                        indexDelete = Integer.parseInt(userInput[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(EMPTY_INDEX);
                    }
                    return new DeleteCommand(indexDelete);
                case "todo":
                case "t":
                case "td":
                case "toDo":
                    String descTodo = "";
                    try {
                        descTodo = userInput[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(EMPTY_TODO);
                    }
                    if (descTodo.isEmpty()) {
                        throw new DukeException(EMPTY_TODO);
                    }
                    ToDo todo = new ToDo(descTodo);
                    return new AddCommand(todo);
                case "deadline":
                case "d":
                case "dl":
                case "deadLine":
                    String descDeadline = "";
                    try {
                        descDeadline = userInput[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(EMPTY_DEADLINE);
                    }
                    if (descDeadline.isEmpty()) {
                        throw new DukeException(EMPTY_DEADLINE);
                    }
                    String[] deadlineInfo = descDeadline.split("/by");
                    if (deadlineInfo.length < 2) {
                        throw new DukeException(NO_DUE_DATE);
                    }
                    String actionD = deadlineInfo[0]; // the action of the deadline
                    String dueDateD = deadlineInfo[1]; // the due date of the deadline
                    Deadline deadline = new Deadline(actionD);
                    try {
                        deadline.parseTime(dueDateD);
                    } catch (ParseException e) {
                        throw new DukeException(INCORRECT_DEADLINE);
                    }
                    return new AddCommand(deadline);
                case "event":
                case "e":
                    String descEvent = "";
                    try {
                        descEvent = userInput[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(EMPTY_EVENT);
                    }
                    if (descEvent.isEmpty()) {
                        throw new DukeException(EMPTY_EVENT);
                    }
                    String[] eventInfo = descEvent.split("/at");
                    if (eventInfo.length < 2) {
                        throw new DukeException(NO_DUE_DATE);
                    }
                    String actionE = eventInfo[0]; // the action of the event
                    String dueDateE = eventInfo[1];
                    Event event = new Event(actionE);
                    try {
                        event.parseTime(dueDateE);
                    } catch (ParseException e) {
                        throw new DukeException(INCORRECT_EVENT);
                    }
                    return new AddCommand(event);
                case "find":
                case "search":
                    String query = "";
                    try {
                        query = userInput[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException(INDICATE_SEARCH);
                    }
                    return new FindCommand(query);
                default :
                    throw new DukeException(CANNOT_UNDERSTAND);
            }
    }
}
