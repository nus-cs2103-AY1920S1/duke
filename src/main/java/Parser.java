import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Parser {
    static Command parse(String fullCommand) throws DukeException {
        if (isExitCommand(fullCommand)) {
            return new ExitCommand();
        } else if (isListCommand(fullCommand)) {
            return new ListCommand();
        } else if (isDoneCommand(fullCommand)) {
            String newInput = fullCommand.replaceFirst("done", "").trim();
            int oneBasedIndex = validateDoneIndex(newInput);
            return new DoneCommand(oneBasedIndex);
        } else if (isAddTodoCommand(fullCommand)) {
            String todo = fullCommand.replaceFirst("todo", "").trim();
            validateTodo(todo);
            return new AddTodoCommand(todo);
        } else if (isAddDeadlineCommand(fullCommand)) {
            String[] deadline = validateDeadline(fullCommand);
            if (isDate(deadline[1])) {
                Date deadlineDate = parseDate(deadline[1]);
                return new AddDeadlineCommand(deadline[0], deadlineDate);
            } else {
                return new AddDeadlineCommand(deadline[0], deadline[1]);
            }
        } else if (isAddEventCommand(fullCommand)) {
            String[] event = validateEvent(fullCommand);
            if (isDate(event[1])) {
                Date eventDate = parseDate(event[1]);
                return new AddEventCommand(event[0], eventDate);
            } else {
                return new AddEventCommand(event[0], event[1]);
            }
        } else if (isDeleteCommand(fullCommand)) {
            String newInput = fullCommand.replaceFirst("delete", "").trim();
            int oneBasedIndex = validateDeleteIndex(newInput);
            return new DeleteCommand(oneBasedIndex);
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Date parseDate(String date) throws DukeException {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    private static boolean isDate(String input) {
        // Assume date is in the format 2/12/2019 1800
        String[] splitInput = input.split("/");
        if (splitInput.length != 3 || isNotNumeric(splitInput[0]) || isNotNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2 || isNotNumeric(yearAndTime[0]) || isNotNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    private static int validateDoneIndex(String doneInput) throws DukeException {
        return validateDoneOrDeleteIndex(doneInput);
    }

    private static int validateDeleteIndex(String doneInput) throws DukeException {
        return validateDoneOrDeleteIndex(doneInput);
    }

    private static int validateDoneOrDeleteIndex(String doneInput) throws DukeException {
        // Checks that the string is not empty and is an integer
        if (doneInput.isEmpty() || isNotNumeric(doneInput)) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be blank or not an integer.");
        }

        return Integer.parseInt(doneInput);
    }

    private static boolean isNotNumeric(String input) {
        return !input.matches("-?\\d+(\\.\\d+)?");
    }

    private static void validateTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static String[] validateEvent(String input) throws DukeException {
        return validateEventOrDeadline(input, "event", "/at");
    }

    private static String[] validateDeadline(String input) throws DukeException {
        return validateEventOrDeadline(input, "deadline", "/by");
    }

    private static String[] validateEventOrDeadline(String input, String textToReplace, String textToSplit) throws DukeException {
        // Removes textToReplace from input,
        // and finally split it by textToSplit
        String[] splitInput = input.replaceFirst(textToReplace, "")
                .trim().split(textToSplit);

        // Trims all whitespace from the resulting split
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        // Event or deadline should be of length 2 after splitting and both should not be blank
        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("\u2639 OOPS!!! I had trouble processing that input.\n" +
                    "\tPlease make sure that the task description and dates are not empty!");
        }
        return splitInput;
    }

    private static boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    private static boolean isListCommand(String input) {
        return input.equals("list");
    }

    private static boolean isDoneCommand(String input) {
        return input.startsWith("done");
    }

    private static boolean isAddTodoCommand(String input) {
        return input.startsWith("todo");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    private static boolean isAddEventCommand(String input) {
        return input.startsWith("event");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }
}
