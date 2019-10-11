import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> listOfTasks;
    //private StringBuilder sb;

    public TaskList() {
        this.listOfTasks = new ArrayList<Tasks>();
    }
    public TaskList(ArrayList<Tasks> list) {
        this.listOfTasks = list;
    }

    protected void addTask(Tasks t) {
        listOfTasks.add(t);
    }

    protected void deleteTask(int i) {
        listOfTasks.remove(i);
    }

    protected Tasks getTask(int i) {
        return listOfTasks.get(i);
    }

    protected ArrayList<Tasks> getTaskList() {
        return listOfTasks;
    }

    protected int size() {
        return listOfTasks.size();
    }

    /**
     * Appends all the tasks in the list into one string to be output as duke response.
     * @return a string of all the tasks in the proper format.
     */
    protected String printAll() {
        StringBuilder sb = new StringBuilder();
        for(Tasks t: listOfTasks) {
            sb.append(t);
        }
        return sb.toString();
    }

}