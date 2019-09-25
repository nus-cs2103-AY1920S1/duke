package weijie.duke.db;

import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSystemStorage implements ITaskStorage {

    private File file;

    /**
     * <p>
     *      Creates a FileSystemStorage class which reads and writes Tasks at the specified path location.
     *      If the file does not exist, a new file with the specified name will be created at the path.
     * </p>
     * @param path Path to file to read and write Tasks from
     * @throws DukeIoException If an IO exception occurs.
     */
    public FileSystemStorage(String path) throws DukeIoException {
        file = new File(path);
        if (!file.exists()) {
            createDirWithFile(path);
        }
    }

    /**
     * <p>
     *     Reads a list of Tasks from the file path specified in the class's constructor.
     * </p>
     * @return The list of Tasks stored in the file path.
     * @throws DukeIoException If there is no file located at the file path.
     */
    public List<Task> read() throws DukeIoException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Task[] tasks =  (Task[]) objectInputStream.readObject();
            return new ArrayList<>(Arrays.asList(tasks));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeIoException("Invalid file provided.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DukeIoException("Unexpected IO error has occurred.");
        }
    }

    /**
     * <p>
     *     Writes a list of Tasks at the file path specified in the class's constructor.
     * </p>
     * @param tasks The list of Tasks to write.
     * @throws DukeIoException If an IOException occurs.
     */
    public void write(List<Task> tasks) throws DukeIoException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(tasks.toArray(new Task[0]));

        } catch (FileNotFoundException e) {
            throw new DukeIoException("Invalid file provided.");
        } catch (IOException e) {
            throw new DukeIoException("Unexpected IO error has occurred.");
        }
    }

    private void createDirWithFile(String path) throws DukeIoException {
        int lastSlashPos = path.lastIndexOf('/');
        boolean createdDirs = false;

        if (lastSlashPos >= 0) {
            File directory = new File(path.substring(0, lastSlashPos));
            if (!directory.exists()) {
                createdDirs = directory.mkdirs();
            } else {
                write(new ArrayList<>());
            }
        }

        if (createdDirs) {
            write(new ArrayList<>());
        }
    }
}
