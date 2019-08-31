import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> arr;

    public TaskList(ArrayList<Task> arrayList) {
        arr = arrayList;
    }

    public TaskList() {
        arr = new ArrayList<Task>();
    }

    public ArrayList<Task> getArr() {
        return arr;
    }

    public int size() {
        return arr.size();
    }

    public Task get(int index) {
        return arr.get(index);
    }

    public Task remove(int index) {
        return arr.remove(index);
    }

    public void add(Task task) {
        arr.add(task);
    }

    /**
     * Finds Tasks that have the input string in their descriptions.
     * @param filter the String that determines whether or not Task will be included in the output.
     * @return TaskList containing subset of Tasks that contain filter in their description.
     */
    public TaskList find(String filter) {
        ArrayList<Task> tempArray = new ArrayList<>();
        for (Task tempTask : arr) {
            if (tempTask.getDescription().contains(filter)) {
                tempArray.add(tempTask);
            }
        }
        return new TaskList(tempArray);
    }

    /**
     * Determines if there are no tasks in the TaskList.
     * @return boolean representing if there are any tasks in the TaskList.
     */
    public boolean isEmpty() {
        return arr.isEmpty();
    }
}
