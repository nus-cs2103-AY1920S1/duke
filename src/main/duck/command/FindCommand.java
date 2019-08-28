package duck.command;

import duck.task.Task;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

public class FindCommand extends Command{

    String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList resultList = new TaskList();

        for (int i = 0; i < taskList.getTotalTask(); i++) {
            Task task = taskList.getTaskAt(i);
            if (task.getDescription().contains(keyWord)) {
                resultList.add(task);
            }
        }

        ui.showFullList(resultList);
    }
}