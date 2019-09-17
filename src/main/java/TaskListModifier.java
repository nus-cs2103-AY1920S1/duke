import java.io.IOException;
import java.util.ArrayList;

/**
 * The class used to modify the existing task list based on the user's requests
 */

public class TaskListModifier {

    private Ui ui = new Ui();

    /**
     * Adds an additional task to the Task array list.
     * Invokes {@link Ui} class to print feedback to the user.
     * Invokes {@link FileWriting} to update the 'duke.txt' file with the new Task array list.
     *
     * @param taskList  Used to store task object information.
     * @param t The Task object.
     * @param action The type of operation that is to be done on the task list (in this case add).
     * @throws IOException For cases where user input does not conform to the other classes' requirements.
     */

    void addToTaskList(ArrayList<Task> taskList, Task t, Duke.Action action) throws IOException {
        assert action == Duke.Action.ADD;

        DuplicateChecker duplicateChecker = new DuplicateChecker(taskList);
        if (!duplicateChecker.checkDuplicate(t)) {
            taskList.add(t);
            ui.addTask(taskList);
            FileWriting.writeToFile(taskList);
        }

        assert (duplicateChecker.checkDuplicate(t)): "Somehow a non-duplicate entry made it through.";

    }

    /**
     * Removes a task or sets the status of a Task to 'done'.
     * Invokes {@link Ui} class to print feedback to the user.
     * Invokes {@link FileWriting} to update the 'duke.txt' file with the new Task array list.
     *
     * @param taskList  Used to store task object information.
     * @param taskNumber The number of the Task object.
     * @param action The type of operation that is to be done on the task list (in this case remove or set done).
     * @throws IndexOutOfBoundsException For numbers exceeding size of taskList.
     */

    void changeTaskList (ArrayList<Task> taskList, int taskNumber, Duke.Action action){
        if (action == Duke.Action.REMOVE) {
            try {
                ui.removeTask(taskList, taskNumber);
                taskList.remove(taskNumber);
                FileWriting.writeToFile(taskList);
            } catch (IndexOutOfBoundsException | IOException err){
                ui.printToConsoleAndGui("You only have " + taskList.size() +
                        " tasks, please choose a number from that.\n");
            }
        } else {

            assert action == Duke.Action.DONE;

            try {
                taskList.get(taskNumber).setDone();
                ui.setTaskDone(taskList, taskNumber);
                FileWriting.writeToFile(taskList);
            } catch (IndexOutOfBoundsException | IOException err) {
                ui.printToConsoleAndGui("You only have " + taskList.size() +
                        " tasks, please choose a number from that.\n");
            }
        }
    }
}
