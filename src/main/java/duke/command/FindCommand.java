package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String execute() {
        TaskList results = this.taskList.getTaskSubsetMatching(searchWord);
        if (results.getSize() == 0) {
            return "I'm sorry, I didn't find any tasks containing the phrase\n'"
                    + searchWord
                    + "' ...";
        } else {
            return "Here are the matching tasks in your list:\n"
                    + ui.indentMessage(results.toString());
        }
    }
}
