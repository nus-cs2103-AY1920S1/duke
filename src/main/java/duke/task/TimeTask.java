package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

abstract class TimeTask extends Task {
    static final String DATE_FORMAT_HINT = "Please enter the date in the format d/M/yyyy HHmm e.g. 19/9/2019 1430";
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HHmm");
    private LocalDateTime time;

    TimeTask(String description, String timeString) {
        super(description);
        DateTimeFormatter parseFormatter = new DateTimeFormatterBuilder()
                .appendOptional(saveFormatter)
                .appendOptional(displayFormatter)
                .toFormatter();
        time = LocalDateTime.parse(timeString, parseFormatter);
    }

    /**
     * Returns the time attached to this task as a string for saving.
     *
     * @return Time as a string.
     */
    String getSaveTimeString() {
        return saveFormatter.format(time);
    }

    /**
     * Returns the time attached to this task as a string to display to the user.
     *
     * @return Time as a string.
     */
    String getTimeString() {
        return displayFormatter.format(time);
    }

    /**
     * Returns a list of strings representing this task so that it can be saved.
     *
     * @return A representation of this task as a list for saving.
     */
    @Override
    public List<String> getSaveList() {
        List<String> list = new ArrayList<>(super.getSaveList());
        list.add(getSaveTimeString());
        return list;
    }
}
