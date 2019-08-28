import java.util.ArrayList;

public class DeleteCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        // InputMismatchException is handled in the Ui class
        int index = ui.readIndex();
        // IndexOutOfBoundsException is caught in TaskList; removed can be Task | null
        Task removed = taskList.deleteTask(index);
        // null => IndexOutOfBoundsException was caught
        if (removed != null) {
            ui.print("Noted. I've removed this task:");
            ui.print(removed.toString());
            ArrayList<Task> tasks = taskList.getArr();
            storage.save(tasks);
            ui.print("Now you have " + tasks.size() +
                    (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
        } // else case handled in TaskList : user will be alerted to the IndexOutOfBoundsException.
    }

}
