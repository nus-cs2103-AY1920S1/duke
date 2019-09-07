package duke.tasklist;

import duke.ui.GuiUi;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class GuiTaskList {
    ArrayList<Task> store;
    Storage storage;

    /**
     * wrapper class for Task.
     * only responsible for actions that directly manipulates the actual list of task.
     * Does not deal with saving or loading of tasks from storage.
     * @param listOfTasks List of task objects stored
     * @param storage storage instance to interact with file
     */
    public GuiTaskList(ArrayList<Task> listOfTasks, Storage storage) {
        assert listOfTasks != null : "list of tasks read should not be null";
        this.store = listOfTasks;
        this.storage = storage;
    }

    public GuiTaskList(ArrayList<Task> listOfTasks) {
        this.store = listOfTasks;
    }

    /**
     * Method that displays the tasks on the current list to users.
     */
    public String listTask() {
        String result = "";
        int num = 1;
        result = result + "Here are the tasks in your list:" + "\n";
        for (Task i : store) {
            result = result + String.valueOf(num) + (". " + i) + "\n";
            num++;
        }
        return result;
    }

    /**
     * Create a done version of task at index.
     * @param index refers to the position of specified task that user inputs
     */

    public String doneTask(int index) {
        String result = "";
        Task taskToModify = store.get(index);
        taskToModify.markAsDone();
        result = result + "Nice! I've marked this task as done: "
                + "\n" + taskToModify.toString();
        storage.saveTaskToFile(store);
        return result;
    }

    /**
     * Creates a todo task based on user input.
     * @param toDoTaskString user input
     */

    public String addToDoTask(String toDoTaskString) {
        String result = "";
        //.trim() to remove trailing space
        Task toDoTask = new ToDo(toDoTaskString);
        store.add(toDoTask);
        storage.saveTaskToFile(store);
        result = result + GuiUi.printGotIt() + "\n" + (" "
                + toDoTask.toString()) + "\n"
                + (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Creates a deadline task based on user input.
     * @param deadlineTaskDescriptionString User input for description of task
     * @param deadlineTaskDateAndTimeString date and time specified. should be in d/mm/yyyy HHmm format
     */

    public String addDeadlineTask(String deadlineTaskDescriptionString,
                                  String deadlineTaskDateAndTimeString) {
        String result = "";
        Task deadlineTask = new Deadline(deadlineTaskDescriptionString,
                deadlineTaskDateAndTimeString);
        store.add(deadlineTask);
        storage.saveTaskToFile(store);
        result = result + GuiUi.printGotIt() + "\n" + (" "
                + deadlineTask.toString()) + "\n"
                + (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Creates event task based on user input.
     * @param eventTaskDescriptionString user input for description of task
     * @param eventTaskDateAndTimeString date and time specified. should be in d/mm/yyyy HHmm format
     */

    public String addEventTask(String eventTaskDescriptionString, String eventTaskDateAndTimeString) {
        String result = "";
        //use of .trim() to aString trailing whitespace
        Task eventTask = new Event(eventTaskDescriptionString, eventTaskDateAndTimeString);
        store.add(eventTask);
        storage.saveTaskToFile(store);
        result = result + GuiUi.printGotIt() + "\n" + (" "
                + eventTask.toString()) + "\n"
                + (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Delete task in list at specified position.
     * @param index position of task to be deleted
     */
    public String deleteTask(int index) {
        String result = "";
        Task removed = store.remove(index);
        storage.saveTaskToFile(store);
        result = result + GuiUi.printNoted() + "\n"
                + removed.toString() + "\n"
                + GuiUi.printNow(store.size());
        return result;
    }

    /**
     * method to edit both desc and time for Task.
     * @param index position of task instance
     * @param input new desc
     * @param newDateAndTime new date and time
     * @return confirmation that task has being updated
     */
    public String editTask(int index, String input, String newDateAndTime) {
        try {
            int actualIndex = index - 1;
            Task taskToEdit = store.get(actualIndex);
            taskToEdit.editTaskDesc(input);
            taskToEdit.editDateAndTime(newDateAndTime);
            storage.saveTaskToFile(store);
            return "Okay, i have edited this task:\n" + taskToEdit.toString()
                    + "\n" + this.listTask();
        } catch (IndexOutOfBoundsException e) {
            return "number of task specified in list is incorrect";
        }
    }

    /**
     * overloaded method to switch between editing only desc and only time.
     * @param index position of task instance
     * @param input new desc
     * @param isTime new time
     * @return confirmation that task has been modified
     */
    public String editTask(int index, String input, boolean isTime) {
        int actualIndex = index - 1;
        Task taskToEdit = store.get(actualIndex);
        if (isTime) {
            taskToEdit.editDateAndTime(input);
        } else {
            taskToEdit.editTaskDesc(input);
        }
        storage.saveTaskToFile(store);
        return "Okay, i have edited this task:\n" + taskToEdit.toString()
                + "\n" + this.listTask();
    }

    private void addGenericTask(Task task) {
        this.store.add(task);
    }

    private String listSearchQuery() {
        String result = "";
        int num = 1;
        for (Task i : store) {
            result = result + (num + ". " + i) + "\n";
            num++;
        }
        return result;
    }

    /**
     * Finds a task.
     * @param query input from user
     * @return string of task
     */
    public String findTask(String query) {
        String result = "";
        ArrayList<String> listOfAllDesc = new ArrayList<>();
        for (Task i : store) {
            listOfAllDesc.add(i.getDescription());
        }
        GuiTaskList temp = new GuiTaskList(new ArrayList<Task>());
        for (int i = 0; i < store.size(); i++) {
            String eachDescription = listOfAllDesc.get(i);
            if (eachDescription.contains(query)) {
                temp.addGenericTask(store.get(i));
            }
        }
        result = result + ("Here are the matching tasks in your list:")
                + "\n" + temp.listSearchQuery();
        return result;
    }
}
