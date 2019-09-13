package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ",2);
        String wordToFind = splitWords[1];
        TaskList findResults = new TaskList();

        for(int i = 0; i < tasks.size(); i++){
            String taskCommand = tasks.get(i).getCommand();
            if (taskCommand.contains(wordToFind) ){
                findResults.add(tasks.get(i));
            }else{}
        }

        ui.FindCommand(findResults);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
