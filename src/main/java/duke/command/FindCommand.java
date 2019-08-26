package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {
    protected String details;

    public FindCommand(String details) {
        super();
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printMessage("\t Here are the matching tasks in your list: ");
        if (details.length() == 0) {
            for (int i = 0; i < tasks.getListSize(); i++) {
                System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
            }
        } else {
            for (int i = 0; i < tasks.getListSize(); i++) {
                if (tasks.getTask(i).getDescription().contains(details)) {
                    System.out.println("\t " + (i + 1) + ". " + tasks.getTask(i));
                }
            }
        }
    }

    public boolean isExit() {
        return false;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof FindCommand) {
            FindCommand obj = (FindCommand) o;
            return obj.getDetails().equals(details);
        } else {
            return false;
        }
    }


}
