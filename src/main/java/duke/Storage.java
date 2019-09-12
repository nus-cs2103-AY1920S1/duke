import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;

/**
 * Represents a store manager which helps to serialize and deserialize a file.
 * This class is used whenever we change an item in the TaskList, 
 * or when the program starts up initially.
 */
class Storage {
    private File tasks;
    private boolean firstTime;

    /**
     * Constructor for Storage. 
     * 
     * <p>In the DukeManager, the filepath is given "Tasks.sav"
     * 
     * @param filePath Indicates the path of the file to be stored at.
     */
    public Storage(String filePath) {
        this.tasks = new File(filePath);
    }

    /**
     * Serializes the Task List so it can be used again the file is opened.
     * 
     * @param taskList TaskList, an ArrayList which stores Tasks, is being serialized
     * @throws DukeException When there is an error serializing the file.
     */
    public void store(TaskList taskList) throws DukeException {
        assert this.tasks != null : "tasks file is invalid";
        try {
            FileOutputStream fileOut = new FileOutputStream(this.tasks.getPath());
            ObjectOutputStream out =  new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            throw new DukeException("Oof. Unable to serialize the list to Tasks.sav. " 
                    + "If there is already a Tasks.sav, please delete it.");
        }
    }

    /**
     * Returns a Tasklist that is deserialized from the file.
     * However, if the file is corrupted, it will throw a DukeException error.
     * 
     * @return a Tasklist that is deserialized from the  file.
     * @throws DukeException When the file is corrupted
     */
    public TaskList retrieve(DukeManager dukeManager) throws DukeException {
        assert this.tasks != null : "tasks file is invalid";
        if (!this.tasks.exists()) {
            dukeManager.setFirstTime(true);
            return createNewTaskList();
        } else {
            dukeManager.setFirstTime(false);
            return deserializeOldTaskList();
        }
    }

    /**
     * Returns an empty TaskList and creates a savefile for it.
     * 
     * @return an empty TaskList.
     * @throws DukeException When failing to create a save file.
     */
    private TaskList createNewTaskList() throws DukeException {
        try {
            this.tasks.createNewFile();
        } catch (Exception e) {
            throw new DukeException("Oof. Unable to create new sav file.");
        }
        TaskList taskList = new TaskList();
        store(taskList);
        return taskList;
    }

    /**
     * Returns a TaskList that was previously saved by deserializing the save file.
     * 
     * @return a previously saved TaskList.
     * @throws DukeException When the save file is corrupted
     */
    private TaskList deserializeOldTaskList() throws DukeException {
        try {
            // If the Tasks file exist
            FileInputStream fileIn = new FileInputStream(tasks.getPath());
            ObjectInputStream in = new ObjectInputStream(fileIn);     
            Object ob;  
            ob = in.readObject();
            fileIn.close();
            in.close();
            if (ob instanceof TaskList) {
                return (TaskList) ob;
            } else {
                // The Tasks file has wrong type when deserializing. Hence corrupted
                corruptedSaveFile();
                return null;
            }
        } catch (Exception error) {
            corruptedSaveFile();
            return null;
        }
    }

    /**
     * Creates a new save file, and throw an error to ask user to restart the game.
     * 
     * @throws DukeException When failing to create a save file.
     */
    private void corruptedSaveFile() throws DukeException {
        // If the Tasks file is corrupted
        try {
            tasks.createNewFile();
        } catch (IOException error) {
            throw new DukeException("Oof. Unable to create new sav file.");
        }
        TaskList taskList = new TaskList();
        store(taskList);
        throw new DukeException("Oof. Corrupted save file. "
                + "I have rewrote the old save file with a new one. "
                + "Please restart me again.");
    }

}