package duke.command;

import duke.task.Task;
import java.util.ArrayList;

public class Delete {

    /** Task deleted successfully message. */
    private static String delete_message = "Noted. I've removed this task:\n";

    static String delete(ArrayList<Task> taskList, int index, Storage storage) throws DukeException {
        Task current;
        try {
            current = taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        String s = delete_message + current;
        boolean isSaved = Save.save(storage, taskList);
        assert isSaved == true : "Error: Not saved to disk.";
        return s;
    }
}
