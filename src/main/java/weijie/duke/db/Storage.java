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

public class Storage {

    private File file;

    /**
     * <p>
     *      Creates a Storage class which reads and writes Tasks at the specified path location. If the file does not
     *      exist, a new file with the specified name will be created at the path.
     * </p>
     * @param path Path to file to read and write Tasks from
     * @throws DukeIoException If an IO exception occurs.
     */
    public Storage(String path) throws DukeIoException {
        this.file = new File(path);

        if (!file.exists()) {
            write(new ArrayList<>());
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
            throw new DukeIoException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DukeIoException("☹ OOPS!!! Unexpected IO error has occurred!");
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
            throw new DukeIoException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException e) {
            throw new DukeIoException("☹ OOPS!!! Unexpected IO error has occurred!");
        }
    }
}
