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

    String displayTaskList() {
        String output = "Here are the tasks in your list:\n";
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            output += (numCommands + "." + i + "\n");
        }
        return output;
    }

    void addToDo(Todo newTodo) {
        taskList.add(newTodo);
    }

    void addDeadline(Deadline newDeadline) {
        taskList.add(newDeadline);
    }

    void addEvent(Event newEvent) {
        taskList.add(newEvent);
    }

    void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    String findTasks(String taskName) {
        String output = "Here are the matching tasks in your list\n";
        int numCommands = 0;
        for (Task i : taskList) {
            numCommands += 1;
            if (i.description.toLowerCase().contains(taskName.toLowerCase())) {
                output += (numCommands + "." + i + "\n");
            }
        }
        return output;
    }
}
