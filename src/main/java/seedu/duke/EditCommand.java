package seedu.duke;

import java.io.IOException;
import java.util.Date;

public class EditCommand extends Command {
    protected static String details;
    protected static int position;

    /**
     * Constructor.
     * @param details String containing new Task details.
     * @param position integer representing position of Task to edit in the taskList
     */
    public EditCommand(int position, String details) {
        this.details = details;
        this.position = position;
    }

    @Override
    public ChatDisplay execute(TaskList t, Ui u, Storage s) {
        Task tobeEdited = t.list.get(position);
        String type = tobeEdited.getType();
        String original = tobeEdited.toString();
        if (type.equals("T")) {
            tobeEdited.editTask(details, "");
        } else if (type.equals("D")) {
            String[] arr = details.split("/by");
            tobeEdited.editTask(arr[0].trim(), arr[1].trim());
        } else if (type.equals("E")) {
            String[] arr = details.split("/at");
            tobeEdited.editTask(arr[0].trim(), arr[1].trim());
        }
        String newTaskString = tobeEdited.toString();
        try {
            s.update(false, "", t);
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return u.editMessage(original, newTaskString);
    }
}
