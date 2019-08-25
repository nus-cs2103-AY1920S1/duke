import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LocalStorage {
    private File tasksFile;

    public LocalStorage(String filePath) {
        this.tasksFile = new File(filePath);
    }

    public void writeToTasksFile(TaskList tasks) {
        List<Task> taskList = tasks.getList();
        try {
            FileWriter fw = new FileWriter(tasksFile);
            for (Task t : taskList) {
                String fileString = t.convertTaskToFileString() + "\n";
                fw.write(fileString);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println( e.getMessage() );
        }
    }

    public void readFromTasksFileToList (TaskList tasks) throws FileNotFoundException  {
        FileReaderHandler fileReaderHandler = new FileReaderHandler(tasks);
            Scanner sc = new Scanner(tasksFile);
            while (sc.hasNext()) {
                String fileLine = sc.nextLine();
                String[] parts = fileLine.split(" \\| ");

                String taskType = parts[0].toUpperCase();
                boolean isCompleted = parts[1].equals("T");
                String taskString = parts[2];

                fileReaderHandler.readLineFromFileToList(taskType, isCompleted, taskString);
            }

    }


}
