package duke.task;

import duke.data.DateTime;

import java.text.ParseException;

public class Deadline extends Task {
    private String _date;
    private DateTime _dateTime;

    /**
     * Creates a Deadline object, which is also a Task.
     * @param desc a description of the Deadline Task.
     * @param date the date of the Deadline which is to be done by.
     */
    public Deadline(String desc, String date) {
        super(desc);
        try {
            this._dateTime = new DateTime(date);
            this._date = this._dateTime.getDateTimeString();
        } catch (ParseException e) {
            System.err.println("Cant parse Date: " + date);
        }
    }

    /**
     * Returns a string representation of a Deadline object.
     * @return String Returns a string representation of a Deadline object.
     */
    public String toString() {
        return String.format("[D][%s]%s(by: %s)",
                    this.getStatusIcon(), this._description, this._date);
    }

    /**
     * Returns a string representation of the Event object to be saved
     * into the hard disk file for the Duke program.
     * @return String Returns a the data representation of the Event Task.
     */
    public String toData() {
        return String.format("E | %s | %s | %s",
                this.getStatusIcon(), this._description, this._date);
    }

    public String getDeadline() {
        return this._date;
    }
}
