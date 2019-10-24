package tasks;

import java.time.LocalDateTime;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        super.symbol = "T";
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String getExtraInfo() {
        return "";
    }

    @Override
    public void postpone(int daysToPostpone, int hoursToPostpone, int minutesToPostpone) {

    }

    @Override
    public String toString() {
        assert super.toString() != null;
        return "[T]" + super.toString() + super.getNotes();
    }
}