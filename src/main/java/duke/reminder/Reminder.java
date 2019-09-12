package duke.reminder;

import duke.sheet.Sheet;
import duke.ui.Ui;

public class Reminder {

    private Sheet sh;
    private Ui ui;

    public Reminder(Sheet sh, Ui ui) {
        this.sh = sh;
        this.ui = ui;
    }

    public void remind(int numOfTask) {
        String sortedTask = sh.sortList(numOfTask);
        ui.showReminder(sortedTask);
    }
}
