package commands;

import components.Storage;
import components.Ui;
import tasks.Task;
import components.TaskList;

import java.util.ArrayList;

public class DeleteCommand implements Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        // IndexOutOfBoundsException is caught in components.TaskList; removed can be Task | null
        Task removed = taskList.deleteTask(index);
        // null => IndexOutOfBoundsException was caught
        if (removed != null) {
            Ui.print("Noted. I've removed this task:");
            Ui.print(removed.toString());
            ArrayList<Task> tasks = taskList.getArr();
            storage.save(tasks);
            Ui.print("Now you have " + tasks.size() +
                    (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        } // else case handled in components.TaskList : user will be alerted to the IndexOutOfBoundsException.
    }
}
