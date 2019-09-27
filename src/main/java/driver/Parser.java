package driver;

import command.*;
import exception.DukeException;
import exception.NoNumberException;
import exception.IncompleteFindCommandException;
import exception.IncompleteAddCommandException;
import formatter.TimeFormatter;
import task.DeadlineTask;
import task.EventTask;
import task.TodoTask;
import java.util.Date;
import java.text.ParseException;

/**
 * Parser checks the user input and converts it into command format so that Duke can execute the program.
 */

class Parser {
    static String sadSmile = "\u2639";

    /**
     * Returns Command to execute based on user input.
     *
     * @param userInput String of what the user wants to do
     * @return Command so that Duke can carry out the program.*
     */

    static Command parse(String userInput) {
        String trimmed = userInput.trim();
        try {
            if (trimmed.equalsIgnoreCase("list")) {
                return new ListCommand();
            } else if (trimmed.startsWith("done")) {
                int index = parseDone(trimmed);
                return new DoneCommand(index);
            } else if (trimmed.startsWith("delete")) {
                int index = parseDelete(trimmed);
                return new DeleteCommand(index);
            } else if (trimmed.startsWith("find")) {
                String mystery = parseFind(trimmed);
                return new SearchCommand(mystery);
            } else if (trimmed.equalsIgnoreCase("stats")) {
                return new StatisticsCommand();
            } else if (trimmed.startsWith("todo")) {
                TodoTask todoTask = parseTodoTask(trimmed);
                return new AddCommand(todoTask);
            } else if (trimmed.startsWith("deadline")) {
                DeadlineTask deadlineTask = parseDeadlineTask(trimmed);
                return new AddCommand(deadlineTask);
            } else if (trimmed.startsWith("event")) {
                EventTask eventTask = parseEventTask(trimmed);
                return new AddCommand(eventTask);
            } else if (trimmed.startsWith("refresh")) {
                return new RefreshCommand();
            } else if (trimmed.startsWith("help")) {
                return new HelpCommand();
            } else {
                throw new DukeException();
            }
        } catch (Exception err) {
            return new ErrorCommand(err);
        }
    }

    /**
     * Returns EventTask after parsing content into correct categories.
     *
     * @param input String of what the user wants to do
     * @return EventTask
     * @throws IncompleteAddCommandException if the user input is incomplete
     * @throws ParseException if the dates are unreadable
     */

    private static EventTask parseEventTask(String input) throws IncompleteAddCommandException, ParseException {
        String[] firstCheck = input.split(" ");
        //First check if the user actually input anything
        if (firstCheck.length < 2) {
            throw new IncompleteAddCommandException(
                    sadSmile + " You did not tell me what I should write for the event task");
        }
        String secondCheck = input;
        //Next check if the user used the at keyword
        if (!secondCheck.contains("/from")) {
            throw new IncompleteAddCommandException(
                    sadSmile + " You did not tell me when this event is. "
                            + "Please include keyword - /from.");
        }
        //Next check if there are 2 timings
        String getRidOfEventWord = input.substring(6);
        String[] holder = getRidOfEventWord.split("/from");
        String actualTask = holder[0];
        String thirdCheck = holder[1];
        if (!thirdCheck.contains(" - ")) {
            throw new IncompleteAddCommandException(
                    sadSmile + " You did not tell me the start and end timings for this event. "
                            + "Please include dates like this: 11/12/2019 0011 -  12/12/2019 0011.");
        }
        //Last check if the dates are in the correct format
        String[] fourthCheck = thirdCheck.split(" - ");
        Date start = TimeFormatter.convertToDate(fourthCheck[0]);
        Date end = TimeFormatter.convertToDate(fourthCheck[1]);
        return new EventTask(actualTask,start,end);
    }

    /**
     * Returns DeadlineTask after parsing content into correct categories.
     *
     * @param input String of what the user wants to do
     * @return DeadlineTask
     * @throws IncompleteAddCommandException if the user input is incomplete
     * @throws ParseException if the dates are unreadable
     */


    private static DeadlineTask parseDeadlineTask(String input) throws IncompleteAddCommandException, ParseException {
        String[] firstCheck = input.split(" ");
        //First check if the user actually input anything
        if (firstCheck.length < 2) {
            throw new IncompleteAddCommandException(
                    sadSmile + " You did not tell me what I should write for the deadline task");
        }
        String secondCheck = input;
        //Next check if the user used the by keyword
        if (!secondCheck.contains("/by")) {
            throw new IncompleteAddCommandException(
                    sadSmile + " You did not tell me when you want to finish this task by. "
                            + "Please include keyword - /by.");
        }
        //Lastly check if the date is in the proper format
        String getRidOfDeadlineWord = input.substring(9);
        String[] thirdCheck = getRidOfDeadlineWord.split("/by");
        String actualTask = thirdCheck[0];
        Date deadLine = TimeFormatter.convertToDate(thirdCheck[1]);
        return new DeadlineTask(actualTask,deadLine);
    }

    /**
     * Returns TodoTask after parsing content into correct categories.
     *
     * @param input String of what the user wants to do
     * @return TodoTask
     * @throws IncompleteAddCommandException if the user input is incomplete
     */

    private static TodoTask parseTodoTask(String input) throws IncompleteAddCommandException {
        String[] workingCopy = input.split(" ");
        if (workingCopy.length < 2) {
            throw new IncompleteAddCommandException(sadSmile + " You did not tell me what to do");
        }
        String cleanedInput = input.substring(5);
        return new TodoTask(cleanedInput);
    }

    /**
     * Returns index of task to be done.
     *
     * @param input String of what the user wants to do
     * @return int
     * @throws NoNumberException if the user input is incomplete
     */

    private static int parseDone(String input) throws NoNumberException {
        String[] numTasks = input.split(" ");
        //Check if user input a number
        if (numTasks.length < 2) {
            throw new NoNumberException("do");
        }
        String numberAsString = numTasks[1];
        int number = Integer.parseInt(numberAsString);
        return number - 1;
    }

    /**
     * Returns index of task to be deleted.
     *
     * @param input String of what the user wants to do
     * @return int
     * @throws NoNumberException if the user input is incomplete
     */

    private static int parseDelete(String input) throws NoNumberException {
        String[] numTasks = input.split(" ");
        //Check if user input a number
        if (numTasks.length < 2) {
            throw new NoNumberException("delete");
        }
        String numberAsString = numTasks[1];
        int number = Integer.parseInt(numberAsString);
        return number - 1;
    }

    /**
     * Returns the string to be searched for.
     *
     * @param input String of what the user wants to do
     * @return String
     * @throws IncompleteFindCommandException if the user input is incomplete
     */

    private static String parseFind(String input) throws IncompleteFindCommandException {
        String[] numTasks = input.split(" ");
        //Check if user input a number
        if (numTasks.length < 2) {
            throw new IncompleteFindCommandException();
        }
        String whatTheUserWantsToFind = numTasks[1];
        return whatTheUserWantsToFind;
    }








}


