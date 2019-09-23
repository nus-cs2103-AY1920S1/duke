package duke.data;

import duke.task.Task;

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
    private File saveFile;
    private FileWriter fw;
    private BufferedWriter bw;
    private TaskList taskList;
    private String userDir;

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the default data directory.
     */
    public DukeData() {
        this.taskList = new TaskList();
        this.userDir = System.getProperty("user.dir");

        // create a new File in the data file
        File dir = new File(this.userDir + "/DukeData");
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            String fileName = "myDukeList" + ".txt";
            File tagFile = new File(dir, fileName);
            try {
                if (!tagFile.exists()) {
                    tagFile.createNewFile();
                }
                this.saveFile = tagFile;
            } catch (IOException e) {
                System.err.println("Data file is null.");
            }

            // attach the file to FileWriter and BufferedWriter
            assert this.saveFile != null;
            this.fw = new FileWriter(this.saveFile);
            this.bw = new BufferedWriter(this.fw);
        } catch (IOException e) {
            System.err.println("Cant make Directory.");
        }
    }

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the given data directory, filePath.
     * @param filePath the file path where the user wants to save Duke's data to
     */
    public DukeData(String filePath) {
        this.taskList = new TaskList();

        // create a new File in the data file
        File dir = new File(filePath + "/data");
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
                    this.saveFile = tagFile;
                } catch (IOException e) {
                    System.err.println("Data file is null.");
                }
            }

            // attach the file to FileWriter and BufferedWriter
            this.fw = new FileWriter(this.saveFile);
            this.bw = new BufferedWriter(this.fw);
        } catch (IOException e) {
            System.err.println("Cant make Directory.");
        }
    }

    /**
     * Adds and updates the given task to the file saved in the hard disk.
     * @param index the index of the task to be added
     * @param task Task object which can represent ToDo, Deadline, or Event
     */
    public void addTask(int index, Task task) throws IOException {
        this.taskList.addTask(task);

        this.bw.write(String.format(" %d. %s", index, task.toData()));
        this.bw.newLine();
        this.bw.flush();
    }

    /**
     * Updates the data file in the hard disk by removing the said task.
     * @param index the index of the task to be removed
     * @throws IOException if an I/O Exception occurs
     */
    public void removeTask(int index) throws IOException {
        this.taskList.removeTask(index);

        // now we replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this.saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : this.taskList.getList()) {
            updatedBW.write(String.format(" %d. %s", count, task.toData()));
            updatedBW.newLine();
            count++;
        }
        updatedBW.flush();
        this.fw = updatedFW;
        this.bw = updatedBW;
    }

    /**
     * Updates the data file in the hard disk by changing
     * the status of the given task.
     * @param index the index of the task from the task list
     * @throws IOException if an I/O Exception occurs
     * @throws FileNotFoundException when the file does not exist
     */
    public void taskDone(int index)
            throws IOException, FileNotFoundException {
        this.taskList.markTaskAsDone(index);

        // now we replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this.saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : this.taskList.getList()) {
            updatedBW.write(String.format(" %d. %s", count, task.toData()));
            updatedBW.newLine();
            count++;
        }
        updatedBW.flush();
        this.bw = updatedBW;
        this.fw = updatedFW;
    }

    /**
     * Closes the BufferedWriter file upon exit of the Duke program.
     * @throws IOException if an I/O Exception occurs
     */
    public void exit() throws IOException {
        this.bw.close();
    }

    /**
     * Returns everything in the data file saved in the hard disk.
     * @reutrn a String representation of the tasks present in the hard disk
     * @throws IOException if an I/O Exception occurs
     */
    public String showDataFile() throws IOException {
        BufferedReader out = new BufferedReader(
                new FileReader(this.saveFile));
        String line = out.readLine();
        StringBuilder dataOut = new StringBuilder();

        while (line != null)  {
            dataOut.append(System.getProperty("line.separator"));
            dataOut.append(line);
            line = out.readLine();
        }
        out.close();

        return dataOut.toString();
    }

    /**
     * Loads the data that DukeData manages in the form of a TaskList object.
     * @return the TaskList object of the list that DukeData manages
     */
    public TaskList load() {
        return this.taskList;
    }
}
