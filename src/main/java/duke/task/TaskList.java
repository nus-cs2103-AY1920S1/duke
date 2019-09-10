package duke.task;

import duke.exception.InvalidDateInputException;
import duke.exception.InvalidEditTaskException;
import duke.exception.InvalidTaskIndexException;
import duke.tag.Tag;
import duke.parser.DateParser;
import java.util.ArrayList;

/**
 * A Class that represents a list of all completed and uncompleted Tasks.
 */
public class TaskList {

    /**
     * Represents the maximum limit of the list of tasks.
     */
    private static final int MAX_LIST_SIZE = 100;

    /**
     * Represents the list of tasks.
     */
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new Task List which reads in 0 tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new Task List which reads in a list of tasks.
     * @param tasks The loaded list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static boolean hasHitTaskLimit() {
        return getNumberOfTasks() >= MAX_LIST_SIZE;
    }

    /**
     * Adds the ToDo Task based on the data given, and returns the index of the new task.
     * @param toDoData The name of the ToDo Task.
     * @return The index of the new task.
     */
    public int addTodoTask(String[] toDoData) {
        ToDoTask newTask = new ToDoTask(ToDoTask.getName(toDoData));
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Adds the Deadline Task based on the data given, and returns the index of the new task.
     * @param taskName The name of the Deadline Task.
     * @param taskDate The date of the Deadline Task.
     * @return The index of the new task.
     */
    public int addDeadlineTask(String taskName, String taskDate) {
        DeadlineTask newTask = new DeadlineTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Adds the Event Task based on the data given, and returns the index of the new task.
     * @param taskName The name of the Deadline Task.
     * @param taskDate The date of the Deadline Task.
     * @return The index of the new task.
     */
    public int addEventTask(String taskName, String taskDate) {
        EventTask newTask = new EventTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Completes a task based on the index given.
     * @param index The index of the task.
     * @throws InvalidTaskIndexException If the index is lees than 0 or the index exceeds the number of tasks.
     */
    public void completeTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            task.isCompleted = true;
        }
    }

    /**
     * Deletes a task based on the index given, returning the Task that was deleted.
     * @param index The index of the task.
     * @return The deleted task.
     * @throws InvalidTaskIndexException If the index is less than 0 or the index exceeds the number of tasks.
     */
    public Task deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        }
    }

    /**
     * Constructs the new edited name based on the data provided from the parser.
     * @param nameData an array consisting the words forming the new name.
     * @return the new name as a String.
     */
    private String constructNewName(String[] nameData) {
        String newName = "";
        for (int i = 1; i < nameData.length; i++) {
            if (i > 1) {
                newName += " ";
            }
            newName += nameData[i];
        }

        return newName;
    }

    /**
     * Edits the specific task based on the name and index given.
     * @param newTaskData An array which consists the index and new name.
     * @return the edited task.
     * @throws InvalidTaskIndexException if the index given is out of bounds.
     */
    public Task editSpecificTaskName(String[] newTaskData) throws InvalidTaskIndexException {
        int index = Integer.parseInt(newTaskData[0]) - 1;
        String newName = constructNewName(newTaskData);
        if (index < 0 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            task.editTaskName(newName);
            return task;
        }
    }

    /**
     * Edits the specific task based on the name and index given.
     * @param newTaskData An array which consists the index and new name.
     * @return the edited task.
     * @throws InvalidTaskIndexException if the index given is out of bounds.
     */
    public Task editSpecificTaskDate(String[] newTaskData) throws
            InvalidTaskIndexException, InvalidDateInputException, InvalidEditTaskException {
        int index = Integer.parseInt(newTaskData[0]) - 1;
        DateParser parser = new DateParser();
        parser.readInput(newTaskData[1] + " " + newTaskData[2]);
        String newDate = parser.convertDateToString();
        if (index < 0 || index > tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            if (task instanceof ToDoTask) {
                throw new InvalidEditTaskException("You cannot change the date of a todo task!");
            } else if (task instanceof EventTask) {
                ((EventTask) task).editTaskDate(newDate);
                return task;
            } else {
                ((DeadlineTask) task).editTaskDate(newDate);
                return task;
            }
        }
    }

    /**
     * Returns a list of indexes of matching tasks which contain specific keywords.
     * @param keyword The matching keyword or keywords.
     * @return A list of indexes of tasks containing the keywords.
     */
    public ArrayList<Integer> findMatchingTasks(String keyword) {
        ArrayList<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            if (task.todo.contains(keyword)) {
                matchingIndexes.add(i);
            }
        }

        return matchingIndexes;
    }

    /**
     * Returns a list of indexes of matching tasks which contain specific tag.
     * @param tag the matching tag.
     * @return A list of indexes of tasks containing the tag.
     */
    public ArrayList<Integer> findMatchingTaggedTasks(String tag) {
        ArrayList<Integer> matchingIndexes = new ArrayList<>();
        for (int i = 0; i < getNumberOfTasks(); i++) {
            Task task = tasks.get(i);
            if (task.getTag().getTagName().equals(tag)) {
                matchingIndexes.add(i);
            }
        }
        return matchingIndexes;
    }

    /**
     * Adds a tag to the task at the specified index.
     * @param index the index of the task.
     * @param tag the tag of the task.
     * @return the tagged task.
     */
    public Task addTag(int index, Tag tag) {
        Task task = this.tasks.get(index);
        task.pushTag(tag);
        return task;
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public static int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the task at the index provided.
     * @param index The index of the task.
     * @return The task to be retrieved.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }
}
