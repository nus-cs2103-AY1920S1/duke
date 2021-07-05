package seedu.duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Executes methods that help to understand user input and data file input.
 * It identifies the intended user command, processes the information inputted
 * and calls the command to be executed.
 */
public class Parser {

    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
    protected static SimpleDateFormat dashDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    protected static SimpleDateFormat twelveHrTimeFormat = new SimpleDateFormat("h.mm aa");
    private static final int CHAR_LENGTH_OF_EVENT = 5;
    private static final int CHAR_LENGTH_OF_DEADLINE = 8;
    private static final int CHAR_LENGTH_OF_TODO = 4;
    private static final int CHAR_LENGTH_OF_BYE = 3;
    private static final int CHAR_LENGTH_OF_FIND = 4;
    private static final int CHAR_LENGTH_OF_DONE = 4;
    private static final int CHAR_LENGTH_OF_DELETE = 6;
    private static final int CHAR_LENGTH_OF_LIST = 4;
    private static final int CHAR_LENGTH_OF_EXPENSE = 7;
    private static final int CHAR_LENGTH_OF_DELETE_E = 8;

    /**
     * Class constructor.
     */
    public Parser() {
    }

    /**
     * Creates a date object, taking in the format of dd/MM/yyyy for the string date input..
     *
     * @param s String of the date input.
     * @return Date of the task which is a <code>Date</code> object.
     * @throws ParseException If there is incorrect date user input format.
     */
    public static Date createDate(String s) throws ParseException {
        Date date = dateFormat.parse(s);
        return date;
    }

    /**
     * Creates a date object, taking in format of HHmm (24hr) for the string time input.
     *
     * @param s String of the time input.
     * @return Time of the task which is a <code>Date</code> object.
     * @throws ParseException If there is incorrect time user input format.
     */
    public static Date createTime(String s) throws ParseException {
        Date time = timeFormat.parse(s);
        return time;
    }

    /**
     * Creates a <code>Task</code> object by reading in a line of input in data file format.
     *
     * @param line String of a line of input in the data file format.
     * @return <code>Deadline</code> , <code>Event</code> or <code>Todo</code >object in data file format.
     * @throws ParseException If there is incorrect input format, different from standard data file format.
     */
    public static Task readInFileLine(String line) throws ParseException {
        String[] stringArr = line.split(" [|] ", 0);
        assert line.length() > 0 : "file path invalid in parser line 65";
        if (stringArr[0].equals("E")) {
            String[] dateTimeArr = (stringArr[3]).split(" ", 2);
            Date date = createDate(dateTimeArr[0]);
            Date time = createTime(dateTimeArr[1]);
            Event event = new Event(stringArr[2], date, time);
            if (stringArr[1].equals("1")) {
                event.markAsDone();
            }
            return event;
        } else if (stringArr[0].equals("T")) {
            Todo td = new Todo(stringArr[2]);
            if (stringArr[1].equals("1")) {
                td.markAsDone();
            }
            return td;
        } else {
            String[] dateTimeArr = (stringArr[3]).split(" ", 2);
            Date date = createDate(dateTimeArr[0]);
            Date time = createTime(dateTimeArr[1]);
            Deadline dl = new Deadline(stringArr[2], date, time);
            if (stringArr[1].equals("1")) {
                dl.markAsDone();
            }
            return dl;
        }
    }

    /**
     * Identifies if the command intended to be a delete command to delete task.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a delete command.
     */
    public static boolean isDeleteCommand(String command) {
        return (command.length() >= CHAR_LENGTH_OF_DELETE && command.substring(0, 6).equals("delete"));
    }


    /**
     * Identifies the index of task to be deleted from the user command input.
     *
     * @param command String of the command user input.
     * @return Integer index of the task to be a deleted.
     */
    public static int taskToDelete(String command) {
        return Integer.parseInt(command.substring(7));
    }

    /**
     * Identifies if the command intended to be a todo command to create a todo task.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a todo command.
     */
    public static boolean isTodoCommand(String command) {
        return (command.length() >= CHAR_LENGTH_OF_TODO && command.substring(0, 4).equals("todo"));
    }

    /**
     * Creates a new todo task according to the user input command.
     *
     * @param command String of the command user input.
     * @return <code>Todo</code> object created from user input command.
     */
    public static Todo createTodo(String command) {
        return new Todo(command.substring(5));
    }

