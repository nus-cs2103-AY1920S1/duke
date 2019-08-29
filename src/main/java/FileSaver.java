import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSaver {
    private static File file = new File("data/duke.txt");

    public static ArrayList<Task> loadTask() throws FileNotFoundException {

        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {

                String[] task = sc.nextLine().split("\\|");
                String taskType = task[0].trim();
                boolean isTaskDone = task[2].trim().equals("1") ? true : false;

                switch(taskType) {
                    case "T":
                        Todo newTask = new Todo(task[2].trim());
                        if (isTaskDone) newTask.markAsDone();
                        taskList.add(newTask);
                        break;
                    case "E":
                        String eventDescription = task[2].trim();
                        String at = task[3].trim();
                        Event newEvent = new Event(eventDescription, at);
                        if (isTaskDone) newEvent.markAsDone();
                        taskList.add(newEvent);
                        break;
                    case "D":
                        String deadlineDescription = task[2].trim();
                        String by = task[3].trim();
                        Deadline newDeadline = new Deadline(deadlineDescription, by);
                        if (isTaskDone) newDeadline.markAsDone();
                        taskList.add(newDeadline);
                        break;
                    default:
                }
            }
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        }
        return taskList;
    }

    public static void saveTaskList(List<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : taskList) {
                if (task instanceof Event) {
                    String str = String.format("E | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Event) task).getAt());
                    fw.write(str);
                } else if (task instanceof Deadline) {
                    String str = String.format("D | %s | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription(), ((Deadline) task).getBy());
                    fw.write(str);
                } else {
                    String str = String.format("T | %s | %s\n", task.isDone() ? "1" : "0",
                            task.getDescription());
                    fw.write(str);
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
