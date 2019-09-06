import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private String doneMessage = "Success! Your tasks have been saved to: "; // should be in Ui class?

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            // create buffered reader from file
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));

            // load tasks from file while file is not empty
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                tasks.add(loadTask(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private Task loadTask(String line) {
        // use Task class's constructor from String
        Task task = new Task(line);
        return task;
    }

    public void save(TaskList tasks) {
        // create json representation and save to given file
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(this.filePath)));

            // for each task in tasks, create its string representation for storage

            // write string to file

            // print done
            System.out.println(doneMessage + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
