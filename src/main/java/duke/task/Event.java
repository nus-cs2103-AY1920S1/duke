package duke.task;

import duke.calendar.Date;
import duke.calendar.Time;

public class Event extends Task {

    protected Date startDate;
    protected Time startTime;
    protected Date endDate;
    protected Time endTime;

    public Event(String description, Date startDate, Time startTime, Date endDate, Time endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    public String getUnprocessedStartDate() {
        return startDate.getUnprocessedDate();
    }

    public String getUnprocessedStartTime() {
        return startTime.getUnprocessedTime();
    }

    public String getUnprocessedEndDate() {
        return endDate.getUnprocessedDate();
    }

    public String getUnprocessedEndTime() {
        return endTime.getUnprocessedTime();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("[E]" + super.toString() + " (at: " + startDate.toString() + (startTime.toString().equals("") ? "" : ", " + startTime.toString()));
        if (endDate.toString().equals("") && endTime.toString().equals("")) {
            output.append(")");
        } else {
            if (endDate.toString().equals("")) {
                output.append(" to " + endTime.toString() + ")");
            } else if (endTime.toString().equals("")) {
                output.append(" to " + endDate.toString() + ")");
            } else {
                output.append(" to " + endDate.toString() + " " + endTime.toString());
            }
        }
        return output.toString();
    }

    public String getType() {
        return "E";
    }
}
