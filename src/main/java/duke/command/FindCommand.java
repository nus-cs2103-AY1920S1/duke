package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    String textToFind;

    public FindCommand(String textToFind){
        this.textToFind = textToFind;
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ArrayList<Task> taskArrList = taskList.getTaskList();

        int counter = 1;
        String listOutput = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < taskArrList.size(); i++) {
            //Get tasks
            Task task = taskArrList.get(i);

            if(task.toString().contains(textToFind)) {
                listOutput += (counter) + "." + task + "\n";
                counter++;
            }

            if (i + 1 == taskArrList.size() && counter != 1) { // Remove extra break line
                listOutput = listOutput.substring(0, listOutput.length() - 2);
            }
        }
        ui.printOutput(listOutput);
    }
}
