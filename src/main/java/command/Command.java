package command;

import exception.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;


public class Command {
    private Operation op;
    private Task task;
    private int number;

    public Command(Operation operation, Task task, int number) {
        this.op = operation;
        this.task = task;
        this.number = number;
    }

    public Command(Operation operation) {
        this(operation, null, -1);
    }

    public Command(Operation operation, Task task) {
        this(operation, task, -1);
    }

    public Command(Operation operation, int number) {
        this(operation, null, number);
    }

    public boolean isExit() {
        if (op == Operation.EXIT) return true;
        else return false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        switch (op) {
        case ADD:
            add(taskList, task, ui);
            break;
        case LIST:
            list(taskList, ui);
            break;
        case MARKDONE:
            markDone(taskList, number, ui);
            break;
        case DELETE:
            delete(taskList, number, ui);
            break;
        case EXIT:
            exit(taskList, ui, storage);
            break;
        default:
            throw new DukeException("Command is invalid");
        }
    }

    private void add(TaskList taskList, Task newTask, Ui ui) {
        taskList.addTask(newTask);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n  " + newTask);
        sb.append("\nNow you have " + taskList.getSize() + " tasks in the list.");
        ui.print(sb.toString());
    }

    private void list(TaskList taskList, Ui ui) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append("\n" + (i + 1) + "." + taskList.getTask(i+1));
        }
        ui.print(sb.toString());
    }

    private void markDone(TaskList taskList, int number, Ui ui) throws DukeException {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:");
        if (number >= taskList.getSize()) throw new DukeException("It's an invalid task");
        Task doneTask = taskList.markDone(number);
        sb.append("\n" + doneTask);
        ui.print(sb.toString());
    }

    private void delete(TaskList taskList, int number, Ui ui) throws DukeException {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:");
        if (number >= taskList.getSize()) throw new DukeException("It's an invalid task");
        Task deletedTask = taskList.deleteTask(number);
        sb.append("\n" + deletedTask);
        ui.print(sb.toString());
    }

    private void exit(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.getList());
        ui.print("Bye. Hope to see you again soon!");
    }

    public enum Operation {
        ADD, LIST, MARKDONE, DELETE, EXIT;
    }
}
