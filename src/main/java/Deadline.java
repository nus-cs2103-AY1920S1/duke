import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), new SimpleDateFormat("dd/MM/yyyy HHmm").format(this.by));
    }
}
