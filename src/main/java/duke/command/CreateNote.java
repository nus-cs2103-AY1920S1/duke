package duke.command;

import duke.task.Note;
import duke.task.Task;

import java.util.ArrayList;

public class CreateNote {

    /** Task added successfully message. */
    private static String note_added_message = "Got it. I've added this note:\n";

    static String createNote(ArrayList<Task> taskList, String[] params, Storage storage) throws DukeException {
        if (Parser.joinStrings(params).isEmpty()) {
            throw new DukeException("You need to specify a body for the note.");
        }

        Task current = new Note(Parser.joinStrings(params));
        taskList.add(current);
        String s = note_added_message + current + TotalNoOfTasks.totalNoOfTasks(taskList);
        boolean isSaved = Save.save(storage, taskList);
        assert isSaved == true : "Error: Not saved to disk.";
        return s;
    }
}
