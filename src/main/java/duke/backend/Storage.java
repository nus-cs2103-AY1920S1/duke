package duke.backend;

import duke.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class Storage {

    private File file;
    private String filePath;

    /**
     * Constructor to initialise a storage with a filePath. Creates new file if doesn't exist.
     * @param filePath String form of path.
     * @throws IOException if File doesn't exist.
     */
    public Storage(String filePath) throws IOException {
        assert !filePath.equals("");
        this.filePath = filePath;
        this.file = new File(filePath);
        checkFile();
    }

    /**
     * Checks given filepath, if file exists, creates ArrayList of tasks from that file.
     * If it does not exist, an empty ArrayList is created.
     * @return new ArrayList of tasks.
     * @throws FileNotFoundException in the event that file does not exist in given directory.
     */
    public List<Task> load() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Task[] arrayOfTasks = (Task[]) ois.readObject();
            return new ArrayList<>(Arrays.asList(arrayOfTasks));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Error!");
        }
    }

    /**
     * method that saves current list in ListManager to a text file in given directory.
     * @throws IOException to handle if there is no text to be written in.
     */
    public void save(List<Task> listOfTasks) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listOfTasks.toArray(new Task[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * simple boolean flag to tell UI that the program is exiting.
     * @param num 0 for no exit, 1 for exit.
     * @return boolean on whether to quit the program.
     */
    private boolean isDone(int num) {
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void checkFile() throws IOException {
        if (!this.file.exists()) {
            File file = new File(this.filePath);
            save(new ArrayList<Task>());
        }
    }
}
