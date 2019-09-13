package duke.note;

/**
 * Note Class for user to store text information.
 */
public class Note {
    private String desc;

    /**
     * Constructor for Note class.
     *
     * @param desc description of note
     */
    public Note(String desc) {
        this.desc = desc;
    }

    /**
     * Returns the description of a note.
     *
     * @return description of the note
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns the string representation of a note.
     *
     * @return String representation of the note
     */
    @Override
    public String toString() {
        return desc;
    }
}
