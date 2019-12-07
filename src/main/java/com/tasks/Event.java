package com.tasks;

import java.text.ParseException;

public class Event extends Task {

    public Event(String description, String at) {
        super(description, at);
        // If event /at is of correct format
        try {
            if (isValidDateTimeFormat(at)) {
                this.subDescription = getNewDateTimeFormat(at);
            }
        } catch (ParseException e) {
            this.subDescription = at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + subDescription + ")";
    }

    public String getTaskType() {
        return "E";
    }

    public boolean containsKeyword(String keyword) {
        String lcKeyword = keyword.toLowerCase();
        String lcDescription = description.toLowerCase();
        String lcSubDescription = subDescription.toLowerCase();
        return lcDescription.contains(lcKeyword) || lcSubDescription.contains(lcKeyword);
    }

}