package duke;

import command.*;
import exception.DukeException;
import exception.EmptyDescriptionException;
import exception.WrongDateFormatException;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Processes user's input text and executes corresponding commands.
 */
public class Parser {
    public static final String LINE = "    ____________________________________________________________\n";
    private Ui ui;

    /**
     * Returns a parser that parses user's input
     *
     * @param ui duke.Ui object that Duke has initialised
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses user's input and executes the corresponding commands.
     *
     * @param userInput text that user sends to Duke
     */
    public Command parse(String userInput) {
        // default command to be returned
        Command command = new EmptyCommand();

        String firstWord = userInput.split("\\s")[0];

        switch (firstWord) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "done":
            try {
                int index = Integer.parseInt(userInput.split("\\s")[1]);
                command = new DoneCommand(index);
            } catch (IndexOutOfBoundsException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The task you want to mark as done doesn't exist.");
                ui.showLine();
                //ui.getUserInput();
            }
            break;
        case "todo":
            try {
                String messageTodo = userInput.split("todo ")[1];
                if (messageTodo.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                }
                command = new AddCommand(TaskType.TODO, messageTodo);

            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                ui.showLine();
                //ui.getUserInput();
            }
            break;
        case "deadline":
            try {
                String[] deadlineMessageAndTime = userInput.split("deadline ")[1].split(" /by ");
                String messageDeadline = deadlineMessageAndTime[0];
                if (messageDeadline.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                }
                if (!userInput.split("deadline ")[1].contains(" /by ")) {
                    throw new DukeException("A deadline requires the '/by' keyword.");
                }
                Calendar deadlineCalendar = convertStringToCalendar(deadlineMessageAndTime[1]);
                command = new AddCommand(TaskType.DEADLINE, messageDeadline, deadlineCalendar);

            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                ui.showLine();
                //ui.getUserInput();
            } catch (WrongDateFormatException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The date format must be dd/mm/yyyy hhmm.");
                ui.showLine();
                //ui.getUserInput();
            } catch (DukeException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! "  + e);
                ui.showLine();
                //ui.getUserInput();
            }
            break;
        case "event":
            try {
                String[] eventMessageAndTime = userInput.split("event ")[1].split(" /at ");
                String messageEvent = eventMessageAndTime[0];
                if (messageEvent.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description of an event cannot be empty.");
                }
                if (!userInput.split("event ")[1].contains(" /at ")) {
                    throw new DukeException("An event requires the '/at' keyword.");
                }
                Calendar eventCalendar = convertStringToCalendar(eventMessageAndTime[1]);
                command = new AddCommand(TaskType.EVENT, messageEvent, eventCalendar);


            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The description of an event cannot be empty.");
                ui.showLine();
                //ui.getUserInput();
            } catch (WrongDateFormatException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The date format must be dd/mm/yyyy hhmm.");
                ui.showLine();
                //ui.getUserInput();
            } catch (DukeException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! " + e);
                ui.showLine();
                //ui.getUserInput();
            }
            break;
        case "delete":
            try {
                int index = Integer.parseInt(userInput.split("\\s")[1]);
                command = new DeleteCommand(index);

            } catch (IndexOutOfBoundsException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The task you want to delete doesn't exist.");
                ui.showLine();
                //ui.getUserInput();
            }
            break;
        case "find":
            try {
                String textToFind = userInput.split("find ")[1].trim();
                if (textToFind.trim().length() == 0) {
                    throw new EmptyDescriptionException("The description cannot be empty.");
                }

                command = new FindCommand(textToFind);

            } catch (ArrayIndexOutOfBoundsException | EmptyDescriptionException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! The description cannot be empty.");
                ui.showLine();
                //ui.getUserInput();
            }
        break;
        default:
            try {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                ui.showLine();
                ui.println("     ☹ OOPS!!! " + e);
                ui.showLine();
                //ui.getUserInput();
            }
        }

        return command;
    }

    /**
     * Converts a date string in the format of dd/mm/yyyy hhmm into a <code>Calendar</code> object.
     *
     * @param dateString String in the format of dd/mm/yyyy hhmm
     * @return Calendar that represents the given date
     */
    private static Calendar convertStringToCalendar (String dateString) throws WrongDateFormatException {
        try {
            String[] dateAndTime = dateString.split("\\s");
            String date = dateAndTime[0];
            String time = dateAndTime[1];
            int hour = Integer.parseInt(time) / 100;
            int minute = Integer.parseInt(time) % 100;

            String[] dateComponents = date.split("/");
            int day = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int year = Integer.parseInt(dateComponents[2]);

            Calendar calendar = new Calendar.Builder().setDate(year, month - 1, day)
                    .setTimeOfDay(hour, minute, 0).build();

            return calendar;
        } catch (Exception e) {
            throw new WrongDateFormatException("Wrong date format!");
        }
    }
}