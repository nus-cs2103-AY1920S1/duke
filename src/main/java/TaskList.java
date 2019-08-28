import java.util.ArrayList;

/**
 * Represents a tasklist that will keep track of the tasks that the user enters.
 * It consists of methods that will add, delete and mark complete tasks in the current list.
 */
public class TaskList {

    private ArrayList<Task> list;

    /**
     * Creates a tasklist object by creating a new empty arraylist.
     */
    public TaskList() {

        this.list = new ArrayList<>();
    }

    /**
     * Creates a tasklist object with an existing list of tasks.
     *
     * @param currentList of existing tasks reloaded from the duke.txt file.
     */
    public TaskList(ArrayList<Task> currentList) {

        this.list = currentList;
    }

    /**
     * Returns the current list.
     *
     * @return the existing ArrayList.
     */
    public ArrayList<Task> getList() {

        return this.list;

    }

    /**
     * Adds a task to the list attribute of this TaskList object.
     *
     * @param current the Task object to be added to the list.
     */
    public void addTask(Task current) {

        this.list.add(current);
        if(list.size() >1) {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list. ");
        } else {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list. ");
        }

    }

    /**
     * Deletes a task off the list attribute of this TaskList object.
     *
     * @param current the Task object to be deleted off the list.
     */
    public void deleteTask(int current) {

        Task deleted = list.get(current);
        list.remove(current);
        if(list.size() > 1) {
            System.out.println("Noted. I've removed this task: \n" + "   " + deleted.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list. ");
        } else {
            System.out.println("Noted. I've removed this task: \n" + "   " + deleted.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list. ");
        }

    }

    /**
     * Marks the task to be completed. The method removes the task by referencing the task based on its position on the
     * list.
     *
     * @param taskNumber which is an integer, which refers to the position of the task in the current list.
     */
    public void completeTask(int taskNumber) {

        Task completed = list.get(taskNumber);
        completed.markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" + "   " + completed);

    }

    /**
     * Prints the current existing list of tasks.
     */
    public void printList() {
        int n = 1;

        if(list.isEmpty()){

            System.out.println("List is empty");

        } else {

            for (Task item : list) {
                System.out.println(n + "." + item);
                n++;
            }

        }
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> list = new ArrayList<>();
        for(Task current : this.list) {
            String taskInString = current.description;

            if(taskInString.toLowerCase().contains(keyword.toLowerCase())){
                list.add(current);
            }
        }
        return list;
    }

}
