package commands;

import tasks.Task;
import storage.Storage;
import util.TaskList;
import ui.Ui;

import java.lang.Integer;
import java.io.IOException;

public class DoneCommand extends Command {

    private String taskNumber;

    public DoneCommand(String imperative, String content) {
        super(imperative);
        this.taskNumber = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (tasks.isEmpty()) {
            // if the user is trying this command on an empty task list
            ui.showEmptyListError();
        } else {
            try {
                int taskIndex = Integer.parseInt(taskNumber) - 1;

                // retrieve task from list, mark as done, and inform the user
                Task taskToMarkDone = tasks.get(taskIndex);
                if (!taskToMarkDone.getIsDone()) {
                    taskToMarkDone.setTaskAsDone(true);

                    // print message
                    ui.showTaskDoneMessage(taskToMarkDone);

                    // update storage
                    storage.update(tasks);
                } else {
                    System.out.println("This task has already been done!");
                }
            } catch (IndexOutOfBoundsException exceptionOne) {
                ui.showIndexOutOfBoundsError();
            } catch (NumberFormatException exceptionTwo) {
                ui.showInvalidIndexError();
            }
        }
    }
}
