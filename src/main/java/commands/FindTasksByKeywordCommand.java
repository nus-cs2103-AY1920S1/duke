package commands;

import components.Storage;
import components.TaskList;

public class FindTasksByKeywordCommand implements Command {
    private String keyword;

    public FindTasksByKeywordCommand(String keyword) {
        this.keyword = keyword;
    }


    @Override
    public String[] execute(Storage storage, TaskList taskList) {
        return taskList.findTaskByKeywordAndPrintList(keyword);
    }
}
