package duke.command;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * A generator which can generate Command objects based on the given user's input.
 */
public class CommandGenerator {

    /**
     * Returns an AddCommand which adds a To Do object to Duke's tasksList.
     *
     * @param input user's input which is used to generate a To Do object which is then given to the AddCommand.
     * @return an AddCommand which adds a To Do object to Duke's tasksList.
     * @throws DukeException if the user's input is invalid (e.g. does not follow the syntax required to create a
     *     To Do object).
     */
    public AddCommand getAddCommandForToDo(String input) throws DukeException {
        String topic = input.substring(4).trim();

        if (topic.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo toDo = new ToDo(topic);
        return new AddCommand(toDo);
    }

    /**
     * Returns an AddCommand which adds a Deadline object to Duke's tasksList.
     *
     * @param input user's input which is used to generate a Deadline object which is then given to the AddCommand.
     * @return an AddCommand which adds a Deadline object to Duke's tasksList.
     * @throws DukeException if the user's input is invalid (e.g. does not follow the syntax required to create a
     *     Deadline object).
     */
    public AddCommand getAddCommandForDeadline(String input) throws DukeException {
        String[] details = input.substring(8).trim().split("/by");

        boolean isInvalidInput = isInSufficientDetails(details);

        if (isInvalidInput) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        Deadline deadline = getDeadline(details);
        return new AddCommand(deadline);
    }

    /**
     * Returns an AddCommand which adds an Event object to Duke's tasksList.
     *
     * @param input user's input which is used to generate an Event object which is then given to the AddCommand.
     * @return an AddCommand which adds an Event object to Duke's tasksList.
     * @throws DukeException if the user's input is invalid (e.g. does not follow the syntax required to create an
     *     Event object).
     */
    public AddCommand getAddCommandForEvent(String input) throws DukeException {
        String[] details = input.substring(5).trim().split("/at");

        boolean isInvalidInput = isInSufficientDetails(details);

        if (isInvalidInput) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        Event event = getEvent(details);
        return new AddCommand(event);
    }

    /**
     * Returns a ListCommand which lists all the tasks in the tasks list.
     *
     * @return a ListCommand which lists all the tasks in the tasks list.
     */
    public ListCommand getListCommand() {
        return new ListCommand();
    }

    /**
     * Returns a FindCommand which finds a task based on keyword.
     *
     * @param input user's input from the UI which consists of the keyword used to find tasks that contains the keyword.
     * @return a FindCommand which finds a task based on keyword.
     */
    public FindCommand getFindCommand(String input) throws DukeException {
        String keyword = input.substring(4).trim();

        if (keyword.isEmpty()) {
            throw new DukeException("Keyword cannot be empty!");
        }

        return new FindCommand(keyword);
    }

    /**
     * Returns a DoneCommand which marks a task in the tasks list as done.
     *
     * @param input user's input from the UI which consists of the index used to find the task to be marked as done.
     * @return a DoneCommand which marks a task in the tasks list as done.
     * @throws DukeException if the index given by user is invalid(e.g. out of bound).
     */
    public DoneCommand getDoneCommand(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(4).trim());
            return new DoneCommand(index - 1);
        } catch (NumberFormatException e) {
            throw new DukeException("There can only be an integer after the word \"done\"!", e);
        }
    }

    /**
     * Returns a ConfirmEventDateCommand used to confirm the date of an event which previously has tentative dates.
     *
     * @param input user's input from the ui which contains the indices used to find the event and the date of the event
     *              to confirm.
     * @return a ConfirmEventDateCommand used to confirm the date of an event which previously has tentative dates.
     * @throws DukeException if the syntax or input given by the user is invalid.
     */
    public ConfirmEventDateCommand getConfirmEventDateCommand(String input) throws DukeException {
        Pattern pattern = Pattern.compile("\\d\\s+\\d");
        Matcher matcher = pattern.matcher(input);

        boolean isValidInput = matcher.find();
        if (!isValidInput) {
            throw new DukeException("Syntax of input is invalid");
        }

        try {
            String[] indicesAsString = matcher.group().split("\\s+");
            int taskIndex = Integer.parseInt(indicesAsString[0]) - 1;
            int dateIndex = Integer.parseInt(indicesAsString[1]) - 1;

            return new ConfirmEventDateCommand(taskIndex, dateIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Syntax of input for index is invalid");
        }
    }

    /**
     * Returns a DeleteCommand which deletes a task from the tasks list.
     *
     * @param input user's input from the UI which contains the index of the task to be deleted.
     * @return a DeleteCommand which deletes a task from Duke's tasks list.
     * @throws DukeException if the index given by user is invalid(e.g. out of bound).
     */
    public DeleteCommand getDeleteCommand(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.substring(6).trim());
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new DukeException("There can only be an integer after the word \"delete\"!");
        }
    }

    /**
     * Returns an ExitCommand which terminates the program.
     *
     * @return an ExitCommand which terminates the program.
     */
    public ExitCommand getExitCommand() {
        return new ExitCommand();
    }

    /**
     * Returns a Deadline object created based on the given details.
     *
     * @param details details of the Deadline task.
     * @return a Deadline object created based on the given details.
     * @throws DukeException if the details is invalid (e.g. the format of date and time does not follow the
     *     required format).
     */
    private Deadline getDeadline(String[] details) throws DukeException {
        try {
            String topic = details[0].stripTrailing();
            String deadlineUnformatted = details[1].stripLeading(); // Unformatted deadline from user's input
            String deadline = formatDateAndTime(deadlineUnformatted);

            return new Deadline(topic, deadline);
        } catch (ParseException e) {
            throw new DukeException("The format of date and time is wrong!", e);
        }
    }

    /**
     * Returns an Event object created based on the given details.
     *
     * @param details details of the Event task.
     * @return an Event object created based on the given details.
     * @throws DukeException if the details is invalid (e.g. the format of date and time does not follow the
     *     required format).
     */
    private Event getEvent(String[] details) throws DukeException {
        try {
            String topic = details[0].trim();
            String dateUnformatted = details[1].trim(); // Unformatted date from user's input

            boolean isDateTentative = dateUnformatted.contains(",");

            if (isDateTentative) {
                String[] dates = formatDateAndTimeArray(dateUnformatted);
                return new Event(topic, dates);
            } else {
                String date = formatDateAndTime(dateUnformatted);
                return new Event(topic, date);
            }
        } catch (ParseException e) {
            throw new DukeException("The format of date and time is wrong!", e);
        }
    }

    /**
     * Formats the date and time given by the user.
     *
     * @param dateTime date and time given by user in format: dd/MM/yyyy HHmm (24 Hours format).
     * @return formatted date and time in format: d Month yyyy, h:mm (12 Hours format).
     * @throws ParseException if the format of date and time given by user is incorrect.
     */
    private String formatDateAndTime(String dateTime) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        DateFormat outputFormatter = new SimpleDateFormat("d MMMM yyyy, h:mm a");
        Date date = inputFormatter.parse(dateTime);

        return outputFormatter.format(date);
    }

    /**
     * Format a series of date and time given by the user.
     *
     * @param dateTimes dates and times given by user in format: dd/MM/yyyy HHmm (24 Hours format).
     * @return An array of formatted dates and times in format: d Month yyyy, h:mm (12 Hours format).
     * @throws ParseException if the format of dates and times given by user is incorrect.
     */
    private String[] formatDateAndTimeArray(String dateTimes) throws ParseException {
        String[] dates = dateTimes.split(",\\s+");
        String[] datesFormatted = new String[dates.length];

        int size = dates.length;
        for (int i = 0; i < size; i++) {
            datesFormatted[i] = formatDateAndTime(dates[i]);
        }

        return datesFormatted;
    }

    /**
     * Returns true if the given details of a Deadline or Event task is not sufficient.
     *
     * @param details details of a Deadline or Event task.
     * @return true if the given details of a Deadline or Event task is not sufficient.
     */
    private boolean isInSufficientDetails(String[] details) {
        boolean isEmptyTopic = details.length == 0 || "".equals(details[0].trim());
        boolean isEmptyDate = details.length <= 1 || "".equals(details[1].trim());

        return isEmptyTopic || isEmptyDate;
    }
}
