import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath = "";
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException ex) {

            }
        }
    }

    /**
     * Write to file specified in filepath
     *
     * @param textToAdd
     * @throws IOException
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * @param taskListToAdd
     * @throws IOException
     */
    public void writeToFile(ArrayList<Task> taskListToAdd) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskListToAdd.size(); i++) {
            sb.append(taskListToAdd.get(i).toStringFile() + "\n");
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException ex) {
            throw new DukeException("I could not save your task");
        }
    }

    /**
     * Get the file content
     * @return arrayList of tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                Task task = decodeTask(s.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException ex) {

        }
        return taskList;
    }

    /**
     * Read in the txt file data and create task objects accordingly
     * @param taskString
     * @return
     * @throws DukeException
     */
    public Task decodeTask(String taskString) throws DukeException {
        String[] taskArr = taskString.split(" \\| ");
        Task task;
        switch (taskArr[0]) {
        case "T":
            task = new Todo(taskArr[2]);
            break;
        case "D":
            task = new Deadline(taskArr[2], taskArr[3]);
            break;
        case "E":
            task = new Event(taskArr[2], DateTimeHandler.getDateTimeRange(taskArr[4]), taskArr[4]);
            break;
        default:
            task = new Task();
        }
        task.isDone = decodeIsDone(taskArr[1]);
        return task;
    }

    /**
     * Return true if 1 or else false
     * @param s
     * @return
     */
    public boolean decodeIsDone(String s) {
        return s.equals("1");
    }
}
