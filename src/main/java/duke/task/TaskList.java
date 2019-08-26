package duke.task;

import duke.ui.Ui;

import duke.exception.DukeException;

import java.util.LinkedList;
import java.util.List;

public class TaskList {

    List<Task> tasks;

    public TaskList() {
        tasks = new LinkedList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(String command, Ui ui) throws DukeException {
        String[] words = command.split(" ");
        String type = words[0];
        Task task;

        try {
            if (type.equalsIgnoreCase("todo")) {
                task = new Todo(command.substring(5));
            } else {
                int index = command.indexOf('/');
                if (type.equalsIgnoreCase("deadline")) {
                    task = new Deadline(command.substring(9, index - 1), command.substring(index + 4));
                } else if (type.equalsIgnoreCase("event")) {
                    task = new Event(command.substring(6, index - 1), command.substring(index + 4));
                } else {
                    throw new IllegalArgumentException("     ☹ OOPS!!!No such task type.");
                }
            }
            tasks.add(task);
            ui.addedTask(task, tasks.size());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Deadline <description> /by <DD/MM/YYYY> <XX:XX>");
        } catch (IllegalArgumentException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void deleteTask(String command, Ui ui) throws DukeException {
        try {
            String[] done = command.split(" ");
            int number = Integer.valueOf(done[1]);
            Task task = tasks.get(number - 1);
            tasks.remove(number - 1);
            ui.deletedTask(task, tasks.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number does not exist.");
        }
    }

    public void doneTask(String command, Ui ui) throws DukeException {
        try {
            String[] done = command.split(" ");
            int number = Integer.valueOf(done[1]);
            if (tasks.get(number - 1).isCompleted()) {
                throw new DukeException("     ☹ OOPS!!! The task is already marked as done.");
            } else {
                tasks.get(number - 1).markAsDone();
                ui.doneTask(tasks, number);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number does not exist.");
        }
    }
}
