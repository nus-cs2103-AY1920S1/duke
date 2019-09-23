package duke.reminder;

import duke.sheet.Sheet;
import duke.ui.Ui;

/**
 * Calls a reminder at the start of the programme listing specified number of tasks based on their proximity
 * to deadline.
 */
public class Reminder {

    private Sheet sh;
    private Ui ui;

    public Reminder(Sheet sh, Ui ui) {
        this.sh = sh;
        this.ui = ui;
    }

    /**
     * Calls a reminder at the start of the programme listing specified number of tasks based on their proximity
     * to deadline.
     *
     * @param numOfTask Specified number of tasks to remind;
     */
    public void remind(int numOfTask) {
        String sortedTask = sh.sortList(numOfTask);
        ui.showReminder(sortedTask);
    }
}
