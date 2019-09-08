import java.util.ArrayList;
import java.io.Serializable;

public class NoteList implements Serializable {
    private static final long serialVersionUID = 142752L;

    private ArrayList<String> notes = new ArrayList<>();

    public void add(String note) {
        notes.add(note);
    }

    public String deleteAt(int index) throws DukeException {
        int realIndex = index - 1;

        checkIndexExists(realIndex);

        String deletedNote = notes.get(realIndex);
        notes.remove(realIndex);
        return deletedNote;
    }

    public void deleteAllNotes() {
        notes = new ArrayList<String>();
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    public int size() {
        return notes.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int iterator = 1;

        for (String n : notes) {
            //For each note n, print out one line of "X.[<Status>] Description"
            sb.append(String.format("%d.%s\n", iterator++, n.toString()));
        }

        return sb.toString();
    }

    private void checkIndexExists(int realIndex) throws DukeException {
        if(realIndex < 0) {
            throw new DukeException(DukeUi.ERROR_LIST_INDEX_SMALL);
        }

        if(realIndex >= notes.size()) {
            throw new DukeException(String.format(DukeUi.ERROR_LIST_INDEX_BIG, realIndex + 1));
        }
    }    
}