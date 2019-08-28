import java.util.AbstractCollection;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTask;

    public TaskList() {
        listOfTask = new ArrayList<>();
    }

    public void listTask() {
        if (listOfTask.size() == 0) {
            System.out.println("List is Empty");
        } else {
            for (int i = 0; i < listOfTask.size(); i++) {
                System.out.println(i + 1 + "." + listOfTask.get(i));
            }
        }
    }

    public Task getTask(int index) {
        return listOfTask.get(index);
    }

    public ArrayList<Task> getEntireList() {
        return listOfTask;
    }

    public void addTask(Task newTask) {
        listOfTask.add(newTask);
    }

    public int getNoOfTask() {
        return listOfTask.size();
    }

    public void removeTask(int index) {
        listOfTask.remove(index);
    }

    public void findTask(String taskKeyWord) throws DukeException {
        if (listOfTask.size() == 0) {
            System.out.println("List is Empty");
        } else {
            ArrayList<Task> tempTaskList = new ArrayList<>();
            for (int i = 0; i < listOfTask.size(); i++) {
                Task currentTask = listOfTask.get(i);
                if (currentTask.toString().contains(taskKeyWord)) {
                    tempTaskList.add(currentTask);
                }
            }
            if (tempTaskList.isEmpty()) {
                throw new DukeException("find");
            } else {
                for (int i = 0; i < tempTaskList.size(); i++) {
                    System.out.println(i + 1 + "." + tempTaskList.get(i));
                }
            }
        }
    }
}
