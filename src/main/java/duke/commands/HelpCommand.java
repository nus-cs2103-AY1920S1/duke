package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UiResponse;

/** Command invoked when "help" command is encountered. */
public class HelpCommand extends Command {
    /**
     * Executes the desired behaviour for the HelpCommand object. In this case,
     * this involves providing a text response for each type of HelpCommand.
     *
     * @param ui       UiResponse object that is responsible for returning a String response to GUI
     * @param storage  Storage object responsible for saving the Tasks into a pre-defined format
     * @param allTasks TaskList object containing all tasks.
     * @return String representing Duke's response
     * @throws DukeException re-thrown from underlying method calls.
     */
    public String execute(UiResponse ui, Storage storage, TaskList allTasks) throws DukeException {
        ui.reset();
        ui.addSentence("Welcome to the Duke Help Page!");
        ui.addSentence("\tlist");
        ui.addSentence("\t\tType 'list' to obtain an exhaustive list of current Tasks");
        ui.addSentence("\tsave");
        ui.addSentence("\t\tType 'save' to save the current list of Tasks to disc");
        ui.addSentence("\tdelete <num>");
        ui.addSentence("\t\tDeletes the <num>-th Task");
        ui.addSentence("\tdone <num>");
        ui.addSentence("\t\tMarks the <num>-th Task as complete");
        ui.addSentence("\tfine <keyword>");
        ui.addSentence("\t\tFinds <keyword> in the current list of Tasks");
        ui.addSentence("\ttodo <description>");
        ui.addSentence("\t\tAdds a ToDo Task with <description>");
        ui.addSentence("\tevent <description> /at <start_time> - <end_time>");
        ui.addSentence("\t\tAdds an Event Task with <description> and start/end times");
        ui.addSentence("\t\tStart/end times must be in this format: dd/MM/yyyy HH:mm");
        ui.addSentence("\tdeadline <description> /by <deadline_time>");
        ui.addSentence("\t\tAdds a Deadline Task with <description> and deadline");
        ui.addSentence("\t\t<deadline_time> must be in this format: dd/MM/yyyy HH:mm");
        ui.addSentence("\n");
        ui.addSentence("Hope this helps! :)");
        return ui.getResponse();
    }
}
