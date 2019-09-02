import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The DukeData class manages the data of the user's task list by
 * updating the data file in the hard disk accordingly.
 */
public class DukeData {
    // every time Duke starts up, create and load a new or existing file.
    private File _saveFile;
    private FileWriter _fw;
    private BufferedWriter _bw;
    private TaskList _taskList;

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the default data directory.
     */
    public DukeData() {
        this._taskList = new TaskList();

        // create a new File in the data file
        File dir = new File("/Users/StudyBuddy/Desktop/CS2103/iP/duke/src/main/data");
        try {
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                String fileName = "myDukeList" + ".txt";
                File tagFile = new File(dir, fileName);
                try {
                    if (!tagFile.exists()) {
                        tagFile.createNewFile();
                    }
                    this._saveFile = tagFile;
                } catch (IOException e) {
                    System.err.println("Data file is null.");
                }
            }

            // attach the file to FileWriter and BufferedWriter
            this._fw = new FileWriter(this._saveFile);
            this._bw = new BufferedWriter(this._fw);
        } catch (IOException e) {
            System.err.println("Cant make Directory.");
        }
    }

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the given data directory, filePath.
     * @param filePath the file path where the user wants to save Duke's data to.
     */
    public DukeData(String filePath) {
        this._taskList = new TaskList();

        // create a new File in the data file
        File dir = new File(filePath);
        try {
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                String fileName = "myDukeList" + ".txt";
                File tagFile = new File(dir, fileName);
                try {
                    if (!tagFile.exists()) {
                        tagFile.createNewFile();
                    }
                    this._saveFile = tagFile;
                } catch (IOException e) {
                    System.err.println("Data file is null.");
                }
            }

            // attach the file to FileWriter and BufferedWriter
            this._fw = new FileWriter(this._saveFile);
            this._bw = new BufferedWriter(this._fw);
        } catch (IOException e) {
            System.err.println("Cant make Directory.");
        }
    }

    /**
     * Adds and updates the given task to the file saved in the hard disk.
     * @param index the index of the task to be added.
     * @param t Task object which can represent ToDo, Deadline, or Event.
     * @throws IOException
     */
    public void addTask(int index, Task t) throws IOException {
        this._taskList.addTask(t);

        this._bw.write(String.format(" %d. %s", index, t.toData()));
        this._bw.newLine();
        this._bw.flush();
    }

    /**
     * Updates the data file in the hard disk by removing the said task.
     * @param index the index of the task to be removed.
     * @throws IOException
     */
    public void removeTask(int index) throws IOException {
        this._taskList.removeTask(index);

        // now we replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this._saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : this._taskList.getList()) {
            updatedBW.write(String.format(" %d. %s", count, task.toData()));
            updatedBW.newLine();
            count++;
        }
        updatedBW.flush();
        this._fw = updatedFW;
        this._bw = updatedBW;
    }

    /**
     * Updates the data file in the hard disk by changing
     * the status of the given task.
     * @param index the index of the task from the task list
     * @throws IOException
     * @throws FileNotFoundException when the file does not exist.
     */
    public void taskDone(int index)
            throws IOException, FileNotFoundException {
        this._taskList.markTaskAsDone(index);

        // now we replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this._saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : this._taskList.getList()) {
            updatedBW.write(String.format(" %d. %s", count, task.toData()));
            updatedBW.newLine();
            count++;
        }
        updatedBW.flush();
        this._bw = updatedBW;
        this._fw = updatedFW;
    }

    /**
     * Closes the BufferedWriter file upon exit of the Duke program.
     * @throws IOException if an I/O Exception occurs.
     */
    public void exit() throws IOException {
        this._bw.close();
    }

    /**
     * Prints out everything in the data file saved in the hard disk.
     * @throws IOException if an I/O Exception occurs.
     */
    public void printDataFile() throws IOException {
        BufferedReader out = new BufferedReader(
                new FileReader(this._saveFile));

        String line = out.readLine();
        while (line != null)  {
            System.out.println(line);
            line = out.readLine();
        }
        out.close();
    }

    /**
     * Loads the data that DukeData manages in the form of a TaskList object.
     * @return TaskList
     */
    public TaskList load() {
        return this._taskList;
    }
}
