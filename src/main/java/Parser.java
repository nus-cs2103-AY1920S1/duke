import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

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
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                    + "    I can only do these functions for now: \n \n"
                    + "    Todo \n" + "        Eg. todo __(task)__\n"
                    + "    Event \n" + "        Eg. event __(task)__ /at _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Deadline \n" + "        Eg. deadline __(task)__ /by _(dd/MM/yyyy)_(hhmm)__\n"
                    + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                    + "    Done \n" + "        Eg. done __(number)__\n"
                    + "    Find \n" + "        Eg. find __(keyword)__\n"
                    + "    List \n" + "    Bye\n");
        }
        int dateIndex = 0;
        String date = inputArray[0];
        String description = getDescriptionOfTask(properInput);
        if (taskType.equals("deadline") || taskType.equals("event")) {
            int slashIndex = properInput.indexOf("/");
            dateIndex = slashIndex + 4;
            if (properInput.length() > dateIndex) {
                date = properInput.substring(dateIndex);
            } else {
                throw new DukeException("    Wrong Format! Please follow the correct format! :)))");
            }
        }
        switch (taskType) {
            case "todo":
                if (isValidToDoCommand(properInput)) {
                    return new ToDoCommand(description);
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct todo format! :<\n"
                            + "    Todo \n" + "        Eg. todo __(task)__\n");
                }
            case "deadline":
                if (isValidDeadlineCommand(properInput) && isValidDateFormat(properInput)) {
                        return new DeadlineCommand(description, formatDate(date));
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct deadline format! :<\n"
                            + "    Deadline \n" + "        Eg. deadline __(task)__ /by _(dd/MM/yyyy)_(hhmm)__\n");
                }
            case "event":
                if (isValidEventCommand(properInput) && isValidDateFormat(properInput)) {
                        return new EventCommand(description, formatDate(date));
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct event format! :<\n"
                            + "    Event \n" + "        Eg. event __(task)__ /at _(dd/MM/yyyy)_(hhmm)__\n");
                }
            case "delete":
                if (isValidDeleteCommand(properInput)) {
                    return new DeleteCommand(taskType, description);
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct delete format! :<\n"
                            + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n");
                }
            case "find":
                if (isValidFindCommand(properInput)) {
                    return new FindCommand(taskType, description);
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct find format! :<\n"
                            + "    Find \n" + "        Eg. find __(keyword)__\n");
                }
            case "done":
                if (isValidDoneCommand(properInput)) {
                    return new DoneCommand(taskType, description);
                } else {
                    Ui.printIndent();
                    throw new DukeException("☹ OOPSY DAISY!!! Please follow the correct done format! :<\n"
                            + "    Done \n" + "        Eg. done __(number)__\n");
                }
            case "list":
                return new ListCommand(description);
            case "bye":
                Command byeCommand = new ByeCommand(description);
                byeCommand.exitSwitch();
                return  byeCommand;
            default:
                Ui.printIndent();
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-("
                        + "    I can only do these functions for now: \n \n"
                        + "    Todo \n" + "        Eg. todo __(task)__\n"
                        + "    Event \n" + "        Eg. event __(task)__ /at _(dd/MM/yyyy)_(hhmm)__\n"
                        + "    Deadline \n" + "        Eg. deadline __(task)__ /by _(dd/MM/yyyy)_(hhmm)__\n"
                        + "    Delete \n" + "        Eg. delete __(number)__ or delete all\n"
                        + "    Done \n" + "        Eg. done __(number)__\n"
                        + "    Find \n" + "        Eg. find __(keyword)__\n"
                        + "    List \n" + "    Bye\n");
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
     *
     *
     * @param text
     * @return
     * @throws DukeException
     */
    public static int getDayDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 4)) && (text.length() > (getSlashIndex(text) + 6))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 4, getSlashIndex(text) + 6));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date format as follows: dd/mm/yyyy hhmm.");
        }
    }

    public static int getMonthDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 7)) && (text.length() > (getSlashIndex(text) + 9))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 7, getSlashIndex(text) + 9));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date format as follows: dd/mm/yyyy hhmm.");
        }
    }

    public static int getYearDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 10)) && (text.length() > (getSlashIndex(text) + 14))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 10, getSlashIndex(text) + 14));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date format as follows: dd/mm/yyyy hhmm.");
        }
    }

    public static int getHourDateNumbers(String text) throws DukeException {
        assert text != null;
        if ((text.length() > (getSlashIndex(text) + 15)) && (text.length() > (getSlashIndex(text) + 17))) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 15, getSlashIndex(text) + 17));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date format as follows: dd/mm/yyyy hhmm.");
        }
    }


    public static int getMinuteDateNumbers(String text) throws DukeException {
        assert text != null;
        if (text.length() >= (getSlashIndex(text) + 17)) {
            return Integer.parseInt(text.substring(getSlashIndex(text) + 17));
        } else {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please enter a date format as follows: dd/mm/yyyy hhmm.");
        }
    }


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

    public static boolean isValidDateFormat(String text) throws DukeException {
        assert text != null;
        if (isValidDateNumbers(text)) {
            return true;
        } else {
            Ui.printIndent();
            throw new DukeException("Invalid Date Format!\n" +
                    "    There are at most 31 days, 12 months, 23 hours and 59 minutes! " +
                    "And remember that the year is 2019!\n" +
                    "    Please try again! Thank you! :)");
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

    public static boolean isValidTaskType(String taskType) {
        assert  taskType != null;
        if (taskType.equals("todo") || taskType.equals("event") || taskType.equals("deadline") ||
                taskType.equals("delete") || taskType.equals("done") || taskType.equals("find") ||
                taskType.equals("list") || taskType.equals("bye") ) {
            return true;
        } else {
            return false;
        }
    }

    public static String getDescriptionOfTask(String text) throws DukeException {
        assert text != null;
        int spaceIndex = text.indexOf(" ");
        if ((text.contains("deadline") && isValidDeadlineCommand(text))
                || text.contains("event") && isValidEventCommand(text)) {
            int slashIndex = text.indexOf("/");
            return text.substring(spaceIndex + 1, slashIndex - 1);
        } else if (text.contains("todo") || text.contains("done") ||
                text.contains("delete") || text.contains("find")) {
            return text.substring(spaceIndex + 1);
        } else {
            return text;
        }
    }

    public static boolean isValidToDoCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.length() > 5;
    }

    public static boolean isValidDeadlineCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.contains("/") && text.contains("by");
    }

    public static boolean isValidEventCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.contains("/") && text.contains("at");
    }

    public static boolean isValidDeleteCommand(String text) throws DukeException, NumberFormatException {
        assert text != null;
        if (text.length() < 7) {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please add an 'all' or a valid number after the delete word! :)");
        } else {
            char[] valueArray = text.substring(7).toCharArray();
            boolean isNumber = false;
            for (int i = 0; i < valueArray.length; i++) {
                isNumber = false;
                if (Character.isDigit(valueArray[i])) {
                    isNumber = true;
                }
            }
            if (isNumber && Integer.parseInt(text.substring(7)) > 0) {
                return text.contains(" ") && (text.contains("all") || Integer.parseInt(text.substring(7)) <= TaskList.listOfTasks.size());
            } else {
                Ui.printIndent();
                throw new DukeException("Wrong Format! Please add an 'all' or a valid number after the delete word! Thank You :)");
            }
        }
    }

    public static boolean isValidFindCommand(String text) {
        assert text != null;
        return text.contains(" ") && text.length() > 5;
    }

    public static boolean isValidDoneCommand(String text) throws DukeException {
        assert text != null;
        if (text.length() < 5) {
            Ui.printIndent();
            throw new DukeException("Wrong Format! Please add a valid number after the done word! :)");
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
                return text.contains(" ") && Integer.parseInt(text.substring(5)) <= TaskList.listOfTasks.size();
            } else {
                Ui.printIndent();
                throw new DukeException("Wrong Format! Please add a valid number after the done word! Thank You :)");
            }
        }
    }

    public static String findWord(String text) {
        assert text != null;
        int spaceIndex = text.indexOf(" ");
        return text.substring(spaceIndex + 1);
    }

    public static String findToDelete(String text) {
        assert text != null;
        int spaceIndex = text.indexOf(" ");
        return text.substring(spaceIndex + 1);
    }

    public static String findTaskNumber(String text) {
        assert text != null;
        int spaceIndex = text.indexOf(" ");
        return text.substring(spaceIndex + 1);
    }
}
