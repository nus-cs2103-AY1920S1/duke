package tasks;

import customexceptions.DukeException;
import tasks.Task;

/**
 * Represent an event that was input by the user.
 */
public class Event extends Task {
    private String eventTime;
    private String dateTime;

    /**
     * Constructor for event object.
     *
     * @param name The event name.
     */
    public Event(String name) {
        super(name);
    }

    /**
     * Constructor for event object.
     *
     * @param name The event name.
     * @param eT   The details of the event.
     * @throws DukeException
     */
    public Event(String name, String eT) throws DukeException {
        super(name);
        this.eventTime = eT;
        this.dateTime = formatDateTime(eT.trim());
    }


    public String toString() {
        return "E|" + super.toString().trim() + "|" + dateTime.trim();

    }

    /**
     * Formats the string input by user to date time.
     *
     * @param details Event details.
     * @return the formatted date time.
     * @throws DukeException if the details are not in the correct format.
     */
    private String formatDateTime(String details) throws DukeException {
        StringBuilder formattedDateTime = new StringBuilder();
        try {
            String[] split = details.split(" ");
            String date = split[0];
            String time = split[1];
            String splitDate[] = date.split("/");
            formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[0])));
            formattedDateTime.append("/");
            formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[1])));
            formattedDateTime.append("/");
            formattedDateTime.append(splitDate[2]);
            formattedDateTime.append(" ");
            formattedDateTime.append(time);
            return formattedDateTime.toString();
        } catch (Exception ex) {
            throw new DukeException("the date time format entered is incorrect. " +
                    "Please enter again in the following format: dd/MM/yyyy HHmm");
        }
    }
}
