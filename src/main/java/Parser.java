import java.time.LocalDateTime;

/**
 * Parses Strings to identify different sub-commands in a given input command.
 */
public class Parser {
    /**
     * Parses the instruction given in an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the instruction in the given String.
     */
    static String parseInstruction(String input) {
        return input.split(" ", 2)[0];
    }
    
    /**
     * Parses the index given in an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the index integer value in the given String.
     */
    static int parseIndex(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }
    
    /**
     * Parses the description given in an input String.
     *
     * @param input The inputted String given by the user.
     * @param isToDo A boolean with value true if the Task is a ToDoTask, and false otherwise.
     * @return Returns the description in the given String.
     */
    static String parseDescription(String input, boolean isToDo) {
        return isToDo
                ? input.split(" ", 2)[1]
                : input.split("deadline|event", 2)[1];
    }
    
    /**
     * Parses the content in the description of an input String.
     *
     * @param input The inputted String given by the user.
     * @return Returns the content in the description of a given String.
     */
    static String parseContent(String input) {
        return input.split("/by|/at", 2)[0].strip();
    }
    
    /**
     * Parses the time in the description of an input String.
     * Time is parsed as a LocalDateTime, and is assumed to be formatted correctly.
     *
     * @param input The inputted String given by the user.
     * @return Returns a LocalDateTime object representing the time of the Task.
     */
    static LocalDateTime parseTime(String input) {
        String taskTimeBeforeParse = input.split("/by|/at", 2)[1].strip();
        String[] taskTimeParsed = taskTimeBeforeParse.split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]),
                Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
    
    /**
     * Parses the data stored in the file saved on the hard drive.
     * Data is assumed to be formatted correctly.
     *
     * @param input The inputted String given by the user.
     * @return Returns a String array containing the values stored in the given formatted String.
     */
    static String[] parseStoredLine(String input) {
        return input.split(" \\| ");
    }
    
    /**
     * Parses the instruction in the String array of values.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @return Returns the instruction value, as a String.
     */
    static String parseStoredInstruction(String[] inputElements) {
        return inputElements[0];
    }
    
    /**
     * Parses a Task to determine if it should be marked as done.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @param task The given Task to be marked as done.
     */
    static void parseTaskForMarking(String[] inputElements, Task task) {
        if (inputElements[1].equals("+")) {
            task.markAsDone();
        }
    }
    
    /**
     * Parses the time of a given Task.
     * The Task is assumed to be correctly formatted.
     *
     * @param inputElements The inputted String array containing the stored values of the formatted String.
     * @return Returns a LocalDateTime object representing the date of the Task.
     */
    static LocalDateTime parseStoredTime(String[] inputElements) {
        String[] taskTimeParsed = inputElements[3].split("[ /]");
        return LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]),
                Integer.parseInt(taskTimeParsed[1]), Integer.parseInt(taskTimeParsed[0]),
                Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
    }
}
