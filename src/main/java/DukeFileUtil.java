import DukeTask.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileUtil {

    private static String TASK_DATA_PATH = "../../../data/taskData.txt";
    private static String PARSE_ERROR_MSG =
            " \u2639 Oops! I encountered an error while reading your previous tasks,\n"
                    + " I ignored that line of input but you should check it out...\n";
    private static String DELIMITER = "|";

    private enum TaskLetter {
        T(false),
        D(true),
        E(true);

        boolean hasTime;

        TaskLetter(boolean hasTime) {
            this.hasTime = hasTime;
        }
    }

    static ArrayList<Task> loadTasksFromDisk() {
        ArrayList<Task> existingTasks = new ArrayList<Task>();


        try {
            Scanner dataScanner = new Scanner(new File(TASK_DATA_PATH));
            String inputs[];
            int lineNumber = 1;

            while (dataScanner.hasNextLine()) {
                inputs = dataScanner.nextLine().split(DELIMITER);

                //initial validation array size after splitting according to delimiter
                validateDelimiter(inputs, lineNumber);

                //check task letter type validity
                TaskLetter letter;
                try {
                    letter = TaskLetter.valueOf(inputs[0].trim());
                } catch (IllegalArgumentException ex) {
                    throwParseException(
                            "Invalid task type encountered while parsing task file",
                            "Invalid Task Letter",
                            lineNumber);
                    continue;
                }

                //check done status validity
                validateDoneStatus(inputs[1].trim(), lineNumber);

                //validate description
                validateDescription(inputs[2], lineNumber);

                //assume time is correct for now, will be corrected post level-8 merge
                //but check for its existence
                if (letter.hasTime) {
                    validateTime(inputs, lineNumber);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.print(Duke.HORIZONTAL_LINE);
            System.out.println(" I did not find any previous data in your data directory,\n"
                    + "Creating one...");
            System.out.println(Duke.HORIZONTAL_LINE);

        } catch (DukeTaskFileParseException ex) {
            System.out.print(Duke.HORIZONTAL_LINE);
            System.out.println(ex.getDisplayMsg());
            System.out.println(Duke.HORIZONTAL_LINE);
        }

        return existingTasks;
    }

    private static void validateDelimiter(String[] inputs, int lineNumber)
            throws DukeTaskFileParseException {

        if (inputs.length != 3 && inputs.length != 4) {
            throwParseException(
                    "Missing delimiter or invalid number of delimiters encountered while parsing task file",
                    String.format("Invalid delimiter or number of delimiters \"%s\"",
                            DukeFileUtil.DELIMITER),
                    lineNumber);
        }
    }

    private static void validateDoneStatus(String status, int lineNumber)
            throws DukeTaskFileParseException {

        try {
            int doneNumber = Integer.parseInt(status);
        } catch (NumberFormatException ex) {
            throwParseException(
                    "Invalid done status number encountered while parsing task file",
                    "Your done status should only be a 0 or 1!",
                    lineNumber);
        }
    }

    private static void validateDescription(String description, int lineNumber)
        throws DukeTaskFileParseException {

        try {
            //Call Duke validate for now, will be refactored post merge
            Duke.validateTaskDescription(description);

        } catch (DukeInvalidArgumentException ex) {
            throwParseException(
                    "Invalid task description encountered when parsing task file",
                    "Invalid task description",
                    lineNumber);
        }
    }

    private static void validateTime(String[] inputs, int lineNumber)
            throws DukeTaskFileParseException {

        try {
            //Call Duke validate for now, will be refactored post merge
            Duke.validateTaskTiming(inputs[3]);

        } catch (IndexOutOfBoundsException ex) {
            throwParseException(
                    "Missing time information encountered when parsing task file",
                    "Empty time information for task requiring it",
                    lineNumber);
        } catch (DukeInvalidArgumentException ex) {
            throwParseException(
                    "Invalid task time encountered when parsing task file",
                    "Invalid task time format",
                    lineNumber);
        }
    }

    private static void throwParseException(String errorMsg, String displayMsg, int lineNumber)
            throws DukeTaskFileParseException {

        throw new DukeTaskFileParseException(
                errorMsg,
                PARSE_ERROR_MSG
                        + String.format("  lineNumber: %d\n", lineNumber)
                        + "  Error Type: " + displayMsg);
    }
}
