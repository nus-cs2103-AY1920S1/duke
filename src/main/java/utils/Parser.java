package utils;

import command.CommandCentre;
import exception.EmptyDescriptionException;
import exception.InvalidArgumentException;
import exception.InvalidCommandException;
import task.Task;
import task.TaskList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Helps to scan user input and process them into information.
 */
public class Parser {

    public static final String DATE_FORMATTER_PATTERN = "dd/MM/yyyy HHmm";
    private static final String EVENT_KEYWORD = "/at";
    private static final String DEADLINE_KEYWORD = "/by";
    private Scanner sc;
    private Ui ui;
    private CommandCentre commandCentre;
    private String arguments;

    public Parser() {
        ui = new Ui();
    }

    public void setScanner(Scanner sc) {
        this.sc = sc;
    }

    public void setCommandCentre(CommandCentre commandCentre) {
        this.commandCentre = commandCentre;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    /**
     * Reads and returns the next action in the user input.
     *
     * @return The next action string.
     */
    public String getNextAction(String input) {
        String[] inputArray = parseRawInput(input);
        String action = inputArray[0].trim();
        arguments = inputArray[1].trim();
        try {
            if (!commandCentre.contains(action)) {
                throw new InvalidCommandException(ui.buildInvalidCommandMessage());
            }

        } catch (InvalidCommandException e) {
            ui.appendMessage(e.getMessage());
            return null;
        }
        return action;
    }

    /**
     * Reads and breaks down the content after the "event" action keyword.
     * Invalid input argument types or format will be highlighted to the
     * user.
     *
     * @return If successful, an array of String of length 2 consisting of the
     * Event Task's name and deadline, a String in the format "DD/MM/YYYY
     * HHmm". Else, a null object.
     */
    public String[] parseEventDetail() {
        String taskName = arguments;
        // Separates a line "taskName /at 20/08/2019 2100" to
        // {"taskName", "20/08/2019 2100"}
        String[] taskInfo = taskName.split("\\s*" + EVENT_KEYWORD + "\\s*");
        boolean isValid = validateTaskInfo(taskInfo);
        if (isValid) {
            return taskInfo;
        } else {
            return null;
        }
    }

    /**
     * Reads and breaks down the content after the "deadline" action keyword.
     * Invalid input argument types or format will be highlighted to the user.
     *
     * @return If successful, an array of String of length 2 consisting of the
     * Deadline Task's name and deadline, a String in the format "DD/MM/YYYY
     * HHmm". Else, a null object.
     */
    public String[] parseDeadlineDetail() {
        String taskName = arguments;
        // Separates a line "taskName /by 20/08/2019 2100" to
        // {"taskName", "20/08/2019 2100"}
        String[] taskInfo = taskName.split("\\s*" + DEADLINE_KEYWORD + "\\s*");
        boolean isValid = validateTaskInfo(taskInfo);
        if (isValid) {
            return taskInfo;
        } else {
            return null;
        }
    }

    /**
     * Reads and breaks down the content after the "todo" action keyword.
     * Invalid input argument types or format will be highlighted to the
     * user.
     *
     * @return If successful, a String representing the Task's name.
     * Else, a null object.
     */
    public String parseTodoDetail() {
        String taskName = arguments;
        boolean isValid = validateDescriptionNotEmpty(taskName);
        if (isValid) {
            return taskName;
        } else {
            return null;
        }
    }

    /**
     * Reads the next integer from user input where the integer must be
     * a valid index for the tasks in TaskList.
     *
     * @return If successful, an Integer representing the task index. Else, a null object.
     */
    public Integer parseTaskIdx() {
        String infoString = arguments;
        TaskList taskList = TaskList.newInstance();
        int idx;
        try {
            idx = Integer.parseInt(infoString) - 1;
        } catch (NumberFormatException e) {
            ui.appendMessage(ui.buildInvalidTaskListIndexMessage(taskList.size()));
            return null;
        }

        try {
            taskList.get(idx);
        } catch (IndexOutOfBoundsException e) {
            if (taskList.isEmpty()) {
                ui.appendMessage(ui.buildEmptyTaskListMessage());
            } else {
                ui.appendMessage(ui.buildInvalidTaskListIndexMessage(taskList.size()));
            }
            return null;
        }

        return idx;
    }

    /**
     * Reads the remaining String from Scanner as the keyword for 'Find' action.
     * Invalid input argument types or format will be highlighted to the user.
     *
     * @return If successful, the String representation of the keyword. Else, a null object.
     */
    public String parseKeyword() {
        String keyword = arguments.trim();
        boolean isValid = validateDescriptionNotEmpty(keyword);
        if (isValid) {
            return keyword;
        } else {
            return null;
        }
    }

    public String[] parseSortInfo() {
        String keyword = arguments.trim();
        String[] sortInfo = parseRawInput(keyword);
        return sortInfo;
    }


    public int parseKeywordAsSortCategory(String keyword) {
        switch (keyword) {
        case "name":
            return Task.NAME_CATEGORY;

        case "deadline":
            return Task.DEADLINE_CATEGORY;

        case "type":
            return Task.TYPE_CATEGORY;

        case "status":
            return Task.STATUS_CATEGORY;

        default:
            try {
                throw new InvalidArgumentException(ui.buildInvalidSortCategoryMessage());
            } catch (InvalidArgumentException e) {
                ui.appendMessage(e.getMessage());
                return -1;
            }
        }
    }

    private boolean validateDescriptionNotEmpty(String keyword) {
        try {
            if (keyword.isEmpty()) {
                throw new EmptyDescriptionException(ui.buildEmptyDescriptionMessage());
            }
            return true;
        } catch (EmptyDescriptionException e) {
            ui.appendMessage(e.getMessage());
            return false;
        }
    }

    private boolean validateTaskInfo(String[] taskInfo) {
        String dateString;
        try {
            if (validateArgumentsNotBlank(taskInfo)) {
                throw new EmptyDescriptionException(ui.buildEmptyDescriptionMessage());
            }
            dateString = taskInfo[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.appendMessage(ui.buildIncorrectArgumentsMessage());
            return false;

        } catch (EmptyDescriptionException e) {
            ui.appendMessage(e.getMessage());
            return false;
        }

        try {
            validateDateFormat(dateString);
        } catch (ParseException e) {
            ui.appendMessage(ui.buildIncorrectDateFormatMessage());
            return false;

        }
        return true;
    }

    private void validateDateFormat(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER_PATTERN);
        sdf.setLenient(false);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(dateString));
    }

    private boolean validateArgumentsNotBlank(String[] taskInfo) {
        return taskInfo.length == 0 || taskInfo[0].isBlank() || taskInfo[1].isBlank();
    }

    /**
     * Splits the raw user input String into the first action keyword and its arguments.
     *
     * @param input The raw user input sent to the Duke chat bot via the javafx GUI.
     * @return An array of size two which contains the "action" as the first element and
     * "arguments" as the second element. If there are no additional arguments to the
     * action, the second element will simply be an empty string.
     */
    private String[] parseRawInput(String input) {
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c != ' ') {
                first.append(c);
            } else {
                second.append(input.substring(i));
                break;
            }
        }
        return new String[]{first.toString(), second.toString().trim()};
    }


}
