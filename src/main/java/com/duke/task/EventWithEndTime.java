package com.duke.task;

import com.duke.exception.DukeIllegalArgumentException;

import java.time.LocalTime;

public class EventWithEndTime extends EventWithStartTime {

    private LocalTime endTime;

    EventWithEndTime(String description, String date, String startTime, String endTime) {
        super(description, date, startTime);
        if (startTime.compareTo(endTime) > 0) {
            throw new DukeIllegalArgumentException("Invalid input for ending time. Ending time should be later than or"
                    + " equal to starting time");
        }
        this.endTime = LocalTime.parse(endTime, FORMAT_USER_INPUT_TIME);
    }

    EventWithEndTime(String description, String date, String startTime, String endTime, boolean isFromFile) {
        super(description, date, startTime, isFromFile);
        if (startTime.compareTo(endTime) > 0) {
            throw new DukeIllegalArgumentException("Invalid input for ending time. Ending time should be later than or"
                    + "equal to starting time");
        }
        if (isFromFile) {
            this.endTime = LocalTime.parse(endTime, FORMAT_FILE_TIME_STRING);
        } else {
            throw new DukeIllegalArgumentException("The input string is not from file");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " to " + this.endTime.format(FORMAT_FILE_TIME_STRING);
    }
}
