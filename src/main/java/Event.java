import exceptions.DateParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    public static final String REGEX = "/at";

    private Date at;

    Event(String desc, String at) throws DateParseException {
        super(desc.trim());
        try {
            this.at = new SimpleDateFormat(Task.INPUT_DATE_FORMAT).parse(at);
        } catch (ParseException e) {
            throw new DateParseException();
        }
    }
    @Override
    String getInitial() {
        return "E";
    }

    @Override
    String getAdditionalMessage() {
        return "(at: " + new SimpleDateFormat(Task.OUTPUT_DATE_FORMAT).format(at) + ")";
    }
}
