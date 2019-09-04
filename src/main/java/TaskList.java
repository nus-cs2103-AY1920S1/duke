import java.util.ArrayList;

/** 
* Represents a list of tasks. Stores all tasks.
*/ 

public class TaskList {

    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void input(Task task) {
        this.list.add(task);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    public int count() {
        return this.list.size();
    }

    public Task fetch(int index) {
        return this.list.get(index);
    }

    /** 
    * find method to search for tasks with the specified keyword in them.
    * @returns ArrayList<Task> of relevant tasks.
    */ 

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> search = new ArrayList<>();
        for(Task current : this.list) {
            String toSearch = current.description;
            if(toSearch.toLowerCase().contains(keyword.toLowerCase())){
                search.add(current);
            }
        }
        return search;
    }
}