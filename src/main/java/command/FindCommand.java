package command;

import utilities.ExpenseList;
import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Executes finding process.
     *
     * @param tasks is the taskList of tasks
     *
     * @param ui prints the output
     *
     * @param storage is for output file
     *
     * @return the output to be printed on screen
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage, ExpenseList expenses) {
        String[]splitWords = command.split(" ",2);
        String wordToFind = splitWords[1];
        TaskList findResults = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            String taskCommand = tasks.get(i).getCommand();
            if (taskCommand.contains(wordToFind)) {
                findResults.add(tasks.get(i));
            }
        }

        return ui.findCommandFX(findResults);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
