import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class that represents a date and time to be used by <code>Task</code> objects.
 */
public class DukeDateTime implements Serializable {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Creates a new <code>DukeDateTime</code> instance according to the input arguments.
     * 
     * @param date A <code>LocalDate</code> representing the date of the <code>DukeDateTime</code>
     * @param time A <code>LocalTime</code> representing the time of the <code>DukeDateTime</code>
     */
    public DukeDateTime(LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
    }

    /**
     * A check for whether an instance of a <code>DukeDateTime</code> actually encodes date-time data.
     * 
     * @return <code>true</code> is both date and time properties are null and <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return date == null && time == null;
    }

    /**
     * Generates a <code>String</code> representing this instance of <code>DukeDateTime</code>. 
     * If both date and time fields are <code>null</code>, will return "Unspecified Time".
     * If only one field is <code>null</code>, only the other field will be printed.
     * If both fields are non-<code>null</code>, will print the date followed by the time.
     * 
     * @return A <code>String</code> representing this <code>DukeDateTime</code>
     */
    @Override
    public String toString() {
        if(date == null && time == null) {
            return "Unspecified Time";
        } else {
            StringBuilder sb = new StringBuilder();
            boolean dateExists = false;

            if(date != null) {
                String dateString = date.toString();
                sb.append(dateString.substring(8, 10));
                sb.append("/");
                sb.append(dateString.substring(5, 7));
                sb.append("/");
                sb.append(dateString.substring(0, 4));

                dateExists = true;
            }

            if(time != null) {
                if(dateExists) {
                    sb.append(" ");
                }

                String timeString = time.toString();
                sb.append(timeString.substring(0, 2));
                sb.append(timeString.substring(3, 5));
            }

            return sb.toString();
        }
    }
}
