import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {

    private static File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads a file to populate the list of tasks.
     * If the file does not exist, a new file will be created
     *
     * @return the list of tasks
     */
    public static ArrayList<Task> load() {

        ArrayList<Task> listOfTasks = new ArrayList<>();

        try{
            checkFileExist();
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] task = sc.nextLine().split("\\|");
                String taskType = task[0].trim();

                if (taskType.equals("D")) {
                    boolean isTaskDone = task[1].trim().equals("1") ? true : false;
                    String taskDescription = task[2].trim();
                    String taskBy = task[3].trim();
                    listOfTasks.add(new Deadline(taskDescription, isTaskDone, Parser.dateTimeConverter(taskBy)));
                } else if (taskType.equals("E")) {
                    boolean isTaskDone = task[1].trim().equals("1") ? true : false;
                    String taskDescription = task[2].trim();
                    String taskAt = task[3].trim();
                    listOfTasks.add(new Event(taskDescription, isTaskDone, Parser.dateTimeConverter(taskAt)));
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

    /**
     * Saves current list of tasks to hard drive
     *
     * @param listOfTasks List that will be saved to the file
     */
    public static void save(List<Task> listOfTasks) {
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
    /**
     * Checks if the file exists, if not create a new file
     *
     */
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