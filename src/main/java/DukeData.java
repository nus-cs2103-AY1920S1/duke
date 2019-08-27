import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Class of saved tasks in hard disk
public class DukeData {

    private File f;
    private boolean isExists;

    // Format of file
    // T | 1 | read book
    // D | 0 | return book | June 6th
    // E | 0 | project meeting | Aug 6th 2-4pm
    // T | 1 | join sports club

    public DukeData() {
        f = new File("F:\\CS2103\\duke\\data\\duke.txt");
        isExists = f.exists();
    }

    // Convert contents of data to ArrayList
    public ArrayList<Task> toArrayList() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
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
                taskList.add(currTask);
            }
        } catch (FileNotFoundException e) {
            throw new DukeDataException();
        }
        return taskList;
    }

    // Save changes in task list to hard disk
    public void saveData(ArrayList<Task> taskList) throws IOException {
        // Note: Overwrites file if currently exists
        FileWriter fw = new FileWriter("F:\\CS2103\\duke\\data\\duke.txt");
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

    // Returns whether data in hard disk exists
    public boolean getIsExist() { return isExists; }
}


