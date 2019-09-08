package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A Task that has a additional info component (that is usually
 * a venue).
 */
public class Event extends Task {

    /**
     * The additional info of an Event task. It is the content that is
     * passed in after the "/at" keyword when interacting with the UI
     * with "event" action.
     */
    private String additionalInfo;

    Event(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[E]";
    }

    @Override
    protected String getAdditionalInfoForDisplay() {
        return String.format("(at: %s)", additionalInfo);
    }

    @Override
    public String getStorageStringFormat() {
        return "E" + " | "
                + getStatus() + " | "
                + getName() + " | "
                + getAdditionalInfo();
    }
}
