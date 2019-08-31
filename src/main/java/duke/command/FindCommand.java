package duke.command;

import duke.task.TaskList;

public class FindCommand extends Command {
    private String searchWord;

    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public void execute() {
        TaskList results = this.taskList.getTaskSubsetMatching(searchWord);
        if (results.getSize() == 0) {
            this.ui.displayMessage("I'm sorry, I didn't find any tasks containing the phrase\n'"
                    + searchWord
                    + "' ...");
        } else {
            this.ui.displaySingleLine("Here are the matching tasks in your list:");
            this.ui.displayMessage(results.toString(), 2);
        }
    }
}
