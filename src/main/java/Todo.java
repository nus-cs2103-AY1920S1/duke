import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileFormat() {
        if (isDone) {
            String format = "T | [✓] | " + taskDesc + "\n";
            return format;
        } else {
            String format = "T | [✗] | " + taskDesc + "\n";
            return format;
        }
    }

    public Date getDate() {
        return null;
    }
}
