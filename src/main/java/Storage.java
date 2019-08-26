import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //Returns an Arraylist with tasks saved in hard disk
    public ArrayList<Task> load() {
        try {
            File taskFile = new File(filePath);
            Scanner sc = new Scanner(taskFile);
            ArrayList<Task> taskList = new ArrayList<>();

            //Loads task from hard disk to ArrayList
            while (sc.hasNextLine()) {
                Task taskFromFile = convertToTask(sc.nextLine());
                taskList.add(taskFromFile);
            }

            return taskList;
        } catch (FileNotFoundException ex) {
            System.out.println(filePath + " cannot be found!");
            return new ArrayList<Task>();
        }
    }

    //Parses text file contents to a Task object
    private Task convertToTask(String task) {
        String[] taskInformation = task.split("\\|");
        String typeOfTask = taskInformation[0];
        String completionStatus = taskInformation[1];
        String taskDescription = taskInformation[2];
        Task convertedTask;
        if (typeOfTask.equals("[T]")) {
            convertedTask = new ToDo(taskDescription);
        } else if (typeOfTask.equals("[E]")) {
            convertedTask = new Event(taskDescription, taskInformation[3]);
        } else {
            convertedTask = new Deadline(taskDescription, taskInformation[3]);
        }

        if (completionStatus.equals("1")) {
            convertedTask.markAsDone();
        }

        return convertedTask;
    }

    //Writes a task to the file each time the task list changes
    public static void writeTaskToFile(String fileName) {
        try {
            FileWriter taskFile = new FileWriter(fileName);
            for (Task taskToWrite : taskList) {
                taskFile.write(taskToWrite.writeToFile() + "\n");
            }

            taskFile.close();
        } catch (IOException ex) {
            System.out.println(fileName + " cannot be found!");
        }
    }
}
