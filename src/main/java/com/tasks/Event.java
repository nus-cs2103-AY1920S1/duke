package com.tasks;

public class Event extends Task {

    public Event(String description, String at) {
        super(description, at);
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