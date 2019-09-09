package duke.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import duke.task.Task;
import duke.task.TaskList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private String doneMessage = "Success! Your tasks have been saved to: "; // should be in Ui class?

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // create buffered reader from file
            BufferedReader br = new BufferedReader(new FileReader(this.filePath));

            // create Gson object
            Gson gson = new Gson();

            // create type token to deal with ArrayList
            Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();

            // import tasks from json
            tasks = gson.fromJson(br, taskListType);

        } catch (FileNotFoundException e) {
            System.out.println("Existing tasks file not found! Starting duke afresh...");
        } catch (Exception e) {
            // temporary haxx
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
            // todo: fix file not found (create file), maybe use FileOutputStream instead
            File file = new File(this.filePath);
            file.createNewFile(); // result (boolean) is not required
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            // create json representation of tasks
            Gson gson = new Gson();
            gson.toJson(tasks, pw);

            // print done
            System.out.println(doneMessage + filePath);
        } catch (IOException e) {
            // temporary haxx
            e.printStackTrace();
        }
    }
}
