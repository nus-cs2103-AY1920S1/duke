import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.EOFException;
import java.io.InvalidClassException;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

/**
 * A class that handles saving and loading of Duke's data to and from the disk.
 */
public class DukeSaveLoad {
    private final String SAVE_FILE_NAME = "DukeSaveFile.dsf";
    private final File saveFile;

    /**
     * Creates a new <code>DukeSaveLoad</code> with a reference to a saveFile in the same directory.
     * 
     * @throws NullPointerException When the <code>pathname</code> is <code>null</code>
     * @throws IOException If an IOException occured
     */
    public DukeSaveLoad () throws NullPointerException, IOException {
        this.saveFile = new File(System.getProperty("user.dir") + "/" + SAVE_FILE_NAME);

        if(!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    /**
     * Attempts to serialize and save the <code>TaskList</code>.
     * 
     * @param tasks The <code>TaskList</code> we aim to serialize and save
     * @throws FileNotFoundException If saveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When an IOException occurs
     * @throws SecurityException If a security manager exists and its <code>checkWrite</code> method denies 
     *                           write access to the file.
     */
    public void attemptSave(TaskList tasks) throws FileNotFoundException, IOException, SecurityException {
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);

        objOutputStream.writeObject(tasks);

        objOutputStream.close();
        fileOutputStream.close();
    }

    /**
     * Attempts to generate a <code>TaskList</code> by reading saved data.
     * 
     * @return A <code>TaskList</code> generated from saved data, or an empty <code>TaskList</code> otherwise
     * @throws FileNotFoundException If saveFile does not exist and cannot be created, or cannot be opened
     * @throws IOException When I/O error occurs when reading Stream header
     * @throws ClassNotFoundException When <code>TaskList</code> class cannot be found
     */
    public TaskList attemptLoad() throws FileNotFoundException, IOException, ClassNotFoundException {
        try{
            FileInputStream fileInputStream = new FileInputStream(saveFile);
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
}
