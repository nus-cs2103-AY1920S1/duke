package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;
import java.util.ArrayList;


/**
 * Represents a TaskList Class which contains the entire
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

    public boolean isDuplicatedTask(String inputInstruction, String type, Ui ui) {
        String inputDescription;
        try {
            if (type.equals("todo")) {
                if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                    throw new DukeException("todo");
                }
                inputDescription = inputInstruction.substring(5);
            } else if (type.equals("deadline")) {
                if (!inputInstruction.contains("/by") || inputInstruction.length() == 8
                        || inputInstruction.length() == 9) {
                    throw new DukeException("deadline");
                }
                inputDescription = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
            } else if (type.equals("event")) {
                if (!inputInstruction.contains("/at") || inputInstruction.length() == 5
                        || inputInstruction.length() == 6) {
                    throw new DukeException("event");
                }
                inputDescription = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
            } else {
                inputDescription = "";
            }
            for (int i = 0; i < listOfTask.size(); i++) {
                Task currentTask = listOfTask.get(i);
                if (currentTask.getDescription().equals(inputDescription)) {
                    return true;
                }
            }
        } catch (DukeException e) {
            ui.printException(e);
        }
        return false;
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