    /**
     * Identifies if the command intended to be a event command to create a event task.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a event command.
     */
    public static boolean isEventCommand(String command) {
        return (command.length() >= CHAR_LENGTH_OF_EVENT && command.substring(0, 5).equals("event"));
    }

    /**
     * Creates a new event task according to the user input command.
     *
     * @param command String of the command user input.
     * @return <code>Event</code> object created from user input command.
     * @throws ParseException If the date or time input of the user is incorrect.
     */
    public static Event createEvent(String command) throws ParseException {
        assert command.contains("/at") : "event command invalid";
        String[] arr = command.split(" /at ", 2);
        String[] dateTimeArr = (arr[1]).split(" ", 2);
        Date date;
        Date time;
        try {
            date = dateFormat.parse(dateTimeArr[0]);
        } catch (ParseException e) {
            date = dashDateFormat.parse(dateTimeArr[0]);
        }
        try {
            time = timeFormat.parse(dateTimeArr[1]);
        } catch (ParseException e) {
            time = twelveHrTimeFormat.parse(dateTimeArr[1]);
        }
        return new Event(arr[0].substring(6), date, time);

    }

    /**
     * Identifies if the command intended to be a deadline command to create a deadline task.
     *
     * @param command String of the command user input.
     * @returrn Boolean if the command is intended to be a deadline command.
     */
    public static boolean isDeadlineCommand(String command) {
        return (command.length() >= CHAR_LENGTH_OF_DEADLINE && command.substring(0, 8).equals("deadline"));
    }

    /**
     * Creates a new deadline task according to the user input command.
     *
     * @param command String of the command user input.
     * @returrn <code>Deadline</code> object created from user input command.
     * @throws ParseException If the date or time input of the user is incorrect.
     */
    public static Deadline createDeadline(String command) throws ParseException {
        //assert command.contains("/by") : "deadline command invalid";
        String[] arr = command.split(" /by ", 2);
        String[] dateTimeArr = (arr[1]).split(" ", 2);
        Date date;
        Date time;
        try {
            date = dateFormat.parse(dateTimeArr[0]);
        } catch (ParseException e) {
            date = dashDateFormat.parse(dateTimeArr[0]);
        }
        try {
            time = timeFormat.parse(dateTimeArr[1]);
        } catch (ParseException e) {
            time = twelveHrTimeFormat.parse(dateTimeArr[1]);
        }
        return new Deadline(arr[0].substring(9), date, time);
    }

    /**
     * Identifies if the command intended to be a mark as done command to mark a task as done.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a mark as done command.
     */
    public static boolean isMarkDone(String command) {
        return (command.length() >= CHAR_LENGTH_OF_DONE && command.substring(0, 4).equals("done"));
    }

    /**
     * Identifies the index of task to be marked as done from the user command input.
     *
     * @param command String of the command user input.
     * @return Integer index of the task to be marked as done.
     */
    public static int taskToMarkDone(String command) {
        assert command.substring(0, 4).equals("done") : "done command invalid";
        int curr = Integer.parseInt(command.substring(5));
        return curr;
    }

    /**
     * Identifies if the command intended to be a list command to list out all tasks.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a list command.
     */
    public static boolean isListCommand(String command) {
        return (command.length() == CHAR_LENGTH_OF_LIST && command.equals("list"));
    }

    /**
     * Parses the user input command to call the correct intended command.
     * Commands include delete, list, deadline, event, todo, bye.
     *
     * @param command String of the command user input.
     * @param ui Ui initialized in duke.
     * @return Command according to the command from user.
     * @throws DukeException If the command is incorrect and not understood by Duke.
     */
    public static Command parse(String command, Ui ui) throws DukeException {
        if (Parser.isByeCommand(command)) {
            return new ByeCommand(command);
        } else if (Parser.isHelpCommand(command)) {
            return new HelpCommand();
        } else if (Parser.isDeleteAllCommand(command)) {
            return new DeleteAllCommand();
        } else if (Parser.isDeleteExpenseCommand(command)) {
            return new DeleteExpenseCommand(command);
        } else if (Parser.isDeleteCommand(command)) {
            return new DeleteCommand(command);
        } else if (Parser.isMarkDone(command)) {
            return new MarkDoneCommand(command);
        } else if (Parser.isListCommand(command)) {
            return new ListCommand(command);
        } else if (Parser.isFindCommand(command)) {
            return new FindCommand(command);
        } else if (Parser.isTodoCommand(command)) {
            return new TodoCommand(command);
        } else if (Parser.isEventCommand(command)) {
            return new EventCommand(command);
        } else if (Parser.isDeadlineCommand(command)) {
            return new DeadlineCommand(command);
        } else if (Parser.isExpenseCommand(command)) {
            return new ExpenseCommand(command);
        } else if (Parser.isEListCommand(command)) {
            return new EListCommand(command);
        } else if (Parser.isTutorialCommand(command)) {
            return new TutorialCommand();
        } else if (Parser.isIncomeCommand(command)) {
            return new IncomeCommand(command);
        } else {
            throw new DukeException(ui.noSuchCommand());
        }
    }

