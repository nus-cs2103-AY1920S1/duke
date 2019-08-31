package seedu.duke.task;

import java.text.ParseException;
import java.util.Date;
import seedu.duke.parser.DateParser;

public class Deadline extends Task{
    protected String strBy;
    protected Date by;

    public Deadline(String description, String strBy){
        super(description);
        this.strBy = strBy;
        this.by = new DateParser().parseDate(strBy);
    }

    @Override
    public String toFile(){
        return "D | " + (isDone?"1":"0") + " | " + this.description + " | " + this.strBy + "\n";
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + strBy + ")";
    }
}
