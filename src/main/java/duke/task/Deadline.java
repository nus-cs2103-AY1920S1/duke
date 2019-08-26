package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    protected String by;

    protected int date;
    protected int month;
    protected int year;
    protected int time;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;

        try {
            date = Integer.valueOf(by.substring(0, 2));
            month = Integer.valueOf(by.substring(3, 5));
            year = Integer.valueOf(by.substring(6, 10));
            String[] temp = by.split(" ");
            time = Integer.valueOf(temp[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Deadline <description> /by <DD/MM/YYYY> <XX:XX>\"");
        } catch (NumberFormatException e) {
            throw new DukeException("     ☹ OOPS!!! Wrong input format. \"Deadline <description> /by <DD/MM/YYYY> <XX:XX>\"");
        }
    }

    @Override
    public String getDescription() {
        return super.description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}