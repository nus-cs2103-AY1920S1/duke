import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class representing a list of <code>Notes</code>.
 */
public class NoteList implements Serializable {
    private static final long serialVersionUID = 142752L;

    private ArrayList<String> notes = new ArrayList<>();
    
    /**
     * Adds a note to the <code>NoteList</code>.
     * 
     * @param note The note to be added to this <code>NoteList</code>
     */
    public void add(String note) {
        notes.add(note);
    }

    /**
     * Deletes from the <code>NoteList</code> at the specified index.
     * 
     * @param index The index at which a note in the list will be deleted
     * @return The deleted note
     * @throws DukeException If the chosen index does not exist in the list
     */
    public String deleteAt(int index) throws DukeException {
        int realIndex = index - 1;

        checkIndexExists(realIndex);

        String deletedNote = notes.get(realIndex);
        notes.remove(realIndex);
        return deletedNote;
    }

    /**
     * Deletes all the elements in this list, leaving it empty.
     */
    public void deleteAllNotes() {
        notes = new ArrayList<String>();
    }

    /**
     * Returns whether the list is empty.
     * 
     * @return <code>true</code> if the list is empty and <code>false</code> otherwise
     */
    public boolean isEmpty() {
        return notes.isEmpty();
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return The number of elements in the list
     */
    public int size() {
        return notes.size();
    }

    /**
     * Returns the <code>String</code> representation of this <code>NoteList</code>.
     * 
     * @return The <code>String</code> representation of this <code>NoteList</code>, containing all tasks in order of
     *     addition marked by its position in the list.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iterator = 1;

        for (String n : notes) {
            //For each note n, print out one line of "X.[<Status>] Description"
            sb.append(String.format("%d.%s\n", iterator++, n.toString()));
        }

        return sb.toString();
    }

    /**
     * Checks if the specified index exists.
     * 
     * @param realIndex The index as a zero-indexed value
     */
    private void checkIndexExists(int realIndex) throws DukeException {
        if (realIndex < 0) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Sweat);
            throw new DukeException(DukeUi.ERROR_LIST_INDEX_SMALL);
        }

        if (realIndex >= notes.size()) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            throw new DukeException(String.format(DukeUi.ERROR_LIST_INDEX_BIG, realIndex + 1));
        }
    }    
}