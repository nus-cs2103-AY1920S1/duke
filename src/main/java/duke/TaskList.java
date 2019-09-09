package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;


/**
 * Represents a TaskList Class which represented the entire
 * list of task that the user have entered into the program.
 * The TaskList class handles all the manipulating of the List.
 */

public class TaskList {
    private ArrayList<Task> listOfTask;

    public TaskList() {
        listOfTask = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> listOfTask) {
        assert listOfTask != null;
        this.listOfTask = listOfTask;
    }

    /**
     * Displays all the task that is currently in the TaskList.
     */
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

    public boolean isEmpty() {
        return listOfTask.isEmpty();
    }

    /**
     * Displays all task that corresponds to the user's input keyword.
     * looks through the current Tasklist to identity all task that corresponds to
     * the keyword and displays them.
     * @param taskKeyWord user input keyword that they want
     * @throws DukeException throws custom exception if input invalid
     */
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
