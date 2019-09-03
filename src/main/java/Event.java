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
        return description.contains(keyword) || subDescription.contains(keyword);
    }

}