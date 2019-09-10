package duke.command;

import duke.task.Note;
import duke.task.Task;

import java.util.ArrayList;

public class MarkAsDone {

    /** Done message. */
    private static String done_message = "Nice! I've marked this task as done:\n";

    static String markAsDone(ArrayList<Task> taskList, int index, Storage storage) throws DukeException {
        Task current;
        try {
            current = taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task with that ID.");
        }
        if (current instanceof Note) {
            throw new DukeException("You can't mark a note as done!");
        }
        boolean status = current.markAsComplete();
        if (!status) {
            throw new DukeException("Action already marked as done!");
        }
        String s = done_message + current;
        boolean isSaved = Save.save(storage, taskList);
        assert isSaved == true : "Error: Not saved to disk.";
        return s;
    }
}
