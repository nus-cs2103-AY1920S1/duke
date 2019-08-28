/**
 * This is a class which keeps track of the list of tasks.
 * @author Choong Yong Xin
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList;

    //Create new file to store task lists
    public TaskList() throws IOException {
        this.taskList = new ArrayList<Task>();;
        String currentDirectory = System.getProperty("user.dir");
        File parent = new File(currentDirectory + "/data/");
        File newFile = new File(parent, "/tasks.txt");
        if (parent.mkdirs()) {
            boolean isCreated = newFile.createNewFile();
        }
    }

    //Load tasklist with data from hard disk
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    void displayTaskList() {
        System.out.println("Here are the tasks in your list:");
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            System.out.println(numCommands + "." + i);
        }
    }

    void addToDo(Todo newTodo) {
        taskList.add(newTodo);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTodo);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void addDeadline(Deadline newDeadline) {
        taskList.add(newDeadline);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newDeadline);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void addEvent(Event newEvent) {
        taskList.add(newEvent);
        System.out.println("Got it. I've added this task: ");
        System.out.println(newEvent);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void deleteTask(int taskNumber) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(taskList.get(taskNumber - 1));
        taskList.remove(taskNumber - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    void findTasks(String taskName) {
        System.out.println("Here are the matching tasks in your list");
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            if (i.description.toLowerCase().contains(taskName.toLowerCase())) {
                System.out.println(numCommands + "." + i);
            }
        }
    }
}
