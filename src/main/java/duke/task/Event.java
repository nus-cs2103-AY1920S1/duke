package duke.task;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected String time;

    public Event(String description, String time) throws DukeException {
        super(description);
        this.type = Type.E;
        this.time = time;
//        try {
//            SimpleDateFormat formatter =new SimpleDateFormat("dd/MM/yyyy HHmm");
//            this.type = Type.E;
//            this.time = formatter.parse(time);
//        } catch (ParseException e) {
//            throw new DukeException("Time format wrong");
//        }
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }

    @Override
    public String toFile() {
        String doneState = isDone ? "1" : "0";
        return String.format("%s | %s | %s | %s", type, doneState, description, time);
    }
}
