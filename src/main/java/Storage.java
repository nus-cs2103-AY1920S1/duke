import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath;

    public Storage() {}

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String line = sc.nextLine(); // eg. T,0,read book
            String[] taskDetails = line.split(",");
            String taskType = taskDetails[0];
            int booleanInt = Integer.parseInt(taskDetails[1]);
            switch (taskType) {
            case "T":
                Todo todo = new Todo(taskDetails[2], booleanInt == 0);
                list.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline(taskDetails[2], booleanInt == 0, taskDetails[3]);
                list.add(deadline);
                break;
            case "E":
                Event event = new Event(taskDetails[2], booleanInt == 0, taskDetails[3]);
                list.add(event);
                break;
            default:
                break;
            }
        }
        return list;
    }

//    public void addTaskToFile(Task task, String taskType) throws IOException {
//        FileWriter fw = new FileWriter(filePath, true);
//        String taskToAppend;
//        if (taskType.equals("todo")) {
//            taskToAppend = "T," + (task.isDone() ? 0 : 1) + "," + task.getDesc();
//        } else if (taskType.equals("deadline")) {
//            taskToAppend = "D," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + "," + ((Deadline)task).getDeadline();
//        } else {
//            // task is event type
//            taskToAppend = "E," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + "," + ((Event)task).getWhen();
//        }
//        fw.write(taskToAppend);
//        fw.close();
//    }

    public void overwriteTasks() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        TaskList taskList = new TaskList();
        ArrayList<Task> tasks = taskList.getTaskList();
        String fileContent = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
        for (Task task : tasks) {
            if (task instanceof Todo) {
                fileContent += "T," + (task.isDone() ? 0 : 1) + "," + task.getDesc();
            } else if (task instanceof Deadline) {
                fileContent += "D," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + ","
                        + ((Deadline)task).getDeadline().format(formatter);
            } else {
                fileContent += "E," + (task.isDone() ? 0 : 1) + "," + task.getDesc() + ","
                        + ((Event)task).getWhen().format(formatter);
            }
            fileContent += System.lineSeparator();
        }
        fw.write(fileContent);
        fw.close();
    }

}
