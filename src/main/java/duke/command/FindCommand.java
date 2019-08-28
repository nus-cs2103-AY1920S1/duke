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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
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
        printArrayList();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public void printArrayList() {
        for (int i = 0; i < toPrintArrayList.size(); i++) {
            System.out.println(i + 1 + "." + toPrintArrayList.get(i).toString());
        }
    }
}
