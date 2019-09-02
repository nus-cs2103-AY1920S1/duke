import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> listOfTasks = new ArrayList<>();

    protected void addTask(Tasks t) {
        listOfTasks.add(t);
    }

    protected void deleteTask(Tasks t) {
        listOfTasks.remove(t);
    }

    protected Tasks getTask(int i) {
        return listOfTasks.get(i);
    }

    protected ArrayList<Tasks> getTaskList() {
        return listOfTasks;
    }

}