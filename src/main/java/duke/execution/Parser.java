package duke.execution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.execution.Ui;
import duke.execution.command.Command;
import duke.execution.command.ByeCommand;
import duke.execution.command.DeadlineCommand;
import duke.execution.command.EventCommand;
import duke.execution.command.DoneCommand;
import duke.execution.command.DeleteCommand;
import duke.execution.command.FindCommand;
import duke.execution.command.HelpCommand;
import duke.execution.command.ListCommand;
import duke.execution.command.ToDoCommand;
import duke.execution.command.ExpensesCommand;
import duke.exceptions.DukeException;

public class Parser {

    /**
     * Makes sense of the user input and finds out what command
     * the user typed in.
     *
     * @param line Input by user.
     * @return Returns the command that comes from the user.
     * @throws DukeException Prints out the message to tell the user
     *                       what was wrong with the input by the user.
     */
    public static Command parse(String line) throws DukeException {
        assert line != null;
        String properInput = line.trim().toLowerCase();
        String[] inputArray = properInput.split(" ");
        String taskType = inputArray[0];
        assert taskType != null;
        if (!isValidTaskType(taskType)) {
            Ui.printIndent();
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't \n"
                    + "know what that means :-(\n"
                    + "    I can only do these functions for now: \n \n"
                    + "    Todo \n" + "        Eg. todo __(task)__\n"
                    + "    Event \n" + "        Eg. event __(task)__\n"
                    + "            /at _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Deadline \n" + "        Eg. deadline __(task)__\n"
                    + "            /by _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                    + "    Done \n" + "        Eg. done __(number)__\n"
                    + "    Find \n" + "        Eg. find __(keyword)__\n"
                    + "    Expenses \n" + "        Eg. expenses __$(amount)__\n"
                    + "            /on __(category)__\n"
                    + "    List \n" + "    Help\n" + "    Bye\n");
        }

