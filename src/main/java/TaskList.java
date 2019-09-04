import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> list; 

    public TaskList() {
    	this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
    	this.list = list;
    }

    public Task get(int index) {
    	return this.list.get(index);
    }

    public void delete(int index) {
    	this.list.remove(index);
    }

    public void done(int index) {
        this.list.get(index).markAsDone();
    }

    public void add(Task task) {
    	this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public void printList() {
    	System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
        }
    }
}
