import java.util.ArrayList;

class TaskList {
    /**
     * the list of all tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * constructor.
     * @param taskList that is returned from storage.
     */
    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * how many tasks in the list.
     * @return number of tasks.
     */
    int getSize() {
        return taskList.size();
    }

    /**
     * get the task at (index+1)th position.
     * @param index of task.
     * @return the task
     */
    Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * add a task to the list.
     * @param task to be added.
     */
    void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * delete a task from list at (index+1)th position
     * @param index of task.
     */
    void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * print the whole list of tasks
     */
    void printList() {
        int counter = 1;
        for (Task t: taskList) {
            System.out.println("      " + counter + "." + t) ;
            counter++;
        }
    }

}
