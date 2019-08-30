import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    protected String input;
    protected Date date;

    public DateTime (String input){
        this.input = input;
    }

    public void setDateAndTime() throws DukeException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeException("OOPS!!! Invalid time and date format. Please enter in dd/MM/yyyy HHmm format");
        }
    }

    public Date getDateAndTime() {
        return this.date;
    }

    @Override
    public String toString() {
        return this.input;
    }
}
