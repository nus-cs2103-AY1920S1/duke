package duke;

import exception.DukeException;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    String underline = "____________________________________________________________\n";
    private String doubleLine(String msg) {
        return underline + msg + "\n" + underline;
    }
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        tasks.remove(index);
    }

    public void doneTask(int index) throws DukeException {
        tasks.get(index).markAsDone();
    }

    private void doneTask(String tokenString, String[] token) throws IOException {
        try {
            if(tokenString.length() == 4) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
            int taskDone = Integer.parseInt(token[1]) - 1;
            if(tasks.size() == 0) {
                throw new DukeException("You have no tasks to be done.");
            } else if(taskDone >= tasks.size() || taskDone < 0) {
                throw new DukeException("Invalid task done. Insert a number from 1 to " + tasks.size() + ".");
            }
            tasks.get(taskDone).markAsDone();
            System.out.println(underline + "Nice! I've marked this task as done:\n  " + tasks.get(taskDone) + "\n" + underline);
        } catch (DukeException e) {
            System.out.print(doubleLine(e.getMessage()));
        }
    }

    private void deleteTask(String tokenString, String[] token) throws IOException {
        try {
            if(tokenString.length() == 6) {
                throw new DukeException("Give me a goddamn numbered task to delete.");
            }
            int taskDeleted = Integer.parseInt(token[1]) - 1;
            if(tasks.size() == 0 ) {
                throw new DukeException("You have no tasks to be deleted.");
            } else if (taskDeleted < 0 || taskDeleted >= tasks.size()) {
                throw new DukeException("Invalid task deleted. Insert a number from 1 to " + tasks.size() + ".");
            } else {
                Task task = tasks.get(taskDeleted);
                tasks.remove(taskDeleted);
                System.out.println(underline + "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n" + underline);
            }
        } catch (DukeException e) {
            System.out.print(doubleLine(e.getMessage()));
        }
    }


}
