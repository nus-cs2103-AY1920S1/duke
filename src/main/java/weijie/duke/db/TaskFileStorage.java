package weijie.duke.db;

import weijie.duke.exceptions.DukeIOException;
import weijie.duke.models.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TaskFileStorage {

    private File file;

    public TaskFileStorage(String path) throws DukeIOException {
        this.file = new File(path);

        if (!file.exists()) {
            write(new Task[0]);
        }
    }

    public Task[] read() throws DukeIOException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            return (Task[]) objectInputStream.readObject();

        } catch (FileNotFoundException e) {
            throw new DukeIOException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DukeIOException("☹ OOPS!!! Unexpected IO error has occurred!");
        }
    }

    public void write(Task[] tasks) throws DukeIOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(tasks);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeIOException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException e) {
            throw new DukeIOException("☹ OOPS!!! Unexpected IO error has occurred!");
        }
    }
}
