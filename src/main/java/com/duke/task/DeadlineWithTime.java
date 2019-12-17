package com.duke.task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DeadlineWithTime extends Deadline {

    private LocalTime time;
    private static final DateTimeFormatter FORMAT_USER_INPUT_TIME = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter FORMAT_FILE_TIME_STRING = DateTimeFormatter.ofPattern("HH:mm");

    DeadlineWithTime(String description, String date, String time) {
        super(description, date);
        this.time = LocalTime.parse(time, FORMAT_USER_INPUT_TIME);
    }

    DeadlineWithTime(String description, String date, String time, boolean isFromFile) {
        super(description, date, isFromFile);
        if (isFromFile) {
            this.time = LocalTime.parse(time, FORMAT_FILE_TIME_STRING);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.time.format(FORMAT_FILE_TIME_STRING);
    }
}
