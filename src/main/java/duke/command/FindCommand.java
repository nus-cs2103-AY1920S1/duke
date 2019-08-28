package duke.command;

import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) {
        TaskList tempTaskList = new TaskList();

        for(int i = 0; i < taskList.getSize(); i++) {
            Task t = taskList.getAtIndex(i);
            if(t.getDescription().contains(keyword)) {
                tempTaskList.add(t);
            }
        }

        ui.printFoundTaskList(tempTaskList);

        return true;
    }

}