    /**
     * Indentifies if command is intended to be income command.
     *
     * @param command User input.
     * @return Boolean whether the command is income command.
     */
    public static boolean isIncomeCommand(String command) {
        return command.length() >= 6 && command.contains("income");
    }

    /**
     * Identifies if command is intended to be a delete all command.
     *
     * @param command String user input
     * @return Boolean if command is delete all command.
     */
    public static boolean isDeleteAllCommand(String command) {
        return command.equals("delete all");
    }

    /**
     * Identifies if the command is intended to be a tutorial command.
     *
     * @param command String of command user input.
     * @return Boolean if command is tutorial command.
     */
    public static boolean isTutorialCommand(String command) {
        return command.equals("tutorial");
    }

    /**
     * Identifies if the command intended to be a find command to find a task.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a find command.
     */
    public static boolean isFindCommand(String command) {
        return (command.length() >= CHAR_LENGTH_OF_FIND) && command.substring(0, 4).equals("find");
    }

    /**
     * Returns the keyword found in the command to find a task in the task list.
     *
     * @param command String of the command user input.
     * @return String of the keyword.
     */
    public static String getKeyword(String command) {
        return command.substring(5);
    }

    /**
     * Identifies if the command intended to be a bye command.
     *
     * @param command String of the command user input.
     * @return Boolean if the command is intended to be a bye command.
     */
    public static boolean isByeCommand(String command) {
        return (command.length() == CHAR_LENGTH_OF_BYE && command.equals("bye"));
    }

