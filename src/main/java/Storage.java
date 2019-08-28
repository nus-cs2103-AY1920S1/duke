import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Object responsible for loading and saving tasks
 * from a text file saved in the hard disk.
 */
public class Storage {

    // Format of file
    // T | 1 | read book
    // D | 0 | return book | June 6th
    // E | 0 | project meeting | Aug 6th 2-4pm
    // T | 1 | join sports club

    private String fp; // Filepath
    private File f;
    private boolean isExists;
    public Storage (String filepath) {
        fp = filepath;
        f = new File(filepath);
        isExists = f.exists();
    }

    /**
     * Reads in data from text file, converts contents
     * to tasks.
     * If no such file, returns an empty AL
     * @return ArrayList of Task objects/List of tasks
     * @throws DukeException If text file is not found at given filepath
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskArr = new ArrayList<Task>();
        try {
            // Represents one line of file
            ArrayList<String> taskData;
            Task currTask = null;
            String taskType; // T (todo), D (deadline), E (event)
            boolean isTaskDone;

            Scanner input = new Scanner(f);
            while (input.hasNextLine()) {
                // Splits data from A | B | C to [A, B, C]
                taskData = new ArrayList<String>(
                        Arrays.asList(input.nextLine().split(" \\| "))
                );
                taskType = taskData.get(0);
                isTaskDone = taskData.get(1).equals("1") ? true : false;
                switch (taskType) {
                case "T":
                    currTask = new ToDo(taskData.get(2));
                    break;
                case "D":
                    currTask = new Deadline(taskData.get(2), taskData.get(3));
                    break;
                case "E":
                    currTask = new Event(taskData.get(2), taskData.get(3));
                    break;
                }
                if (isTaskDone) { currTask.markDone(); }
                taskArr.add(currTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeDataException();
        }
        return taskArr;
    }

    /**
     * Saves given updated list of tasks to text file in hard disk
     * @param taskList
     * @throws IOException
     */
    public void save(ArrayList<Task> taskList) throws IOException {
        // Note: Overwrites file if currently exists
        FileWriter fw = new FileWriter(fp);
        String separatorStr = " | ";
        for (Task currTask : taskList) {
            fw.write(
                    // Format: D | 0 | return book | June 6th
                    currTask.getTaskType() + separatorStr +
                            (currTask.isDone()? 1 : 0) + separatorStr +
                            currTask.getDescription() +
                            (currTask.getTaskType().equals("T")?
                                    "" : separatorStr + currTask.getSubDescription())
            );
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Getter method for class attribute
     * @return Whether file in given filepath exists
     */
    public boolean doesFileExist() { return isExists; }

}
