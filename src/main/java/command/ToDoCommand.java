package command;

import exception.ToDoException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;

public class ToDoCommand extends Command {

    protected String[] input;

    public ToDoCommand(String[] input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (input.length <= 1) {
            System.err.println("      " + new ToDoException());
        } else {
            Task newToDoTask = new ToDo(input[1]);
            tasks.getTaskList().add(newToDoTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newToDoTask);
            if (tasks.getTaskList().size() > 1) {
                System.out.println("     Now you have " + tasks.getTaskList().size() + " tasks in the list.");
            } else {
                System.out.println("     Now you have " + tasks.getTaskList().size() + " task in the list.");
            }
        }
    }

    public boolean isExit() {
        return false;
    }
}
