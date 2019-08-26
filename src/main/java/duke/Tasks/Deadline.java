package duke.Tasks;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private Date deathTime;
    SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Deadline(String task_name, String deathTime) throws DukeException {
        super(task_name);
        try {
            this.deathTime = myFormat.parse(deathTime);
        } catch (ParseException e) {
            throw new DukeException("The date input format is not correct, " +
                    "it should be in the form dd/MM/yyyy HH:mm:ss");
        }
    }

    @Override
    public String task_info() {
        String indicator;
        if (isFinished()) indicator = "[\u2713] ";
        else indicator = "[\u2715] ";
        return "[D]" + indicator + get_name() + " (by: " + myFormat.format(deathTime) + ")";
    }

    @Override
    public String record_info() {
        if (isFinished()) return "D|" + "1|" + get_name() + "|" + myFormat.format(deathTime);
        else return "D|" + "0|" + get_name() + "|" + myFormat.format(deathTime);
    }
}
