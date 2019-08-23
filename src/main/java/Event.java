import exceptions.DateParseException;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {
    public static final String REGEX = "/at";
    public static final String INITIAL = "E";

    private Date at;

    Event(String desc, String at) throws DateParseException {
        super(desc.trim());
        try {
            this.at = INPUT_DATE_FORMAT.parse(at);
        } catch (ParseException e) {
            throw new DateParseException();
        }
    }
    @Override
    String getInitial() {
        return INITIAL;
    }

    @Override
    Date getDate() {
        return at;
    }

    @Override
    String getPrefix() {
        return "at: ";
    }
}
