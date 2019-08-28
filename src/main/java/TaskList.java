import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;

    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }

    public void addAndPrint(Task newTask) {
        list.add(newTask);
        System.out.println("Got it. I've added this task: \n  " +
                newTask + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public void addTodo(String activityName, boolean isDone){
        Task newTask = new Todo(activityName,false);
        addAndPrint(newTask);
    }

    public void addDeadline(String activityName, String deadline, boolean isDone) {
        Task newTask = new Deadline(activityName, false, deadline);
        addAndPrint(newTask);
    }

    public void addEvent(String activityName, String time, boolean isDone) {
        Task newTask = new Event(activityName,false, time);
        addAndPrint(newTask);
    }

    public void doneTask(int idx){
        list.get(idx - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done: \n  " +
                list.get(idx - 1));
    }

    public void deleteTask(int idx) {
        Task removed = list.remove(idx - 1);
        System.out.println("Noted. I've removed this task: \n  " +
                removed + "\nNow you have " + list.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }
}
