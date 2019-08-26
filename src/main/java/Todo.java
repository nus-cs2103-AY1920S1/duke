import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Todo extends Task{

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public Date getDate() {
        return null;
    }
}
