package duke.util;

import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.note.Note;

/**
 * Class for NoteList.
 */
public class NoteList {
    private ArrayList<Note> notes;
    private int count;

    /**
     * Constructor for empty notelist.
     */
    public NoteList() {
        this.notes = new ArrayList<>();
        count = 0;
    }

    /**
     * Constructor for notelist when creating it from history.
     * @param notes list of notes from history
     */
    public NoteList(ArrayList<Note> notes) {
        this.notes = notes;
        this.count = notes.size();
    }

    /**
     * Registers a new note in the notelist.
     * @param desc Description of new note
     * @return String representation of new note
     */
    public String registerNewNote(String desc) {
        Note n = addToList(desc);
        return echo(n);
    }

    /**
     * Deletes a note from the notelist
     * @param index index of note to be deleted
     * @return String representation of deleted note
     * @throws DukeException
     */
    public String deleteItem(int index) throws DukeException {
        if (index <= 0 || index > count) {
            throw new DukeException("Invalid note number!");
        }
        index--;
        Note n = notes.get(index);
        notes.remove(index);
        String result = "Noted. I've removed this note:\n";
        result += "\"" + n + "\"" + "\n";
        count -= 1;
        if (count == 1) {
            result += "Now you have 1 note in the list";
        } else {
            result += "Now you have " + count + " notes in the list";
        }
        return result;
    }

    /**
     * Returns an <code>ArrayList</code> of notes.
     * @return ArrayList of notes
     */
    public ArrayList<Note> getNotesAsArrayList() {
        return notes;
    }

    /**
     * Adds note to the notelist.
     * @param desc Description of note
     * @return Note object that is added to the notelist
     */
    public Note addToList(String desc) {
        Note n = new Note(desc);
        notes.add(n);
        count++;
        return n;
    }

    /**
     * Returns the string representation of the notelist.
     * @return String representation of notelist
     */
    public String getListAsString() {
        if (count == 0) {
            return "You have no notes in your list!";
        }
        String result = "Here are the notes in your list:\n";
        return IntStream.rangeClosed(0, count - 1)
                .mapToObj(x -> (x + 1) + ". " + notes.get(x).toString() + "\n")
                .reduce(result, (a, b) -> a + b);
    }

    /**
     * Echos the note in a fixed format.
     * @param n Note object
     */
    public String echo(Note n) {
        String result = "Got it. I've added this note:\n";
        result += "\"" + n + "\"" + "\n";
        if (count == 1) {
            result += "Now you have 1 note in the list.";
        } else {
            result += "Now you have " + count + " notes in the list.";
        }
        return result;
    }
}