    /**
     * Checks the exception for find commands.
     * Throws DukeException when the command is intended to find a task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to find a task.
     */
    public static void checkErrorForFindCommand(String command, Ui ui) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == CHAR_LENGTH_OF_FIND) {
                throw new DukeException(ui.showNoFindKeyword());
            }
        } else if (command.length() == CHAR_LENGTH_OF_FIND) {
            //throw exception for no task number
            throw new DukeException(ui.showNoFindKeyword());
        }
    }

    /**
     * Checks the exception for mark as done commands.
     * Throws DukeException when the command is intended to mark a task as done but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to mark a task as done.
     */
    public static void checkMarkDoneError(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == CHAR_LENGTH_OF_DONE) {
                throw new DukeException(ui.showNoTaskNumber());
            }
        } else if (command.length() == CHAR_LENGTH_OF_DONE) {
            //throw exception for no task number
            throw new DukeException(ui.showNoTaskNumber());
        }
        int curr = Parser.taskToMarkDone(command);
        if (curr > tasks.size()) {
            //check if index is within list size or throw exception
            throw new DukeException(ui.showNoSuchTask());
        }
    }

    /**
     * Checks the exception for deadline commands.
     * Throws DukeException when the command is intended to create a deadline task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to create a deadline task.
     */
    public static void checkErrorForDeadlineCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() == CHAR_LENGTH_OF_DEADLINE) {
            //throw exception for no description
            throw new DukeException(ui.showNoDescription("deadline"));
        } else if (!command.substring(8, 9).equals(" ")) {
            //throw exception for no whitespace after deadline
            throw new DukeException(ui.showNoWhitespaceForDescription("deadline"));
        } else if (command.contains(" ")) {
            // throw exception for no description and there is just trailing whitespaces
            String res = command.replaceAll(" ", "");
            if (res.length() == CHAR_LENGTH_OF_DEADLINE) {
                throw new DukeException(ui.showNoDescription("deadline"));
            }
        }
        if (!command.contains(" /by ") && command.contains("/by")) {
            //throw exception for incorrect whitespaces for /by
            throw new DukeException(ui.showNoWhitespaceForDate("deadline"));
        } else if (!command.contains(" /by ")) {
            //throw exception for no /by
            throw new DukeException(ui.showNoDate("deadline"));
        } else if (command.contains(" /by ")) {
            String[] arr = command.split(" /by ", 2);
            if (arr[0].length() == CHAR_LENGTH_OF_DEADLINE) {
                throw new DukeException(ui.showNoDescription("deadline"));
            }
        }
    }

    /**
     * Checks the exception for delete commands.
     * Throws DukeException when the command is intended to delete a task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to delete task.
     */
    public static void checkErrorForDeleteCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == CHAR_LENGTH_OF_DELETE) {
                throw new DukeException(ui.showNoTaskNumber());
            }
        } else if (command.length() == CHAR_LENGTH_OF_DELETE) {
            //throw exception for no task number
            throw new DukeException(ui.showNoTaskNumber());
        }
        int curr = Parser.taskToDelete(command);
        if (tasks.size() == 0) {
            //check if list has no task to throw exception
            throw new DukeException(ui.showNoTaskInList());
        } else if (curr > tasks.size()) {
            //check if index is within list size or throw exception
            throw new DukeException(ui.showNoSuchTask());
        }
    }

    /**
     * Checks the exception for todo commands.
     * Throws DukeException when the command is intended create a todo task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to create a todo task.
     */
    public static void checkErrorForTodoCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() == CHAR_LENGTH_OF_TODO) {
            //throw exception for no description
            throw new DukeException(ui.showNoDescription("todo"));
        } else if (!command.substring(4,5).equals(" ")) {
            //throw exception for no description and there is just trailing whitespaces
            throw new DukeException(ui.showNoWhitespaceForDescription("todo"));
        } else if (command.contains(" ")) {
            String res = command.replaceAll(" ", "");
            if (res.length() == CHAR_LENGTH_OF_TODO) {
                throw new DukeException(ui.showNoDescription("todo"));
            }
        }
    }

    /**
     * Checks the exception for event commands.
     * Throws DukeException when the command is intended to create an event task but is
     * incorrectly inputted by the user.
     *
     * @param command String of command that user input.
     * @param tasks TaskList of all the tasks currently.
     * @throws DukeException If there is a incorrectly inputted user command that is intended to create an event task.
     */
    public static void checkErrorForEventCommand(String command, TaskList tasks, Ui ui) throws DukeException {
        if (command.length() == CHAR_LENGTH_OF_EVENT) {
            //throw exception for no description
            throw new DukeException(ui.showNoDescription("event"));
        } else if (!command.substring(5,6).equals(" ")) {
            //throw exception for no whitespace after event
            throw new DukeException(ui.showNoWhitespaceForDescription("event"));
        } else if (command.contains(" ")) {
            //throw exception for no description and there is just trailing whitespaces
            String res = command.replaceAll(" ", "");
            if (res.length() == CHAR_LENGTH_OF_EVENT) {
                throw new DukeException(ui.showNoDescription("event"));
            }
        }
        if (!command.contains(" /at ") && command.contains("/at")) {
            //throw exception for wrong user input syntax for incorrect whitespaces for /at
            throw new DukeException(ui.showNoWhitespaceForDate("event"));
        } else if (!command.contains(" /at ")) {
            //throw exception for no /at
            throw new DukeException(ui.showNoDate("event"));
        } else if (command.contains(" /at ")) {
            String[] arr = command.split(" /at ", 2);
            if (arr[0].length() == CHAR_LENGTH_OF_EVENT) {
                throw new DukeException(ui.showNoDescription("event"));
            }
        }
    }

    /**
     * Creates an expense object.
     *
     * @param command User input.
     * @return Expense object.
     */
    public static Expense createExpense(String command) {
        String[] arr = command.split(" ");
        int arrSize = arr.length;
        double amount = Double.parseDouble(arr[arrSize - 1]);
        String description = arr[1];
        if (arrSize > 3) {
            for (int i = 2; i <= arrSize - 2; i++) {
                description = description + " " + arr[i];
            }
        }
        Expense e = new Expense(description, amount);
        return e;
    }

    /**
     * Reads in data file for expense.
     * @param line File line.
     * @return Expense object.
     */
    public static Expense readInExpenseFileLine(String line) {
        String[] stringArr = line.split(": ", 0);
        assert line.length() > 0 : "file path invalid in Parser line 493";
        double amount = Double.parseDouble(stringArr[1]);
        return new Expense(stringArr[0], amount);
    }

    /**
     * Returns boolean if the command is intended to be an expense command.
     *
     * @param command User input.
     * @return Boolean whether command is expense command.
     */
    public static boolean isExpenseCommand(String command) {
        return command.length() >= 7 && command.contains("expense");
    }

    /**
     * Returns boolean if command is intended to be elist command.
     *
     * @param command User input.
     * @return Boolean whether command is elist command.
     */
    public static boolean isEListCommand(String command) {
        return command.length() == 5 && command.equals("elist");
    }

    /**
     * Checks for errors in user input for expense command.
     *
     * @param command User input.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException If wrong user input
     */
    public static void checkErrorForExpenseCommand(String command, Ui ui, Storage storage) throws DukeException {
        if (command.length() == CHAR_LENGTH_OF_EXPENSE) {
            //throw exception for no description
            throw new DukeException(ui.showNoDescription("expense"));
        } else if (!command.substring(7, 8).equals(" ")) {
            //throw exception for no whitespace after event
            throw new DukeException(ui.showNoWhitespaceForExpenseDescription("expense"));
        } else if (command.contains(" ")) {
            //throw exception for no description and there is just trailing whitespaces
            String res = command.replaceAll(" ", "");
            String[] arr = command.split(" ", 2);
            if (res.length() == CHAR_LENGTH_OF_EXPENSE) {
                throw new DukeException(ui.showNoDescription("expense"));
            } else if (!arr[1].contains(" ")) {
                throw new DukeException(ui.showNoWhitespaceForAmount());
            }
        }
    }

    /**
     * Returns integer of expense index in list to delete.
     *
     * @param command User input.
     * @return Integer.
     */
    public static int expenseToDelete(String command) {
        return Integer.parseInt(command.substring(8));
    }

    /**
     * Returns boolean if command is intended to be delete expense command.
     *
     * @param command User input.
     * @return Boolean whether command is delete expense command.
     */
    public static boolean isDeleteExpenseCommand(String command) {
        return command.length() >= 8 && command.contains("delete e");
    }

    /**
     * Returns boolean if the command is intended to be help command.
     *
     * @param command User input.
     * @return Boolean whether command is help command.
     */
    public static boolean isHelpCommand(String command) {
        return command.length() == 4 && command.equals("help");
    }

    /**
     * Checks error for user input for delete expense command.
     *
     * @param command User input.
     * @param expenses Expense list.
     * @param ui Ui.
     * @throws DukeException If  wrong user input for delete expense command.
     */
    public static void checkErrorForDeleteExpenseCommand(String command, ExpenseList expenses, Ui ui)
            throws DukeException {
        if (command.contains(" ")) {
            //throw exception for no task number and there is just trailing whitespaces
            String res = command.replace(" ", "");
            if (res.length() == CHAR_LENGTH_OF_DELETE_E - 1) {
                throw new DukeException(ui.showNoExpenseNumber());
            }
        } else if (command.length() == CHAR_LENGTH_OF_DELETE_E) {
            //throw exception for no task number
            throw new DukeException(ui.showNoExpenseNumber());
        }
        int curr = Parser.expenseToDelete(command);
        if (expenses.size() == 0) {
            //check if list has no task to throw exception
            throw new DukeException(ui.showNoExpenseInList());
        } else if (curr > expenses.size()) {
            //check if index is within list size or throw exception
            throw new DukeException(ui.showNoSuchExpense());
        }
    }

    /**
     * Returns double value of income and checks for wrong number format input.
     *
     * @param command String of user command
     * @return Double value of income
     * @throws DukeException If wrong user input for income
     */
    public static double parseAndCheckErrorsForIncome(String command) throws DukeException {
        try {
            double income = Double.valueOf(command.substring(7));
            return income;
        } catch (NumberFormatException e) {
            Ui ui = new Ui();
            throw new DukeException(ui.showWrongIncomeInput());
        }
    }

    /**
     * Checks error for user input for income command.
     *
     * @param command User input.
     * @throws DukeException If there is wrong user input format.
     */
    public static void checkNoIncomeInputError(String command) throws DukeException {
        Ui ui = new Ui();
        if (command.length() == 6) {
            throw new DukeException(ui.showNoIncomeInput());
        } else if (command.contains("income") && !command.contains(" ")) {
            throw new DukeException(ui.showNoWhitespaceForIncome());
        }
        String res = command.replace(" ", "");
        if (res.length() == 6) {
            throw new DukeException(ui.showNoIncomeInput());
        }
    }
}
