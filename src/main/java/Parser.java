/**
 * Simulates a system that parses user input into
 * commands for the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class Parser {

    /**
     * Parsers user input into appropriate commands.
     * @param fullCommand The user input in String format.
     * @return The command to be carried out by Duke.
     */
    public static Command parse(String fullCommand) {

        Command returnCommand = new DummyCommand();

        if(isBye(fullCommand)) {
            returnCommand = new ByeCommand();
        } else if(isList(fullCommand)) {
            returnCommand = new ListCommand();
        } else if(isDone(fullCommand)) {
            returnCommand = processDoneCommand(fullCommand);
        } else if(isDelete(fullCommand)) {
            returnCommand = processDeleteCommand(fullCommand);
        } else if(isFind(fullCommand)) {
            returnCommand = processFindCommand(fullCommand);
        } else {
            try {
                returnCommand = processAddCommand(fullCommand);
            } catch(InvalidInputException e) {
                System.out.println("InvalidInputException occurred: " + e);
                System.out.println();
            }
        }

        return returnCommand;

    }

    /**
     * Processes a done command for a task.
     * @param fullCommand The user input for the done command.
     * @return The done command.
     */
    private static Command processDoneCommand(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ");

        int taskNumber = Integer.parseInt(splitCommand[1]);

        Command doneCommand = new DoneCommand(taskNumber);

        return doneCommand;
    }

    /**
     * Processes a delete command for a task.
     * @param fullCommand The user input for the delete command.
     * @return The delete command.
     */
    private static Command processDeleteCommand(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ");

        int taskNumber = Integer.parseInt(splitCommand[1]);

        Command deleteCommand = new DeleteCommand(taskNumber);

        return deleteCommand;
    }

    /**
     * Processes an add command for a task.
     * @param fullCommand The user input for the add command.
     * @return The add command.
     * @throws InvalidInputException The exception to be thrown if the input is invalid.
     */
    private static Command processAddCommand(String fullCommand) throws InvalidInputException {

        // Block for catching input mismatch errors.
        if (!fullCommand.startsWith("todo")) {
            if (!fullCommand.startsWith("deadline")) {
                if (!fullCommand.startsWith("event")) {
                    throw new InputMismatchException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }

        String[] splitInput = fullCommand.split(" ");

        // Block for catching empty description errors.
        if (splitInput.length <= 1) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a task cannot be empty.");
        }

        if (isToDo(fullCommand)) {
            String taskType = "todo";
            String taskDescription = processTaskDescription(fullCommand);

            Command addCommand = new AddCommand(taskType, taskDescription);

            return addCommand;
        } else if (isDeadline(fullCommand)) {
            String[] splitBySlash = fullCommand.split("/");

            String taskType = "deadline";
            String taskDateTime = processDateTime(splitBySlash);
            String taskDescription = processTaskDescription(splitBySlash[0]);

            Command addCommand = new AddCommand(taskType, taskDescription, taskDateTime);

            return addCommand;
        } else {
            String[] splitBySlash = fullCommand.split("/");

            String taskType = "event";
            String taskDateTime = processDateTime(splitBySlash);
            String taskDescription = processTaskDescription(splitBySlash[0]);

            Command addCommand = new AddCommand(taskType, taskDescription, taskDateTime);

            return addCommand;
        }
    }

    /**
     * Processes a find command for a word.
     * @param fullCommand The user input for the find command.
     * @return The find command.
     */
    private static Command processFindCommand(String fullCommand) {
        String[] splitCommand = fullCommand.split(" ");

        String word = splitCommand[1];

        Command findCommand = new FindCommand(word);

        return findCommand;
    }

    /**
     * Splits the input String into appropriate format.
     * @param input The user input.
     * @return The description of the action to be carried out.
     */
    private static String processTaskDescription(String input) {
        String[] splitInput = input.split(" ");
        int arrSize = splitInput.length;

        String returnString = splitInput[1];

        for (int i = 2; i < arrSize; i++) {
            returnString += " " + splitInput[i];
        }

        return returnString;
    }

    /**
     * Processes the date time into appropriate format.
     * @param splitBySlash The split input.
     * @return The appropriate date time format.
     */
    private static String processDateTime(String[] splitBySlash) {
        String day = splitBySlash[1];
        String month = splitBySlash[2];

        String[] splitYearTime = splitBySlash[3].split(" ");

        String year = splitYearTime[0];
        String time = splitYearTime[1];

        DateTime dateTime = new DateTime(day, month, year, time);
        dateTime.format();

        return dateTime.toString();
    }

    private static boolean isBye(String str) {
        return str.equals("bye") || str.equals("Bye");
    }

    private static boolean isList(String str) {
        return str.equals("list");
    }

    private static boolean isDone(String str) {
        return str.startsWith("done");
    }

    private static boolean isDelete(String str) {
        return str.startsWith("delete");
    }

    private static boolean isToDo(String str) {
        return str.startsWith("todo");
    }

    private static boolean isDeadline(String str) {
        return str.startsWith("deadline");
    }

    private static boolean isFind(String str) {
        return str.startsWith("find");
    }

}
