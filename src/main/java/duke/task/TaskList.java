package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public enum TaskType {
        DEADLINE,
        EVENT,
        TODO
    }

    // ideal implementation of addToList
    public void add(Task task) {
        tasks.add(task);

        System.out.println("Okay! I've added: " + task.getDescription()
            + ". Use list to see all your tasks!");
    }

    public Task newTask(TaskType taskType, String description, String deadline)
            throws IllegalArgumentException {
        Task newTask = null; // task to be added

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

        return newTask;
    }

    public void printList() {
        int i = 1;
        for (Task task : tasks) {
            String s = task.toString();
            System.out.println(i + ". " + s);
        }
    }

//    public String writeToFile() {
//        Task task;
//        for (Task task : tasks) {
//            String s = task.printToFile();
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
        return tasks.isEmpty();
    }

    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
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
