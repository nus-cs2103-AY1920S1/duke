package tasks;

import customexceptions.DukeException;
import tasks.Task;

/**
 * Represents a deadline that is input by the user
 */
public class Deadline extends Task {
    private String deadlineTime;
    private String dateTime;

    /**
     * Constructor for deadline object.
     *
     * @param name Name of the task.
     */
    public Deadline(String name) {
        super(name);
    }

    /**
     * Constructor for deadline object.
     *
     * @param name Name of the task.
     * @param dT   Details of the deadline.
     * @throws DukeException
     */
    public Deadline(String name, String dT) throws DukeException {
        super(name);
        assert (!(dT.equals(""))) : "date time empty";
        this.deadlineTime = dT;
        this.dateTime = formatDateTime(dT.trim());
    }

    public String toString() {
        return "D|" + super.toString().trim() + "|" + dateTime.trim();
    }


    /**
     * Formats the string given by user to date time.
     *
     * @param details details of the task.
     * @return the formatted date time.
     * @throws DukeException
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
