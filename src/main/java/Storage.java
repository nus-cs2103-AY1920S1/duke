import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static FileWriter fileWriter;
    private String fileName;

    /**
     * @param file name of txt file to store data whenever TaskList changes
     *
     */
    public Storage(String file){
        fileName = file;
    }

    /**
     * writes and saves all Task data into a txt file
     * @param taskManager a TaskList object
     *
     */
    public void saveTask(TaskList taskManager) throws IOException {
        String message = taskManager.getTasksData();
        fileWriter = new FileWriter(this.fileName);
        fileWriter.write(message);
        fileWriter.close();
    }

    /**
     * calls saveTask()
     * @param taskManager a TaskList object
     *
     */
    public void saveTaskUtil (TaskList taskManager){
        try {
            saveTask(taskManager);
        } catch (IOException e) {
            System.out.println(fileName + " not found.");
        }
    }

}
