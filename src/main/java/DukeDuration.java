import java.io.Serializable;

/**
 * A class that represents a duration in time to be used by <code>Task</code> classes.
 */
public class DukeDuration implements Serializable {
    private final DukeDateTime startDateTime;
    private final DukeDateTime endDateTime;

    /**
     * Creates a new <code>DukeDuration</code> according to the input arguments.
     * 
     * @param startDateTime <code>DukeDateTime</code> containing the date and time at which the duration begins
     * @param endDateTime <code>DukeDateTime</code> containing the date and time at which the duration ends
     */
    public DukeDuration(DukeDateTime startDateTime, DukeDateTime endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Generates a <code>String</code> representing this instance of <code>DukeDuration</code>. 
     * If both <code>startDateTime</code> and <code>endDateTime</code> are <code>null</code>, will return "Unspecified Time".
     * If only the first field is non-<code>null</code>, will print the starting date-time of the duration.
     * If both fields are non-<code>null</code>, will print both fields delimited by a " to ".
     * 
     * @return A <code>String</code> representing this <code>DukeDateTime</code>
     */
    @Override
    public String toString() {
        if(startDateTime.isEmpty() && endDateTime.isEmpty()) {
            return "Unspecified Time";
        } else {
            StringBuilder sb = new StringBuilder();

            if(!startDateTime.isEmpty()) {
                sb.append(startDateTime.toString());
            }

            if(!endDateTime.isEmpty()) {
                sb.append(" to ");
                sb.append(endDateTime.toString());
            }
            
            return sb.toString();
        }
    }
}
