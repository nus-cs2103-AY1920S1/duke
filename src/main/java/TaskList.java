import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public void deleteTask(int number) {
        Task temp = tasks.get(number - 1);
        tasks.remove(number - 1);
    }
    public void addTask (String taskType, String taskDetails) throws DukeException {
        if (taskDetails.equals("")) {
            throw new DukeException("      ☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        }
        if (taskType.equals("todo")) {
            this.tasks.add(new Todo(taskDetails));
        } else if(taskType.equals("deadline") || taskType.equals("event")) {
            if(taskType.equals("deadline") || taskType.equals("event")) {
                //replace the first / so that the dates will not be split up
                taskDetails = taskDetails.replaceFirst("/", ":");  //need to assign this to taskDetails so it is re-recorded
                String[] tempStringArr = taskDetails.split(":");
                String description = ((String) Array.get(tempStringArr, 0)).trim();  //to remove ending whitespace
                String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                if (taskType.equals("deadline")) {
                    tasks.add(new Deadline(description, secondString));
                } else {
                    tasks.add(new Event(description, secondString));
                }
            }
        } else {//all other keywords not part of Duke's task handling schedule
            throw new DukeException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
