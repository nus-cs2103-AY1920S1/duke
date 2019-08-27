import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DukeFileManager {

    private static File file = new File("data/duke.txt");

    public static ArrayList<Task> loadListOfTasks() {

        ArrayList<Task> listOfTasks = new ArrayList<>();

        try{
            DukeFileManager.checkFileExist();
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                String taskType = task[0].trim();

                if (taskType.equals("D")) {
                    boolean isTaskDone = task[1].trim().equals("1") ? true : false;
                    String taskDescription = task[2].trim();
                    String taskBy = task[3].trim();
                    listOfTasks.add(new Deadline(taskDescription, isTaskDone, taskBy));
                } else if (taskType.equals("E")) {
                    boolean isTaskDone = task[1].trim().equals("1") ? true : false;
                    String taskDescription = task[2].trim();
                    String taskAt = task[3].trim();
                    listOfTasks.add(new Event(taskDescription, isTaskDone, taskAt));
                } else {
                    boolean isTaskDone = task[1].trim().equals("1") ? true : false;
                    String taskDescription = task[2].trim();
                    listOfTasks.add(new Todo(taskDescription, isTaskDone));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listOfTasks;
    }

    public static void saveListOfTasks(List<Task> listOfTasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : listOfTasks) {
                if (task instanceof Deadline) {
                    String taskToAdd = String.format("D | %s | %s | %s\n", task.isTaskDone() ? "1" : "0",
                            task.getTaskDescription(), ((Deadline) task).getBy());
                    fw.write(taskToAdd);
                } else if (task instanceof Event) {
                    String taskToAdd = String.format("E | %s | %s | %s\n", task.isTaskDone() ? "1" : "0",
                        task.getTaskDescription(), ((Event) task).getAt());
                    fw.write(taskToAdd);
                } else {
                    String taskToAdd = String.format("T | %s | %s\n", task.isTaskDone() ? "1" : "0",
                        task.getTaskDescription());
                    fw.write(taskToAdd);
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkFileExist() {
        try {
            String folder = "data";
            File directory = new File(folder);
            if (!directory.exists()) {
                directory.mkdir();
            } else {
                if (!file.exists()) {
                    file.createNewFile();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}