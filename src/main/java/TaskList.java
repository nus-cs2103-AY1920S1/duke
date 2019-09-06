import java.util.ArrayList;

/**
 * Represents a taskList with the ability to perform features on the tasks within.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
    public void addTask(Task newTask) {
        listOfTasks.add(newTask);
    }
    public void deleteTask(int number) {
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
    }

    public void printList() {
        int counter = 1;
        for (Task task : this.listOfTasks) {
            System.out.println("     " + counter + "." + task);
            counter++;
        }
    }

    /**
     * Sets a task in a specified position in listOfTasks as done.
     *
     * @param number Specified index of task.Task object in listOfTasks.
     */
    public void setTaskAsDone(int number) {
        Task temp = this.listOfTasks.get(number - 1);
        temp.setDone();
    }

    /**
     * Returns a task.Task object in a specified position in listOfTasks.
     *
     * @param number Specified index of task.Task object in listOfTasks.
     * @return task.Task
     */
    public Task getTask(int number) { //what about case where it is empty?
        return this.listOfTasks.get(number);
    }
}
