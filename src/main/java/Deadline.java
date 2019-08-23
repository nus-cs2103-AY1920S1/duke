import exceptions.DateParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    public static final String REGEX = "/by";

    private Date by;

    Deadline(String desc, String by) throws DateParseException {
        super(desc.trim());
        try {
            this.by = new SimpleDateFormat(Task.INPUT_DATE_FORMAT).parse(by);
        } catch (ParseException e) {
            throw new DateParseException();
        }
    }
    @Override
    String getInitial() {
        return "D";
    }

    @Override
    String getAdditionalMessage() {
        return "(by: " + new SimpleDateFormat(Task.OUTPUT_DATE_FORMAT).format(by) + ")";
    }
}
