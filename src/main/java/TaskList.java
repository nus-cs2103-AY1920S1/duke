import java.util.ArrayList;
import java.util.Calendar;

public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    ArrayList<Task> getList() {
        return this.taskList;
    }

    int getSize() {
        return this.taskList.size();
    }

    void printList() throws EmptyTaskListException {
        if (taskList.size() == 0) { // if 'list' is called with no tasks currently stored
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    void markElementAsDone(int index) throws DukeException {
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

    void deleteElement(int index) throws DukeException {
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

    void createToDo(String taskContent) {
        Task currentTask = new ToDoTask((taskContent));
        taskList.add(currentTask);
        System.out.println("Got it. I've added this task:\n  " + currentTask);
    }

    void createDeadline(String taskContent, Calendar taskTime) {
        Task currentTask = new DeadlineTask(taskContent, taskTime);
        taskList.add(currentTask);
        System.out.println("Got it. I've added this task:\n  " + currentTask);
    }

    void createEvent(String taskContent, Calendar taskTime) {
        Task currentTask = new EventTask(taskContent, taskTime);
        taskList.add(currentTask);
        System.out.println("Got it. I've added this task:\n  " + currentTask);
    }
}
