package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A Task that has a deadline in the format DD/MM/YYYY HHmm
 * as its additional info.
 */
public class Deadline extends Task {

    private String additionalInfo;

    Deadline(String name, String additionalInfo) {
        super(name);
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    @Override
    protected String getTypeSymbol() {
        return "[D]";
    }

    @Override
    protected String getAdditionalInfoForDisplay() {
        return String.format("(by: %s)", additionalInfo);
    }

    @Override
    public String getStorageStringFormat() {
        return "D" + " | "
                + getStatus() + " | "
                + getName() + " | "
                + getAdditionalInfo();
    }
}
