package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
    private String userDir;

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the default data directory.
     */
    public DukeData() {
        this.userDir = System.getProperty("user.dir");

        // create a new file and directory if it doesnt already exist
        File dir = new File(this.userDir + "/DukeData");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = "myDukeList" + ".txt";
        File tagFile = new File(dir, fileName);
        try {
            tagFile.createNewFile();
            this.saveFile = tagFile;
        } catch (IOException e) {
            System.err.println("Data file is null.");
        }
    }

    /**
     * Updates the data file in the hard disk with the corresponding
     * updated task list.
     * @param updatedTaskList the updated TaskList
     * @throws IOException if an I/O Exception occurs
     */
    public void update(TaskList updatedTaskList)
            throws IOException {
        // replace the file with the new updated data
        FileWriter updatedFW = new FileWriter(this.saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (Task task : updatedTaskList.getList()) {
            updatedBW.write(task.toData());
            updatedBW.newLine();
        }
        updatedBW.flush();
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
        while (oneTask != null) {
            String[] taskInfo = oneTask.split(" \\| ");
            String taskType = taskInfo[0];
            boolean isMarkedDone = taskInfo[1].equals("O");
            String description = taskInfo[2];
            Task newTask;
            switch (taskType) {
                case "T":
                    newTask = new ToDo(description);
                    if (isMarkedDone) {
                        newTask.markAsDone();
                    }
                    existingList.addTask(newTask);
                    break;
                case "D":
                    newTask = new Deadline(description, taskInfo[3]);
                    if (isMarkedDone) {
                        newTask.markAsDone();
                    }
                    existingList.addTask(newTask);
                    break;
                case "E":
                    newTask = new Event(description, taskInfo[3]);
                    if (isMarkedDone) {
                        newTask.markAsDone();
                    }
                    existingList.addTask(newTask);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + taskType);
            }
            oneTask = readFile.readLine();
        }
        readFile.close();

        return existingList;
    }
}
