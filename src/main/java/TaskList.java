import java.util.ArrayList;
import java.util.Iterator;
//import com.google.gson.Gson;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    // ideal implementation of addToList
    public void add(Task task) {
        this.tasks.add(task);
    }

    // temporary until I figure out how to abstract out task creation from adding to list
    public void addToList(TaskType taskType, String description, String deadline)
            throws IllegalArgumentException {
        // debug
        System.out.println("at addToList method");
        
        Task newTask = null; // task to be added

        // based on task type, create corresponding subclass object
        // todo: how to call subclass constructor from parent class?
        // want to move this to the Task class
        switch (taskType) {
        case DEADLINE:
            newTask = new Deadline(description, deadline);
            break;
        case EVENT:
            newTask = new Event(description, deadline);
            break;
        case TODO:
            newTask = new Todo(description);
            break;
        default:
            // this should not happen; invalid task type should be caught in caller method already
            System.out.println("Something went wrong!");
            break;
        }

        // add newTask to taskList
        this.tasks.add(newTask);
        System.out.println("Okay! I've added: " + description
                + ". Use list to see all your tasks!");
    }

    public void printList() {
        int i = 1;
        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); i++) {
            String s = iterator.next().toString();
            System.out.println(i + ". " + s);
        }
    }

//    public String writeToFile() {
//        Task task;
//        for (Iterator<Task> iterator = this.tasks.iterator(); iterator.hasNext(); task = iterator.next()) {
//            String s = iterator.next().printToFile();
//            return s;
//        }
//    }

    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex - 1);
    }

    public void deleteTask(Task taskToDelete) {
        String taskDescription = taskToDelete.toString();
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task: " + taskDescription);
        System.out.println("Now you have " + tasks.size() + " items in this list.");
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
//    public void saveToDisk() {
////        // create gson to serialise taskList
////        Gson gson = new Gson();
////        String taskListGson = gson.toJson(this);
////
////        // write taskList to txt file
////    }
////
////    public void readFromFile() {
////        // read json from file
////        // TODO
////
////        // create type token to deal with Arraylist
////        // todo: why can't I use Type?????
////        Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
////        this.tasks = Gson.fromJson(json, taskListType);
////    }
}
