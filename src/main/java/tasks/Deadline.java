package tasks;

import customexceptions.DukeException;
import tasks.Task;

public class Deadline extends Task {
    private String deadlineTime;
    private String dateTime;

    public Deadline(String name) {
        super(name);
    }

    public Deadline(String name, String dT) throws DukeException {
        super(name);
        this.deadlineTime = dT;
        this.dateTime = formatDateTime(dT.trim());
    }

    public String toString() {
        return "D|" + super.toString().trim() + "|" + dateTime.trim();
    }


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
