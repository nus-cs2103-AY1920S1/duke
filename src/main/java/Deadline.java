import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Deadline extends Task{
    private Date by;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        Date date=new SimpleDateFormat("dd/MM/yyyy HHmm").parse(by);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
