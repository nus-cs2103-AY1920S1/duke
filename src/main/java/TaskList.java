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

    protected String printAll() {
        StringBuilder sb = new StringBuilder();
        for(Tasks t: listOfTasks) {
            //System.out.println(t);
            sb.append(t);
        }
        return sb.toString();
    }

}