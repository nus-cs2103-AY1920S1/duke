package duke.task;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface UsingDateTime {
    DateTimeFormatter readFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
    DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yy HH:mm", Locale.ENGLISH);
}
