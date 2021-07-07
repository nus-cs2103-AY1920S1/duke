package seedu.duke.model.dateandtime;

/**
 * Defines a DateAndTime object.
 * Consists of different combination of Date and Time objects.
 */
public class DateAndTime implements Comparable<DateAndTime> {

    private Date date;
    private Time timeStart;
    private Time timeEnd;

    /**
     * Creates an instance of DateAndTime in [Date] format.
     * Stores a Date object as it's attribute.
     * @param date Date object representing the date stored by an instance of this class.
     */
    public DateAndTime(Date date) {
        this.date = date;
    }

    /**
     * Creates an instance of DateAndTime in [Date] [Time] format.
     * Stores a Date and Time object (ending time) as it's attributes.
     * @param date Date object representing the date stored by an instance of this class.
     * @param timeEnd Time object representing the ending time stored by an instance of this class.
     */
    public DateAndTime(Date date, Time timeEnd) {
        this(date);
        this.timeEnd = timeEnd;
    }

    /**
     * Creates an instance of DateAndTime in [Date] [Time] [Time] format.
     * Stores a Date and two Time objects (both starting and ending time) as it's attributes.
     * @param date Date object representing the date stored by an instance of this class.
     * @param timeStart Time object representing the starting time stored by an instance of this class.
     * @param timeEnd Time object representing the ending time stored by an instance of this class.
     */
    public DateAndTime(Date date, Time timeStart, Time timeEnd) {
        this(date, timeEnd);
        this.timeStart = timeStart;
    }

    /**
     * Returns a Date object stored as an attribute of this DateAndTime instance.
     * @return Date object representing the date stored in this DateAndTime instance.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns a Time object (starting time) stored as an attribute of this DateAndTime instance.
     * @return Time object representing the starting Time stored in this DateAndTime instance.
     */
    public Time getTimeStart() {
        return timeStart;
    }

    /**
     * Returns a Time object (ending time) stored as an attribute of this DateAndTime instance.
     * @return Time object representing the ending Time stored in this DateAndTime instance.
     */
    public Time getTimeEnd() {
        return timeEnd;
    }

    @Override
    public int compareTo(DateAndTime other) {
        if (this.date.compareTo(other.date) < 0) {
            return -1;
        } else if (this.date.compareTo(other.date) > 0) {
            return 1;
        } else if (this.timeStart != null && other.timeStart != null) {
            return this.timeStart.compareTo(other.timeStart);
        } else if (this.timeStart == null && other.timeStart != null) {
            if (this.timeEnd != null) {
                return this.timeEnd.compareTo(other.timeStart);
            }
        } else if (this.timeStart != null && other.timeStart == null) {
            return -1;
        } else if (this.timeStart == null && this.timeEnd == null) {
            if (this.timeEnd != null && other.timeEnd != null) {
                if (this.timeEnd.compareTo(other.timeEnd) < 1) {
                    return -1;
                } else if (this.timeEnd.compareTo(other.timeEnd) > 1) {
                    return 1;
                }
            } else if (this.timeEnd == null && other.timeEnd != null) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Returns the String representation of a DateAndTime instance.
     * @return String representation of a DateAndTime instance, with Time followed by Date.
     */
    @Override
    public String toString() {
        assert date != null : "DateAndTime object is empty.";
        if (timeEnd == null) {
            return "" + date;
        } else if (this.timeStart == null) {
            return "" + date + " " + timeEnd;
        } else {
            return "" + date + " " + timeStart + "-" + timeEnd;
        }
    }
}
