import java.util.ArrayList;

public class TaskList {
    
    private ArrayList<Task> list; 

    public TaskList() {
    	this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
    	this.list = list;
    }

    /**
     * Returns the task stored in the list
     *
     * @param index Index of the task in the list
     */
    public Task get(int index) {
    	return this.list.get(index);
    }

    /**
     * Deletes the task stored in the list
     *
     * @param index Index of the task in the list
     */
    public void delete(int index) {
    	this.list.remove(index);
    }

    /**
     * Marks the task stored in the list as done
     *
     * @param index Index of the task in the list
     */
    public String done(int index) {
        return this.list.get(index).markAsDone();
    }

    /**
     * Returns the list for read only access
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds the task to the list
     *
     * @param task Task object to be added
     */
    public void add(Task task) {
    	this.list.add(task);
    }

    /**
     * Returns the size of the list
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Prints all the tasks in the list
     */
    public String printList() {
        String listStr = "";
    	System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
            listStr += i + "." + list.get(i - 1);
            if (i != list.size()) {
                listStr += "\n";
            }
        }
        return listStr;
    }

    /**
     * Prints all the tasks in the list that have matching keyword
     *
     * @param keyword Keyword to search for
     */
    public String printListWithKeyword(String keyword) {
        System.out.println("     Here are the matching tasks in your list:");
        String listStr = "";
        int num = 1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(keyword)) {
                System.out.println("     " + num + "." + list.get(i));
                listStr += num + "." + list.get(i);
                if (i != list.size() - 1) {
                    listStr += "\n";
                }
                num++;
            }
        }
        return listStr;
    }
}