        //variable refers to either date or what the expenditure is spent on.
        int variableIndex = 0;
        String variable = inputArray[0];
        String description = getDescriptionOfTask(properInput);
        if (taskType.equals("deadline") || taskType.equals("event") || taskType.equals("expenses")) {
            int slashIndex = properInput.indexOf("/");
            variableIndex = slashIndex + 4;
            if (properInput.length() > variableIndex) {
                variable = properInput.substring(variableIndex);
            } else {
                throw new DukeException("Sorry! Please follow the correct format! :)))");
            }
        }
        switch (taskType) {
        case "todo":
            if (isValidToDoCommand(properInput)) {
                return new ToDoCommand(description);
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct todo format! :<\n"
                        + "    Todo \n"
                        + "        Eg. todo __(task)__\n");
            }
        case "deadline":
            if (isValidDeadlineCommand(properInput) && isValidDateFormat(properInput)) {
                return new DeadlineCommand(description, formatDate(variable));
            } else {
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct deadline format! :<\n"
                        + "    Deadline \n"
                        + "        Eg. deadline __(task)__ /by \n"
                        + "           _(dd/MM/yyyy)_(hhmm)__\n");
            }
        case "event":
            if (isValidEventCommand(properInput) && isValidDateFormat(properInput)) {
                return new EventCommand(description, formatDate(variable));
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct event format! :<\n"
                        + "    Event \n"
                        + "        Eg. event __(task)__ /at \n"
                        + "           _(dd/MM/yyyy)_(hhmm)__\n");
            }
        case "delete":
            if (isValidDeleteCommand(properInput)) {
                return new DeleteCommand(taskType, description);
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct delete format! :<\n"
                        + "    Delete \n"
                        + "        Eg. delete __(number)__ or delete all\n");
            }
        case "find":
            if (isValidFindCommand(properInput)) {
                return new FindCommand(taskType, description);
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow\n"
                        + "the correct find format! :<\n"
                        + "    Find \n" + "        Eg. find __(keyword)__\n");
            }
        case "done":
            if (isValidDoneCommand(properInput)) {
                return new DoneCommand(taskType, description);
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct done format! :<\n"
                        + "    Done \n"
                        + "        Eg. done __(number[not expenses task])__\n");
            }
        case "expenses":
            if (isValidExpensesCommand(properInput)) {
                System.out.println("asda");
                return new ExpensesCommand(description, variable);
            } else {
                Ui.printIndent();
                throw new DukeException("☹ OOPSY DAISY!!! Please follow \n"
                        + "the correct done format! :<\n"
                        + "    Expenses \n"
                        + "        Eg. expenses __$(amount)__ /on __(category)__\n");
            }
        case "list":
            return new ListCommand(description);
        case "bye":
            Command byeCommand = new ByeCommand(description);
            byeCommand.exitSwitch();
            return  byeCommand;
        case "help":
            return new HelpCommand(description);
        default:
            Ui.printIndent();
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't \n"
                    + "know what that means :-(\n"
                    + "    I can only do these functions for now: \n \n"
                    + "    Todo \n" + "        Eg. todo __(task)__\n"
                    + "    Event \n" + "        Eg. event __(task)__\n"
                    + "            /at _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Deadline \n" + "        Eg. deadline __(task)__\n"
                    + "            /by _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                    + "    Done \n" + "        Eg. done __(number)__\n"
                    + "    Find \n" + "        Eg. find __(keyword)__\n"
                    + "    Expenses \n" + "        Eg. expenses __$(amount)__\n"
                    + "            /on __(category)__\n"
                    + "    List \n" + "    Help\n" + "    Bye\n");
        }
    }

    /**
     * Gets the slash index from the input, if available.
     * This is so that we can determine the dates easily.
     *
     * @param text Input by the user.
     * @return Returns the index of the slash.
     */
    public static int getSlashIndex(String text) {
        return text.indexOf("/");
    }

    /**
     * Gets the day numbers from the input.
     *
     * @param text Date and Time.
     * @return Returns the day numbers.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static int getDayDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 4)) && (text.length() > (getSlashIndex(text) + 6))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 4, getSlashIndex(text) + 6));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date\n"
                    + " format as follows: dd/mm/yyyy hhmm.");
        }
    }

    /**
     * Gets the month numbers from the input.
     *
     * @param text Date and Time.
     * @return Returns the month numbers.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static int getMonthDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 7)) && (text.length() > (getSlashIndex(text) + 9))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 7, getSlashIndex(text) + 9));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date\n"
                    + " format as follows: dd/mm/yyyy hhmm.");
        }
    }

    /**
     * Gets the year numbers from the input.
     *
     * @param text Date and Time.
     * @return Returns the year numbers.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static int getYearDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 10)) && (text.length() > (getSlashIndex(text) + 14))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 10, getSlashIndex(text) + 14));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date\n"
                    + " format as follows: dd/mm/yyyy hhmm.");
        }
    }

    /**
     * Gets the hour numbers from the input.
     *
     * @param text Date and Time.
     * @return Returns the hour numbers.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static int getHourDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 15)) && (text.length() > (getSlashIndex(text) + 17))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 15, getSlashIndex(text) + 17));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date\n"
                    + " format as follows: dd/mm/yyyy hhmm.");
        }
    }


    /**
     * Gets the minute numbers from the input.
     *
     * @param text Date and Time.
     * @return Returns the minute numbers.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static int getMinuteDateNumbers(String text) throws DukeException {
        assert text != null;
        if (text.length() >= (getSlashIndex(text) + 17)) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 17));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date\n"
                    + " format as follows: dd/mm/yyyy hhmm.");
        }
    }


    /**
     * Checks whether the numbers are within the limit of days,
     * months, years, hours and minutes.
     *
     * @param text Date and Time.
     * @return Returns a boolean that checks whether the numbers are valid or not.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static boolean isValidDateNumbers(String text) throws DukeException {
        assert text != null;
        int dayDate = getDayDateNumbers(text);
        int monthDate = getMonthDateNumbers(text);
        int yearDate = getYearDateNumbers(text);
        int timeHour = getHourDateNumbers(text);
        int timeMin = getMinuteDateNumbers(text);
        return  dayDate > 0 && dayDate <= 31 && monthDate > 0 && monthDate <= 12
                && timeHour > 0 && timeHour <= 24 && timeMin >= 0 && timeMin <= 59
                && yearDate >= 2019;
    }

    /**
     * Checks whether the dates and numbers given are valid or not.
     *
     * @param text Dates and Time.
     * @return Returns a boolean that checks whether the numbers are valid or not.
     * @throws DukeException If the numbers are not valid, then a DukeException will
     *                       be thrown to inform the user.
     */
    public static boolean isValidDateFormat(String text) throws DukeException {
        assert text != null;
        if (isValidDateNumbers(text)) {
            return true;
        } else {
            Ui.printIndent();
            throw new DukeException("Invalid Date Format!\n"
                    + "    There are at most 31 days, 12 months,\n"
                    + " 23 hours and 59 minutes! \n"
                    + "And remember that the year is 2019!\n"
                    + "    Please try again! Thank you! :)");
        }
    }

    /**
     * Method to format the date into the appropriate format.
     * For example, 10/02/2012 1800 to 10th of February 2012, 6:00 pm.
     *
     * @param date Takes in a valid date to format it.
     * @return Returns the correctly formatted date with the
     *     appropriate strings.
     */
    public static String formatDate(String date) {
        assert date != null;
        String formatted = date;
        if (!date.contains(")")) {
            try {
                Date d = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(date);
                String day = new SimpleDateFormat("dd").format(d);
                String month = new SimpleDateFormat("MMMMMMMMMMMMMMM").format(d);
                String year = new SimpleDateFormat("yyyy").format(d);
                String time = new SimpleDateFormat("h:mm a").format(d).toLowerCase();
                String endOfDate;
                int dayInteger = Integer.parseInt(day);

                if (dayInteger % 10 == 1 && dayInteger != 11) {
                    endOfDate = "st";
                } else if (dayInteger % 10 == 2 && dayInteger != 12) {
                    endOfDate = "nd";
                } else if (dayInteger % 10 == 3 && dayInteger != 13) {
                    endOfDate = "rd";
                } else {
                    endOfDate = "th";
                }
                formatted = dayInteger + endOfDate + " of " + month + " " + year + ", " + time;
            } catch (ParseException e) {
                Ui.printIndent();
                System.out.println(e.getMessage());
                Ui.printIndent();
                System.out.println("That is the wrong date format! >:-(");
            }
        } else {
            //isCorrectFormat = true;
        }
        return formatted;
    }

    /**
     * Checks whether the task type is at least one of the functions available.
     *
     * @param taskType User input.
     * @return Returns a boolean that checks whether the task type is valid or not.
     */
    public static boolean isValidTaskType(String taskType) {
        assert  taskType != null;
        if (taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline")
                || taskType.equals("delete") || taskType.equals("done") || taskType.equals("find")
                || taskType.equals("list") || taskType.equals("bye") || taskType.equals("expenses")
                || taskType.equals("help")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Sieves out the description of the task and returns it.
     *
     * @param text User input.
     * @return Returns the description of the task without task type and dates/variables.
     * @throws DukeException Throws out a DukeException if the text doesnt follow any of the
     *                       task formats.
     */
    public static String getDescriptionOfTask(String text) throws DukeException {
        assert text != null;
        int spaceIndex = text.indexOf(" ");
        if ((text.contains("deadline") && isValidDeadlineCommand(text))
                || (text.contains("event") && isValidEventCommand(text))
                || (text.contains("expenses") && isValidExpensesCommand(text))) {
            int slashIndex = text.indexOf("/");
            return text.substring(spaceIndex + 1, slashIndex - 1);
        } else if (text.contains("todo") || text.contains("done")
                || text.contains("delete") || text.contains("find")) {
            return text.substring(spaceIndex + 1);
        } else {
            return text;
        }
    }

    /**
     * Checks whether the to do command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the to do command is valid.
     */
    public static boolean isValidToDoCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.length() > 5;
    }

    /**
     * Checks whether the deadline command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the deadline command is valid.
     */
    public static boolean isValidDeadlineCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.contains("/") && text.contains("by");
    }

    /**
     * Checks whether the event command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the event command is valid.
     */
    public static boolean isValidEventCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.contains("/") && text.contains("at");
    }

    /**
     * Checks whether the delete command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the delete command is valid.
     * @throws DukeException If the format is wrong, a DukeException will be thrown
     *                       to inform the user and to retype it properly again.
     * @throws NumberFormatException If the user types in a character that is not a number,
     *                               an exception will be thrown to inform the user.
     */
    public static boolean isValidDeleteCommand(String text) throws DukeException, NumberFormatException {
        assert text != null;
        if (text.length() < 7) {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please add an 'all' or\n"
                    + " a valid number after the delete word! :)");
        } else {
            char[] valueArray = text.substring(7).toCharArray();
            boolean isNumber = false;
            for (int i = 0; i < valueArray.length; i++) {
                isNumber = false;
                if (Character.isDigit(valueArray[i])) {
                    isNumber = true;
                }
            }
            if (text.contains("all")) {
                return text.contains(" ");
            } else if (isNumber && Integer.parseInt(text.substring(7)) > 0) {
                return text.contains(" ") && Integer.parseInt(text.substring(7)) <= CompleteList.listOfPlans.size();
            } else {
                Ui.printIndent();
                throw new DukeException("Wrong Format! Please add an 'all' or\n"
                        + "a valid number after the delete word! Thank You :)");
            }
        }
    }

    /**
     * Checks whether the find command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the find command is valid.
     */
    public static boolean isValidFindCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.length() > 5;
    }

    /**
     * Checks whether the done command input given is valid
     * and follows the format that is provided.
     *
     * @param text User input.
     * @return Returns a boolean that tells us whether the done commmand is valid.
     * @throws DukeException If the format is wrong, a DukeException will be thrown
     *                       to inform the user and to retype it properly again.
     */
    public static boolean isValidDoneCommand(String text) throws DukeException {
        assert text != null;
        if (text.length() < 5) {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please add a \n"
                    + "valid number after the done word! :)");
        } else {
            char[] valueArray = text.substring(5).toCharArray();
            boolean isNumber = false;
            for (int i = 0; i < valueArray.length; i++) {
                isNumber = false;
                if (Character.isDigit(valueArray[i])) {
                    isNumber = true;
                }
            }
            if (isNumber && Integer.parseInt(text.substring(5)) > 0) {
                return text.contains(" ") && Integer.parseInt(text.substring(5)) <= CompleteList.listOfPlans.size()
                        && isValidDoneNumberGiven(text.substring(5));
            } else {
                Ui.printIndent();
                throw new DukeException("Wrong Format! Please add a valid \n"
                        + "number that is a task (and not an expenses!!)\n"
                        + " after the done word! Thank You :)");
            }
        }
    }

    /**
     * Checks whether the expenses command given is valid
     * and follows the format provided.
     *
     * @param text User input
     * @return Returns a boolean that tells us if the input is valid.
     */
    public static boolean isValidExpensesCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.contains("/") && text.contains("on") && text.length() > 20;
    }

    /**
     * Checks whether the done number given is valid. In other
     * words, checks if done number is given to either todo, event
     * or deadline.
     *
     * @param num Number given by the user.
     * @return Returns a boolean that tells the user whether the number given
     *         satisfies the conditions.
     */
    public static boolean isValidDoneNumberGiven(String num) {
        int taskNumber = Integer.parseInt(num);
        try {
            BufferedReader br = new BufferedReader(new FileReader(Storage.file));
            String text;
            int counter = 0;
            int lineCounter = 0;
            while ((text = br.readLine()) != null) {
                lineCounter++;
                if (lineCounter == taskNumber) {
                    if (text.contains("[Expenses]")) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}
