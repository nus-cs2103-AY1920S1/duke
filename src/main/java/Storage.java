import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.io.FileNotFoundException;

/**
 * Class responsible for loading and saving tasks
 * from a text file saved in the hard disk.
 */
public class Storage {

    // Format of file
    // T | 1 | read book
    // D | 0 | return book | June 6th
    // E | 0 | project meeting | Aug 6th 2-4pm
    // T | 1 | join sports club

    private String filePath;
    private File file;
    private boolean doesFileExist;
    public Storage (String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.doesFileExist = file.exists();
    }

    /**
     * Reads in data from given text file,
     * reads and converts to a list.
     * If no such file, returns an empty list.
     * @return List of tasks
     */
    public ArrayList<Task> load() throws DukeException {
        // If no such file, create one
        if (!doesFileExist) {
            return new ArrayList<Task>();
        }

        // If file exists, read in data
        Scanner input = new Scanner(filePath);

        ArrayList<Task> taskArr = new ArrayList<Task>();
        ArrayList<String> oneLineTextArr; // One line of file
        String taskType; // T (todo), D (deadline), E (event)
        boolean isTaskDone; // 0 (not done) , 1 (done)
        Task currTask;

        while (input.hasNextLine()) {
            // Splits data from A | B | C to [A, B, C]
            oneLineTextArr = new ArrayList<String>(
                    Arrays.asList(input.nextLine().split(" \\| "))
            );
            taskType = oneLineTextArr.get(0);
            isTaskDone = oneLineTextArr.get(1).equals("1");
            switch (taskType) {
            case "T":
                currTask = new ToDo(oneLineTextArr.get(2));
                break;
            case "D":
                currTask = new Deadline(oneLineTextArr.get(2), oneLineTextArr.get(3));
                break;
            case "E":
                currTask = new Event(oneLineTextArr.get(2), oneLineTextArr.get(3));
                break;
            default:
                // No such task type as entered in text file
                throw new DukeStorageException();
            }
            if (isTaskDone) {
                currTask.markDone();
            }
            taskArr.add(currTask);
        }
        return taskArr;
    }

    /**
     * Saves given updated list of tasks to text file in hard disk.
     * @param taskList
     * @throws IOException
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        // Note: Overwrites file if currently exists
        FileWriter fw = new FileWriter(filePath);
        String STR_SEPARATOR = " | ";
        for (Task currTask : taskList) {
            // If available, for tasks with subcommands
            String subDescription = currTask.getTaskType().equals("T") ?
                    "" : STR_SEPARATOR + currTask.getSubDescription();
            // Format: D | 0 | return book | June 6th
            fw.write(currTask.getTaskType() + STR_SEPARATOR +
                    (currTask.isDone()? 1 : 0) + STR_SEPARATOR +
                    currTask.getDescription() + STR_SEPARATOR +
                    subDescription
            );
            fw.write("\n");
        }
        fw.close();
    }
}
