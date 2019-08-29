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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {

    private File file;

    public Storage(String path) throws DukeIOException {
        this.file = new File(path);

        if (!file.exists()) {
            write(new ArrayList<>());
        }
    }

    public List<Task> read() throws DukeIOException {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            Task[] tasks =  (Task[]) objectInputStream.readObject();
            return new ArrayList<>(Arrays.asList(tasks));

        } catch (FileNotFoundException e) {
            throw new DukeIOException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DukeIOException("☹ OOPS!!! Unexpected IO error has occurred!");
        }
    }

    public void write(List<Task> tasks) throws DukeIOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(tasks.toArray(new Task[0]));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new DukeIOException("☹ OOPS!!! Invalid file file provided.");
        } catch (IOException e) {
            throw new DukeIOException("☹ OOPS!!! Unexpected IO error has occurred!");
        }
    }
}
