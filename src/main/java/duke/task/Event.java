package duke.task;

import duke.exception.DukeException;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Encapsulates a task object of type Event.
 */
public class Event extends Task {
    private String date;
    private String[] tentativeDates;

    /**
     * Creates an Event object.
     *
     * @param topic the topic of the event.
     * @param date the date of the event.
     */
    public Event(String topic, String date) {
        super(topic);
        this.type = "E";
        this.date = date;
    }

    /**
     * Creates an Event object consists of tentative dates.
     *
     * @param topic the topic of the event.
     * @param tentativeDates the tentative dates of the event,
     */
    public Event(String topic, String[] tentativeDates) {
        super(topic);
        this.type = "E";
        this.tentativeDates = tentativeDates;
        this.date = IntStream.range(0, tentativeDates.length)
                .mapToObj(i -> tentativeDates[i])
                .collect(Collectors.joining("; "));
    }

    /**
     * Sets the confirmed date of the event.
     *
     * @param i index of the confirmed date among the tentative dates (index starts from 1).
     * @return a new Event object with the same title and confirmed date.
     * @throws DukeException if the Event object does not have tentative dates or the index is out of bound
     *     (i < 0 || i > tentativeDates.length - 1).
     */
    public Event setDate(int i) throws DukeException {
        if (tentativeDates == null) {
            throw new DukeException("The event does not have tentative dates!");
        }

        boolean indexOutOfBound = i < 0 || i > tentativeDates.length - 1;
        if (indexOutOfBound) {
            throw new DukeException("Index given for tentative dates is invalid!");
        }

        String confirmedDate = tentativeDates[i];
        Event newEvent = new Event(title, confirmedDate);

        if (this.isDone) {
            newEvent.markAsDone();
        }

        return newEvent;
    }

    /**
     * Returns the string representation of the event.
     * It takes the form of [type][done status][title][date].
     *
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", type, status, title, date);
    }

    /**
     * Returns the data summary of this event to record this task in the database.
     *
     * @return the data summary of this event.
     */
    public String getSummaryForDatabase() {
        int status = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", type, status, title, date);
    }
}
