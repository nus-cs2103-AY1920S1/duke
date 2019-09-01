package duke;

import duke.task.TaskList;

import java.io.*;

import java.nio.file.*;

/**
 * This class provides all file input/output functionality needed for persistent storage of Duke tasks.
 */
public class Storage {
    private Path path;

    /**
     * Constructs a Storage using the specified path.
     *
     * @param filepath  String containing the path of the file to read and write
     */
    public Storage(String filepath) {
        this.path = Paths.get(filepath).toAbsolutePath().normalize();
    }

    /**
     * Returns the TaskList loaded from disk. The file on disk must have been written by {@link #save(TaskList)}
     * using the current versions of the TaskList and Task classes. If the file does not exist, a new empty TaskList
     * is returned.
     *
     * @return                         the TaskList loaded from the file data
     * @throws IOException             if there is an I/O exception. This could mean that the file exists but cannot be
     *                                 accessed.
     * @throws ClassNotFoundException  if the data in the file does not match the current TaskList and Task class
     *                                 definitions. This could mean that the file does not contain valid Duke data or
     *                                 was saved by an earlier incompatible version of Duke.
     */
    public TaskList load() throws IOException, ClassNotFoundException {
        if(!Files.exists(path)) {
            return new TaskList();
        }
        FileInputStream fis = new FileInputStream(path.toString());
        ObjectInputStream ois = new ObjectInputStream(fis);
        TaskList taskList = (TaskList) ois.readObject();
        ois.close();
        return taskList;
    }

    /**
     * Saves the specified TaskList to disk. The TaskList is serialised using an {@link ObjectOutputStream}. Any
     * non-existent directories specified in the filepath are created. Overwrites the file at the path if it exists.
     *
     * @param taskList      the TaskList to save to disk. It must be {@link Serializable}.
     * @throws IOException  if there is an I/O exception. This could mean that the directories cannot be created or the
     *                      file cannot be accessed.
     */
    public void save(TaskList taskList) throws IOException {
        if(path.getParent() != null) Files.createDirectories(path.getParent());
        FileOutputStream fos = new FileOutputStream(path.toString());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(taskList);
        oos.close();
    }
}
