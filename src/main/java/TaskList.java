import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<List<String>> taskInputs) throws DukeException {
        this.tasks = new ArrayList<>();
        for (List<String> taskInput : taskInputs) {
            Task task;
            switch (taskInput.get(0)) {
            case "T":
                task = new Todo(taskInput.get(2));
                break;
            case "D":
                task = new Deadline(taskInput.get(2), taskInput.get(3));
                break;
            case "E":
                task = new Event(taskInput.get(2), taskInput.get(3));
                break;
            default:
                throw new DukeException("Could not load your tasks!");
            }
            if (taskInput.get(1).equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
    }

    public void printNumberTasks() {
        String s = tasks.size() == 1 ? "" : "s";
        System.out.println("Now you have " + tasks.size() + " task" + s + " in the list.");
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public void finishTask(String posString) throws DukeException {
        try {
            int position = Integer.parseInt(posString) - 1;
            Task task = tasks.get(position);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    public void deleteTask(String posString) throws DukeException {
        try {
            int position = Integer.parseInt(posString) - 1;
            Task oldTask = tasks.get(position);
            tasks.remove(position);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + oldTask);
            printNumberTasks();
        } catch (NumberFormatException e) {
            throw new DukeException("Your input should be a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at the given position.");
        }
    }

    public void addTask(Task task) {
        // TODO: addTask should be changed to addTodo, addEvent, addDeadline
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        printNumberTasks();
    }

    public List<String> dump() {
        ArrayList<String> dump = new ArrayList<>();
        for (Task task : tasks) {
            dump.add(task.toSaveString());
        }
        return dump;
    }
}
