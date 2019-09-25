package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;

/**
 * The DukeData class manages the data of the user's task list by
 * updating the data file in the hard disk accordingly.
 */
public class DukeData {
    // every time Duke starts up, create and load a new or existing file.
    private File saveFile;
    private FileWriter fw;
    private BufferedWriter bw;
    private String userDir;

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the default data directory.
     */
    public DukeData() {
        this.userDir = System.getProperty("user.dir");

        // create a new file and directory if it doesnt already exist
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
     * @param filePath the file path where the user wants to save Duke's data
     */
    public DukeData(String filePath) {

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
    public void addTask(int index, Task task)
            throws IOException {
        this.bw.write(String.format(" %d. %s", index, task.toData()));
        this.bw.newLine();
        this.bw.flush();
    }

    /**
     * Updates the data file in the hard disk by removing the said task.
     * @param updatedTaskList the TaskList which has removed the unwanted Task
     * @throws IOException if an I/O Exception occurs
     */
    public void removeTask(TaskList updatedTaskList)
            throws IOException {
        // replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this.saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : updatedTaskList.getList()) {
            updatedBW.write(String.format(
                    " %d. %s", count, task.toData()));
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
     * @param updatedTaskList the TaskList which has removed the specified Task
     * @throws IOException if an I/O Exception occurs
     * @throws FileNotFoundException when the file does not exist
     */
    public void taskDone(TaskList updatedTaskList)
            throws IOException, FileNotFoundException {
        // now we replace the file with the new updated data
        int count = 1;
        FileWriter updatedFW = new FileWriter(this.saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : updatedTaskList.getList()) {
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
     * @return a String representation of the tasks present in the hard disk
     * @throws IOException if an I/O Exception occurs
     */
    public String showDataFile() throws IOException {
        BufferedReader out = new BufferedReader(new FileReader(this.saveFile));
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
    public TaskList load() throws IOException {
        TaskList existingList = new TaskList();

        BufferedReader readFile = new BufferedReader(
                new FileReader(this.saveFile));
        String oneTask = readFile.readLine();
        while (oneTask != null)  {
            String[] taskInfo = oneTask.split(" | ");
            String taskType = taskInfo[0];
            Task newTask;
            switch (taskType.substring(4)) {
            case "T":
                newTask = new ToDo(taskInfo[2]);
                existingList.addTask(newTask);
                break;
            case "D":
                newTask = new Deadline(taskInfo[2], taskInfo[3]);
                existingList.addTask(newTask);
                break;
            case "E":
                newTask = new Event(taskInfo[2], taskInfo[3]);
                existingList.addTask(newTask);
                break;
            default: // never happens
                newTask = null;
            }
            if (taskInfo[1].equals("O")) {
                assert newTask != null;
                newTask.markAsDone();
            }
            oneTask = readFile.readLine();
        }
        readFile.close();

        return existingList;
    }
}
