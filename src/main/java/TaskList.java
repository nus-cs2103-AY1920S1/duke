import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasksList;
    private File file;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
    }

//    public TaskList(File file) {
//        tasksList = new ArrayList<>();
//        this.file = file;
//        populateList();
//    }
//
//    public void populateList() {
//        try {
//            Scanner sc = new Scanner(file);
//            while(sc.hasNext()) {
//                String[] taskDetails = sc.nextLine().split("\\|");
//                Task task;
//                switch(taskDetails[0].trim()) {
//                case "T":
//                    task = new ToDo(taskDetails[2].trim());
//                    setDoneFlag(task, taskDetails[1].trim());
//                    break;
//                case "D":
//                    task = new Deadline(taskDetails[2].trim(), taskDetails[3].trim());
//                    setDoneFlag(task, taskDetails[1].trim());
//                    break;
//                case "E":
//                    task = new Event(taskDetails[2].trim(), taskDetails[3].trim());
//                    setDoneFlag(task, taskDetails[1].trim());
//                    break;
//                default:
//                    System.out.println("Error parsing the planner");
//                    return;
//                }
//                tasksList.add(task);
//            }
//        }
//        catch (FileNotFoundException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    public void setDoneFlag(Task task, String flag) {
//        if (flag.equals("+")) {
//            task.markAsDone();
//        }
//    }

    public void addItem(Task task) {
        tasksList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasksList.size());
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            Task task = tasksList.get(i);
            System.out.printf("%d. %s\n", (i + 1), task);
        }
    }

    public void markIndexedTaskAsDone(int index) {
        getTaskAtIndex(index - 1).markAsDone();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    public void deleteTask(int position) {
        Task task = tasksList.remove(position - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasksList.size());
    }

    public Task getTaskAtIndex(int position) {
        return tasksList.get(position);
    }

}
