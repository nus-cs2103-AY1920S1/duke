package duke.tasklist;

import duke.UI.GuiUi;
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
     * wrapper class for Task
     * only responsible for actions that directly manipulates the actual list of task.
     * Does not deal with saving or loading of tasks from storage
     * @param listOfTasks
     * @param storage
     */

    public GuiTaskList(ArrayList<Task> listOfTasks, Storage storage) {
        this.store = listOfTasks;
        this.storage = storage;

    }

    public GuiTaskList(ArrayList<Task> listOfTasks) {
        this.store = listOfTasks;
    }

    /**
     * Method that displays the tasks on the current list to users
     */
    public String listTask() {
        String result = "";
        int num = 1;
        result += "Here are the tasks in your list:";
        result += "\n";
        for (Task i : store) {
            result += String.valueOf(num);
            result += (". " + i);
            result += "\n";
            num++;
        }
        return result;

    }

    /**
     * Create a done version of task at index
     * @param index refers to the position of specified task that user inputs
     */

    public String doneTask(int index) {
        String result = "";
        Task taskToModify = store.get(index);
        taskToModify.markAsDone();
        result += "Nice! I've marked this task as done: ";
        result += "\n";
        result += taskToModify.toString();
        storage.saveTaskToFile(store);
        return result;
    }

    /**
     * Creates a todo task based on user input
     * @param toDoTaskString user input
     */

    public String addToDoTask(String toDoTaskString) {
        String result = "";
        //.trim() to remove trailing space
        Task toDoTask = new ToDo(toDoTaskString);
        store.add(toDoTask);
        storage.saveTaskToFile(store);
        result += GuiUi.printGotIt();
        result += "\n";
        result += (" " + toDoTask.toString());
        result += "\n";
        result += (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Creates a deadline task based on user input
     * @param deadlineTaskDescriptionString User input for description of task
     * @param deadlineTaskDateAndTimeString date and time specified. should be in d/mm/yyyy HHmm format
     */

    public String addDeadlineTask(String deadlineTaskDescriptionString, String deadlineTaskDateAndTimeString) {
        String result = "";
        Task deadlineTask = new Deadline(deadlineTaskDescriptionString, deadlineTaskDateAndTimeString);
        store.add(deadlineTask);
        storage.saveTaskToFile(store);
        result += GuiUi.printGotIt();
        result += "\n";
        result += (" " + deadlineTask.toString());
        result += "\n";
        result += (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Creates event task based on user input
     * @param eventTaskDescriptionString user input for description of task
     * @param eventTaskDateAndTimeString date and time specified. should be in d/mm/yyyy HHmm format
     */

    public String addEventTask(String eventTaskDescriptionString, String eventTaskDateAndTimeString) {
        String result = "";
        //use of .trim() to aString trailing whitespace
        Task eventTask = new Event(eventTaskDescriptionString, eventTaskDateAndTimeString);
        store.add(eventTask);
        storage.saveTaskToFile(store);
        result += GuiUi.printGotIt();
        result += "\n";
        result += (" " + eventTask.toString());
        result += "\n";
        result += (GuiUi.printNow(store.size()));
        return result;
    }

    /**
     * Delete task in list at specified position
     * @param index position of task to be deleted
     */
    public String deleteTask(int index) {
        String result = "";
        Task removed = store.remove(index);
        storage.saveTaskToFile(store);
        result += GuiUi.printNoted();
        result += "\n";
        result += removed.toString();
        result += "\n";
        result += GuiUi.printNow(store.size());
        return result;
    }

    private void addGenericTask(Task task) {
        this.store.add(task);
    }

    private String listSearchQuery() {
        String result = "";
        int num = 1;
        for (Task i : store) {
            result += (num + ". " + i);
            result += "\n";
            num++;
        }
        return result;
    }


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
        result += ("Here are the matching tasks in your list:");
        result += "\n";
        result += temp.listSearchQuery();
        return result;
    }
}
