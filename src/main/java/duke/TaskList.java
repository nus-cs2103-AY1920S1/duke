package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.exceptions.DukeException;
import duke.tasks.Todo;

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

    /**
     * Checks whether a Task description is duplicated.
     * @param inputInstruction input task description
     * @param type type of task input
     * @param ui inputs the ui object
     * @return a boolean value whether the inputInstruction is a duplicate
     */
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
                String currentTaskString = currentTask.toString().toLowerCase();
                if (currentTaskString.contains(taskKeyWord.toLowerCase())) {
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

    /**
     * Sorts the currentTasklist to list in the following order
     * 1) To-do 2) Deadline 3) Event Task.
     */
    public void sortTaskByEventType() {
        ArrayList<Task> newListOfTask = new ArrayList<>();
        for (int typeNo = 0; typeNo < 3; typeNo++) {
            for (int i = 0; i < listOfTask.size(); i++) {
                Task currentTask = listOfTask.get(i);
                if (typeNo == 0) {
                    if (currentTask instanceof Todo) {
                        newListOfTask.add(currentTask);
                    }
                }
                if (typeNo == 1) {
                    if (currentTask instanceof Deadline) {
                        newListOfTask.add(currentTask);
                    }
                }
                if (typeNo == 2) {
                    if (currentTask instanceof Event) {
                        newListOfTask.add(currentTask);
                    }
                }
            }
        }
        listOfTask = newListOfTask;
    }

    /**
     * Sorts the currentTasklist to list in the order of time,
     * with the earliest event as the first.
     */
    public void sortTaskByTime() {
        ArrayList<Task> restOfListOfTask = new ArrayList<>();
        ArrayList<Task> newListOfTodoTask = new ArrayList<>();
        for (int j = 0; j < listOfTask.size(); j++) {
            Task currentTask = listOfTask.get(j);
            if (currentTask instanceof Todo) {
                newListOfTodoTask.add(currentTask);
            } else {
                restOfListOfTask.add(currentTask);
            }
        }
        ArrayList<Task> newListOfTask = new ArrayList<>();
        while (!restOfListOfTask.isEmpty()) {
            Task currentEarliestTask = restOfListOfTask.get(0);
            for (int i = 0; i < restOfListOfTask.size(); i++) {
                Task currentTask = restOfListOfTask.get(i);
                if (currentEarliestTask.getDateTime().isAfter(currentTask.getDateTime())) {
                    currentEarliestTask = currentTask;
                }
            }
            newListOfTask.add(currentEarliestTask);
            restOfListOfTask.remove(currentEarliestTask);
        }
        newListOfTask.addAll(newListOfTodoTask);
        listOfTask = newListOfTask;
    }
}
