import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Deals with loading and saving tasks from and in the file
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

    // Converts contents of file to ArrayList
    // If no such file, return empty AL
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

    // Save updated AL to file in hard disk
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

    // Returns whether file in hard disk exists
    public boolean doesFileExist() { return isExists; }

}
