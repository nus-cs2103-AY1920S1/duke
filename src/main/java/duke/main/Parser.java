package duke.main;

import duke.command.*;
import duke.exception.*;
import duke.task.PriorityLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMMM yyyy hh':'mma");

    /**
     * Parses a user input and performs action based on the input.
     *
     * @param input the input from user to duke.
     * @return a boolean to tell whether Duke should continue waiting for more commands.
     */
    public Command parse(String input) {
        String[] splitInput = input.trim().split(" ");
        Command parsedInput;
        String task = splitInput[0];
        switch (task) {
        case "list":
            parsedInput = createParsedInputForList();
            break;
        case "bye":
            parsedInput = createParsedInputForBye();
            break;
        case "delete":
            parsedInput = createParsedInputForDelete(task, input);
            break;
        case "done":
            parsedInput = createParsedInputForDone(task, input);
            break;
        case "find":
            parsedInput = createParsedInputForFind(task, input);
            break;
        case "todo":
            parsedInput = createParsedInputForTodo(task, input);
            break;
        case "deadline":
            parsedInput = createParsedInputForDeadline(task, input);
            break;
        case "event":
            parsedInput = createParsedInputForEvent(task, input);
            break;
        case "priority":
            parsedInput = createParsedInputForPriority(task, input);
            break;
        default:
            parsedInput = createParsedInputForUnknownTask();
            break;
        }
        return parsedInput;
    }

    private Command createParsedInputForBye() {
        return new ByeCommand();
    }

    private Command createParsedInputForList() {
        return new ListCommand();
    }

    private Command createParsedInputForDelete(String task, String input) {
        Command c;
        String index = "";
        try {
            checkIfAnythingAfterCommand(task, input);
            index = input.substring(task.length() + 1);
            c = new DoneCommand(tryToParseIndex(index));
        } catch (NoTaskIndexDukeException e) {
            c = new InvalidCommand("Index not provided");
        } catch (NumberFormatException e) {
            c = new InvalidCommand("Index given is not a number");
        }
        return c;
    }
    
    private Command createParsedInputForDone(String task, String input) {
        Command c;
        String index = "";
        try {
            checkIfAnythingAfterCommand(task, input);
            index = input.substring(task.length() + 1);
            int i = tryToParseIndex(index);
            c = new DoneCommand(i - 1);
        } catch (NoTaskIndexDukeException e) {
            c = new InvalidCommand("Index not provided");
        } catch (NumberFormatException e) {
            c = new InvalidCommand("Index given is not a number");
        }
        return c;
    }
    
    private void checkIfAnythingAfterCommand(String task, String input) throws NoTaskIndexDukeException {
        if (input.length() == task.length()) {
            throw new NoTaskIndexDukeException("index not provided");
        }
    }
    
    private int tryToParseIndex(String index) throws NumberFormatException{
        return Integer.parseInt(index);
    }


    private Command createParsedInputForTodo(String task, String input) {
        Command c;
        String description = "";
        try {
            description = getDscFromRestOfInput(input, task.length());
            c = new TodoCommand(description);
        } catch (EmptyDscDukeException e) {
            description = "Description is empty";
            c = new InvalidCommand(description);
        }
        return c;
    }

    private Command createParsedInputForFind(String task, String input) {
        Command c;
        String searchKey = "";
        try {
            searchKey = getDscFromRestOfInput(input, task.length());
            c = new FindCommand(searchKey);
        } catch (EmptyDscDukeException e) {
            searchKey = "No keyword to search for";
            c = new InvalidCommand(searchKey);
        }
        return c;
    }

    private Command createParsedInputForEvent(String task, String input) {
        Command c;
        String description;
        String byTime;
        Date d;
        int indexOfDivider;
        try {
            indexOfDivider = findDividerIndex(input);
            checkIfDateAfterKeyword("at", input, indexOfDivider);
            byTime = input.substring(indexOfDivider + 4);
            description = getDscForEventAndDeadline(input, task.length(), indexOfDivider);
            d = Parser.inputDateFormat.parse(byTime);
        } catch (NoDateDukeException e) {
            return new InvalidCommand("Divider('/') for date could not be found");
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand("");
        } catch (EmptyDscDukeException e) {
            return new InvalidCommand("Description is empty");
        } catch (ParseException e) {
            return new InvalidCommand("Date is in incorrect format");
        }
        c = new EventCommand(description, d);
        return c;
    }

    private void checkIfDateAfterKeyword(String keyword, String input, int index) throws NoDateDukeException{
        int dateStartIndex = index + keyword.length() + 1;
        if (dateStartIndex > (input.length() - 1)) {
            throw new NoDateDukeException("Index is greater than the input last index");
        }
    }

    private Command createParsedInputForDeadline(String task, String input) {
        Command c;
        String description;
        String byTime;
        Date d;
        int indexOfDivider;
        try {
            indexOfDivider = findDividerIndex(input);
            checkIfDateAfterKeyword("by", input, indexOfDivider);
            byTime = input.substring(indexOfDivider + 4);
            description = getDscForEventAndDeadline(input, task.length(), indexOfDivider);
            d = Parser.inputDateFormat.parse(byTime);
        } catch (NoDateDukeException e) {
            return new InvalidCommand("Divider('/') for date could not be found");
        } catch (EmptyDscDukeException e) {
            return new InvalidCommand("Description is empty");
        } catch (ParseException e) {
            return new InvalidCommand("Date is in incorrect format");
        }
        c = new DeadlineCommand(description, d);
        return c;
    }

    private int findDividerIndex(String input) throws NoDateDukeException{
        int indexOfDivider = input.indexOf('/');
        if (indexOfDivider == -1) {
            throw new NoDateDukeException("Cannot find '/' divider");
        }
        return indexOfDivider;
    }

    private String getDscFromRestOfInput(String input, int taskLength) throws EmptyDscDukeException{
        if (input.length() == taskLength) {
            throw new EmptyDscDukeException("Description not provided");
        }
        return input.substring(taskLength + 1);
    }

    private String getDscForEventAndDeadline(String input, int taskLength, int indexOfDivider) throws EmptyDscDukeException {
        String description = input.substring(taskLength + 1, indexOfDivider);
        checkForEmptyDsc(description);

        return description;
    }

    private void checkForEmptyDsc(String dsc) throws EmptyDscDukeException{
        if (dsc.trim().length() == 0) {
            throw new EmptyDscDukeException("Empty description");
        }
    }

    private Command createParsedInputForPriority(String task, String input) {
        Command c;
        try {
            checkIfAnythingAfterCommand(task, input);
            String indexInString = input.substring(9, 10);
            int index = tryToParseIndex(indexInString) - 1;
            checkIfPriorityAfterIndex(11, input);
            String priorityInString = input.substring(11).toUpperCase();
            PriorityLevel p = parsePriorityLevel(priorityInString);
            c = new PriorityCommand(index, p);
        } catch (NoTaskIndexDukeException e) {
            c = new InvalidCommand("Index not provided");
        } catch (NumberFormatException e) {
            c = new InvalidCommand("Index given is not a number");
        } catch (NoPriorityDukeException e) {
            c = new InvalidCommand("No priority after index");
        } catch (InvalidPriorityDukeException e) {
            c = new InvalidCommand("Invalid priority");
        }
        return c;
    }

    private PriorityLevel parsePriorityLevel(String s) throws InvalidPriorityDukeException{
        for (PriorityLevel p : PriorityLevel.values()) {
            if (s.equals(p.name())) {
                return p;
            }
        }
        throw new InvalidPriorityDukeException("Invalid priority");
    }

    private void checkIfPriorityAfterIndex(int indexToStartFrom, String input) throws NoPriorityDukeException {
        if (input.length() < indexToStartFrom) {
            throw new NoPriorityDukeException("No priority after index");
        }
    }

    private Command createParsedInputForUnknownTask() {
        return new InvalidCommand("Unknown command");
    }
}
