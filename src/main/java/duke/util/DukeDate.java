package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DukeDate {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM YYYY");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d 'of' MMMM YYYY h:mma");

    private LocalDateTime localDateTime;
    private boolean hasTime;
    private String displayString;

    public DukeDate(LocalDateTime localDateTime, boolean hasTime) {
        this.localDateTime = localDateTime;
        this.hasTime = hasTime;

        if(hasTime) {
            displayString = localDateTime.format(DATE_TIME_FORMATTER);
        } else {
            displayString = localDateTime.format(DATE_FORMATTER);
        }
    }

    @Override
    public String toString() {
        return displayString;
    }
}
