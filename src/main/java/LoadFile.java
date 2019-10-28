import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;

/**
 * Handles loading of file from specified directory.
 */

public class LoadFile {

    private String filePath;

    /**
     * Construct a object to specific the location for loading of file.
     * @param filePath Directory of the file
     */
    public LoadFile(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Loads the tasks.txt file via the file path.
     *
     * @return List of tasks.
     * @throws DukeException If file cannot be found.
     */
    public ArrayList<Task> loadTaskFromFile() throws DukeException {
        try {
            File savedFile = new File(this.getFilePath());
            Scanner sc = new Scanner(new FileReader(savedFile));

            ArrayList<Task> taskArr = changeTextToTask(new ArrayList<Task>(), sc);
            return taskArr;
        } catch (IOException err) {
            throw new DukeException("Unable to load file.");
        }
    }

    private ArrayList<Task> changeTextToTask(ArrayList<Task> taskArr, Scanner sc) throws IOException {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineArr = line.split(" \\| ");
            String typeOfTask = lineArr[0];
            String isDone = lineArr[1];
            String desc = lineArr[2];
            String date = "";
            if (typeOfTask.equals("E") || typeOfTask.equals("D")) {
                date = lineArr[3];
            }

            //create each task base on txt, and add into list
            Task newTask = new Task("");
            switch (typeOfTask) {
            case "T":
                newTask = new ToDo(desc);
                taskArr.add(newTask);
                break;
            case "D":
                newTask = new Deadline(desc, date);
                taskArr.add(newTask);
                break;
            case "E":
                newTask = new Event(desc, date);
                taskArr.add(newTask);
                break;
            default:
                break;
            }

            //check if Task is done
            if (isDone.equals("1")) {
                newTask.markAsDone();
            }
        }

        return taskArr;
    }
}
