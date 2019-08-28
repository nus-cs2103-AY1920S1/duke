/**
 * Simulates a find command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

import java.util.ArrayList;

public class FindCommand extends Command {

    private String word;

    /**
     * Creates a FindCommand instance with the appropriate attributes.
     * @param word The word to be found.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the find command; finds tasks with the word from the task list,
     * and prints the found message.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> arrayList = taskList.getArrayList();
        TaskList foundList = new TaskList();

        for(Task task : arrayList) {
            if(task.getDescription().contains(word)) {
                foundList.addTask(task);
            }
        }

        ui.printFoundMessage(foundList);
    }

}