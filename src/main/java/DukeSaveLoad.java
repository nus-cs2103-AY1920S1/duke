import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * A class that handles saving and loading of Duke's data to and from the disk.
 */
public class DukeSaveLoad {
    private static final String TASKLIST_FILE_NAME = "TaskList.tl";
    private static final String NOTELIST_FILE_NAME = "NoteList.nl";
    
    private final File taskListSaveFile;
    private final File noteListSaveFile;

    /**
     * Creates a new <code>DukeSaveLoad</code> with a reference to a saveFile in the same directory.
     * 
     * @throws NullPointerException When the <code>pathname</code> is <code>null</code>
     * @throws IOException If an IOException occured
     */
    public DukeSaveLoad() throws NullPointerException, IOException {
        this.taskListSaveFile = new File(System.getProperty("user.dir") + "/" + TASKLIST_FILE_NAME);
        this.noteListSaveFile = new File(System.getProperty("user.dir") + "/" + NOTELIST_FILE_NAME);

        if (!taskListSaveFile.exists()) {
            taskListSaveFile.createNewFile();
        }

        if (!noteListSaveFile.exists()) {
            noteListSaveFile.createNewFile();
        }
    }

    /**
     * Attempts to serialize and save the <code>TaskList</code>.
     * 
     * @param tasks The <code>TaskList</code> we aim to serialize and save
     * @throws FileNotFoundException If saveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When an IOException occurs
     * @throws SecurityException If a security manager exists and its <code>checkWrite</code> method denies 
     *                           write access to the file
     */
    public void attemptSaveTaskList(TaskList tasks) throws FileNotFoundException, IOException, SecurityException {
        FileOutputStream fileOutputStream = new FileOutputStream(taskListSaveFile);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(tasks);

        objOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Attempts to generate a <code>TaskList</code> by reading saved data.
     * 
     * @return A <code>TaskList</code> generated from saved data, or an empty <code>TaskList</code> otherwise
     * @throws FileNotFoundException If taskListSaveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When I/O error occurs when reading Stream header
     * @throws ClassNotFoundException When <code>TaskList</code> class cannot be found
     */
    public TaskList attemptLoadTaskList() throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(taskListSaveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TaskList tasks = (TaskList) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return tasks;
        } catch (StreamCorruptedException e) {
            //Save file was corrupted
            TaskList tasks = new TaskList();
            tasks.add(new ToDoTask("Forgive Me >__<"));
            return tasks; //moe~<3
        } catch (EOFException e) {
            //Initialising save data
            return new TaskList();
        } catch (InvalidClassException e) {
            //Should only happen during debugging, due to changes in TaskList capability
            return new TaskList();
        }
    }

    /**
     * Attempts to serialize and save the <code>NoteList</code>.
     * 
     * @param notes The <code>NoteList</code> we aim to serialize and save
     * @throws FileNotFoundException If noteListSaveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When an IOException occurs
     * @throws SecurityException If a security manager exists and its <code>checkWrite</code> method denies 
     *                           write access to the file
     */
    public void attemptSaveNoteList(NoteList notes) throws FileNotFoundException, IOException, SecurityException {
        FileOutputStream fileOutputStream = new FileOutputStream(noteListSaveFile);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(notes);

        objOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Attempts to generate a <code>TaskList</code> by reading saved data.
     * 
     * @return A <code>TaskList</code> generated from saved data, or an empty <code>TaskList</code> otherwise
     * @throws FileNotFoundException If noteListSaveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When I/O error occurs when reading Stream header
     * @throws ClassNotFoundException When <code>NoteList</code> class cannot be found
     */
    public NoteList attemptLoadNoteList() throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(noteListSaveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            NoteList notes = (NoteList) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return notes;
        } catch (StreamCorruptedException e) {
            //Save file was corrupted
            NoteList notes = new NoteList();
            notes.add("Forgive Me >__<");
            return notes; //moe~<3
        } catch (EOFException e) {
            //Initialising save data
            return new NoteList();
        } catch (InvalidClassException e) {
            //Should only happen during debugging, due to changes in NoteList capability
            return new NoteList();
        }
    }
}
