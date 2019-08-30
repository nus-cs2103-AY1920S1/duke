import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

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
        if (taskList.size() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    void markTask(int index) throws DukeException {
        if (taskList.size() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        try {
            taskList.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + taskList.get(index - 1));
        } catch (NumberFormatException e) {
            throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'done'!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
        }
    }

    void deleteTask(int index) throws DukeException {
        if (taskList.size() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        }
        try {
            Task currentTask = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n  " + currentTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new NotAnIntegerTaskListException("OOPS!!! Please enter an integer after 'delete'!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIntegerTaskListException("OOPS!!! Please enter a valid task number!");
        }
    }

    private void addTask(Task currentTask) {
        taskList.add(currentTask);
        System.out.println("Got it. I've added this task:\n  " + currentTask);
    }

    void createToDo(String taskContent) {
        Task currentTask = new ToDoTask((taskContent));
        this.addTask(currentTask);
    }

    void createDeadline(String taskContent, LocalDateTime taskTime) {
        Task currentTask = new DeadlineTask(taskContent, taskTime);
        this.addTask(currentTask);
    }

    void createEvent(String taskContent, LocalDateTime taskTime) {
        Task currentTask = new EventTask(taskContent, taskTime);
        this.addTask(currentTask);
    }
    
    ArrayList<Task> findTasks(String taskToFind) {
        ArrayList<Task> listOfFoundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(taskToFind)) {
                listOfFoundTasks.add(task);
            }
        }
        return listOfFoundTasks;
    }
}
