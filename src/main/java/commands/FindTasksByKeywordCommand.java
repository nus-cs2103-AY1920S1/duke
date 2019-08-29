package commands;

import components.Storage;
import components.TaskList;
import components.Ui;

public class FindTasksByKeywordCommand implements Command {
    private String keyword;

    public FindTasksByKeywordCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.findTaskByKeywordAndPrintList(keyword);
    }
}
