package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    /**
     * Executes the current command.
     *
     * @param ui       Ui object.
     * @param storage  Storage object.
     * @param taskList TaskList object.
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        String helpDesc = "Valid commands\n(description separated with a dash):\n\n" +
            "-\n"+
            ": todo [description]\n" +
            "-\n"+
            ": deadline [description] /by [date in DD/MM/YYYY HH:mm format]\n" +
            "-\n"+
            ": event [description] /at [date in DD/MM/YYYY HH:mm format]\n" +
            "-\n"+
            ": done [task no.] - Task no. from list/upcoming command\n" +
            "-\n"+
            ": delete [task no.] - Task no. from list/upcoming command\n" +
            "-\n"+
            ": list - Lists the current tasks\n" +
            "-\n"+
            ": upcoming - Lists the upcoming tasks\n" +
            "-\n"+
            ": find [description] - Find a task\n" +
            "-\n"+
            ": help - Access the command page\n" +
            "-\n"+
            ": bye - Exits the program";

        return helpDesc;
    }
}
