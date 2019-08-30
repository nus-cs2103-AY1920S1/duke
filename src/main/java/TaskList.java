import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An abstraction of the list of Task objects.
 */
public class TaskList {
    /**
     * The list of Tasks.
     */
    private ArrayList<Task> taskList;
    
    /**
     * Creates a TaskList object.
     * The list is initialized with a capacity of 100.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }
    
    /**
     * Creates a TaskList object.
     * The list is initialized as the input list.
     *
     * @param taskList The inputted list of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    /**
     * Gets the list of Tasks stored in this TaskList object.
     *
     * @return Returns this list of Tasks.
     */
    ArrayList<Task> getList() {
        return this.taskList;
    }
    
    /**
     * Gets the number of Tasks in this TaskList object.
     *
     * @return Returns the number of Tasks in this list.
     */
    int getSize() {
        return this.taskList.size();
    }
    
    /**
     * Prints the items in this TaskList.
     *
     * @throws EmptyTaskListException A DukeException indicating an empty TaskList.
     */
    void printList() throws EmptyTaskListException {
        if (taskList.size() == 0) { // if 'list' is called with no tasks currently stored
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }
    
    /**
     * Marks a Task in this TaskList as done.
     *
     * @param index The index of the Task to be marked as done.
     * @throws DukeException An Exception thrown to indicate an incorrect index input.
     */
    void markTask(int index) throws DukeException {
        if (taskList.size() == 0) { // if 'list' is empty, 'done' cannot be called
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        try {
            taskList.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index - 1));
        } catch (NumberFormatException e) { // if not a number is entered after 'done'
            throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'done'!");
        } catch (IndexOutOfBoundsException e) { // if an invalid number is entered after 'done'
            throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
        }
    }
    
    /**
     * Deletes a Task from this TaskList.
     *
     * @param index The index of the Task to be deleted.
     * @throws DukeException An Exception thrown to indicate an incorrect index input.
     */
    void deleteTask(int index) throws DukeException {
        if (taskList.size() == 0) { // if 'list' is empty, 'delete' cannot be called
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        try {
            Task currentTask = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n  " + currentTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) { // if not a number is entered after 'delete'
            throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'delete'!");
        } catch (IndexOutOfBoundsException e) { // if an invalid number is entered after 'delete'
            throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
        }
    }
    
    /**
     * Adds a Task to this TaskList.
     *
     * @param currentTask The Task object to be added.
     */
    void addTask(Task currentTask) {
        taskList.add(currentTask);
        System.out.println("Got it. I've added this task:\n  " + currentTask);
    }
    
    /**
     * Creates a ToDoTask and adds it to this TaskList.
     *
     * @param taskContent The description content of the Task to be added.
     */
    void createToDo(String taskContent) {
        Task currentTask = new ToDoTask((taskContent));
        this.addTask(currentTask);
    }
    
    /**
     * Creates a DeadlineTask and adds it to this TaskList.
     *
     * @param taskContent The description content of the Task to be added.
     * @param taskTime The time of the Task to be added, as a LocalDateTime object.
     */
    void createDeadline(String taskContent, LocalDateTime taskTime) {
        Task currentTask = new DeadlineTask(taskContent, taskTime);
        this.addTask(currentTask);
    }
    
    /**
     * Creates an EventTask and adds it to this TaskList.
     *
     * @param taskContent The description content of the Task to be added.
     * @param taskTime The time of the Task to be added, as a LocalDateTime object.
     */
    void createEvent(String taskContent, LocalDateTime taskTime) {
        Task currentTask = new EventTask(taskContent, taskTime);
        this.addTask(currentTask);
    }
}
