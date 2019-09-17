package duke.command;

import duke.exception.DukeException;
import duke.initials.Task;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

public class FindCommand extends Command {

    String toFind;
    ArrayList<Task> toPrintArrayList = new ArrayList<>();

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        ui.showLine();
        toFind = ui.getRemainingWords().trim();

        ArrayList<Task> arrayList = tasks.getTaskArrayList();
        int j = arrayList.size();
        for (int i = 0; i < arrayList.size(); i++) {
            Task currentTask = arrayList.get(i);
            String currentLine = currentTask.toString();
            if (currentLine.contains(toFind)) {
                toPrintArrayList.add(currentTask);
            }
        }
        return printArrayList();
    }

    public String printArrayList() {
        String toPrint = "";
        for (int i = 0; i < toPrintArrayList.size(); i++) {
            toPrint += (i + 1 + "." + toPrintArrayList.get(i).toString());
        }
        return toPrint;
    }
}
