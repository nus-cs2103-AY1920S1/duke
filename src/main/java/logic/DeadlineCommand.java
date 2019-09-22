package logic;

import model.Tasklist;
import model.deadline;
import storage.Storage;
import ui.UI;

import java.time.format.DateTimeFormatter;

public class DeadlineCommand implements Command {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private String arguments;
    private String description;
    private String details;

    /**
     * Creates an instance of DeadlineCommand with its arguments.
     *
     * @param arguments arguments of the Command
     */
    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
        try {
            String[] sp = arguments.split("/by", 2);
            this.description = sp[0].trim();
            this.details = sp[1].trim();
        } catch (Exception E) {
            this.description = arguments;
            this.details = null;
        }
    }

    /**
     * Parses the arguments of the Command and executes it.
     *
     * @param tasks   the TaskList of Tasks
     * @param ui      The User Interface
     * @param storage Storage
     * @return
     */
    @Override
    public String execute(Tasklist tasks, UI ui, Storage storage) {
        String content = "";
        try {
            if (arguments == null || arguments.trim().equals("")) {
                content = "OOPS! The description of a deadline cannot be empty.\n" +
                        "Usage: deadline DESCRIPTION [/by DETAILS]\n";
            } else {
                deadline task = new deadline(description, details);
                tasks.add(task);

                content += "Got it. I've added this task:\n";
                if (details != null) {
                    content += "[" + task.getSymbol() + "][" + task.getIsDoneSymbol() + "] " + task.getDescription() + " (by: " + task.getTime() + ")\n";
                } else {
                    content += "[" + task.getSymbol() + "][" + task.getIsDoneSymbol() + "] " + task.getDescription() + "\n";
                }
                content += "Now you have " + tasks.size() + " tasks in this list\n";
            }
        } catch (Exception E) {
            content = "Ohno something went wrong! :(\n";
        }

        return content;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
