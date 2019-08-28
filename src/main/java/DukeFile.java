import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFile {

    private String filePath = "";
    private File file;

    public DukeFile(String filePath) throws IOException {
        this.filePath = filePath;
        file = new File(filePath);
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Write to file specified in filepath
     * @param textToAdd
     * @throws IOException
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     *
     * @param taskListToAdd
     * @throws IOException
     */
    public void writeToFile(ArrayList<Task> taskListToAdd) throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < taskListToAdd.size(); i++) {
            sb.append(taskListToAdd.get(i).toStringFile() + "\n");
        }
        writeToFile(sb.toString());
    }

    /**
     * Get the file content
     * @return arrayList of tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> getFileContents() throws FileNotFoundException, DukeException {
//        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(file); // create a Scanner using the File as the source
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (s.hasNext()) {
            Task task = decodeTask(s.nextLine());
            taskList.add(task);
        }
        return taskList;
    }

    public Task decodeTask(String taskString) throws DukeException {
        String[] taskArr = taskString.split(" \\| ");
        Task task;
        switch (taskArr[0]) {
        case "T" :
            task = new Todo(taskArr[2]);
            break;
        case "D" :
            task = new Deadline(taskArr[2], taskArr[3]);
            break;
        case "E" :
            task = new Event(taskArr[2], DateTimeHandler.getDateTimeRange(taskArr[4]), taskArr[4]);
            break;
        default:
            task = new Task();
        }
        task.isDone = decodeIsDone(taskArr[1]);
        return task;
    }

    public boolean decodeIsDone(String s) {
        return s.equals("1");
    }
}
