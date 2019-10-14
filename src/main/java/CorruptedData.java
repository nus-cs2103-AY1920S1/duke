package duke.exception;

/**
 * Creates a class to show that a data within the safe file is corrupted.
 */
public class CorruptedData extends DukeException {
    private String data;

    /**
     * Create a new CorruptedData object. It contains the string representation of
     * the data that is corrupted.
     * 
     * @param data a string representation of the data that is taken in by the
     *             loading of the db file
     */
    public CorruptedData(String data) {
        this.data = data;
    }

    /**
     * Returns the message that is printed out when the excpetion is met.
     * 
     * @return a string representation that the data is corrupted
     */
    public String toString() {
        return String.format("This data (%s) is corrupted. Skipping...", data);
    }
}