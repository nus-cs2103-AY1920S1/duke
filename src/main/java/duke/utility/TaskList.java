package duke.utility;

import duke.errands.Task;
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
        this.list.remove(index - 1);
    }

    public int count() {
        return this.list.size();
    }

    public Task fetch(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Method to find all tasks that match a specified keyword from the user.
     *
     * @param keyword The keyword to search for in Duke's tasks based on their description.
     * @return ArrayList of tasks will all matching tasks.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> search = new ArrayList<>();
        for (Task current : this.list) {
            String toSearch = current.description;
            if (toSearch.toLowerCase().contains(keyword.toLowerCase())) {
                search.add(current);
            }
        }
        return search;
    }
}