import java.text.SimpleDateFormat;

public class AddEventCommand extends Command {
    private String instruction;
    private String date;

    /**
     * Represents the command of adding an event task to tasklist.
     * @param instruction refers to the event task
     * @param date refers to the date of the event
     */
    public AddEventCommand(String instruction, String date) {
        this.instruction = instruction;
        this.date = date;
    }

    /**
     * Returns the message DukeBot will show after each interaction with the user.
     * @param tasks refers to the list of tasks DukeBot is handling
     * @param ui refers to the instance of the UI class which handles output messages
     * @param storage refers to the instance of Storage class which handles read-write to the .txt file
     * @return response of DukeBot to the given user query
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        try {
            if (instruction.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of event cannot be empty");
            } else if (date.equals("")) {
                throw new DukeException("☹ OOPS!!! The event must be at by a certain date");
            } else {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                    Event et = new Event(instruction, formatter.parse(date));
                    if (tasks.contains(et)) {
                        throw new DukeException("Item already exists in the list!");
                    } else {
                        tasks.addItemToList(et);
                    }
                } catch (java.text.ParseException exp) {
                    return exp.getMessage();
                }
                try {
                    storage.append(tasks.getLastItem());
                } catch (Exception exp) {
                    return ui.showErrorMsg(exp.getMessage());
                }

            }
        } catch (DukeException exp) {
            return ui.showErrorMsg(exp.getMessage());
        }

        return ui.showTaskAddedMsg(tasks.getLastItem(), tasks.getListSize());
    }

}
