package duke.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    protected Date time;

    public Deadline(String description, String time) throws DukeException {
        super(description);
        this.type = Type.D;
        try {
            SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.time = formatter.parse(time);
        } catch (ParseException e) {
            throw new DukeException("Time format wrong");
        }

    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), time);
    }

    @Override
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy HHmm");
        return String.format("%s | %s | %s | %s", type, doneState, description, formatter.format(time));
    }
}
