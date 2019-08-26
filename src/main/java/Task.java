public class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String saveFormat() {
        return (isDone ? "1" : "0") + " " + description;
    }

}

class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }
    @Override
    public String getStatus() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String saveFormat() {
        return "T" + (isDone ? "1" : "0") + " " + description;
    }
}

class Deadline extends Task {
    private String timeDue;
    private String preposition;

    Deadline(String description, String preposition, String timeDue) {
        super(description);
        this.timeDue = timeDue;
        this.preposition = preposition;
    }
    @Override
    public String getStatus() {
        return "[D][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + timeDue + ")";
    }
    @Override
    public String saveFormat() {
        return "D" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + timeDue;
    }
}

class Event extends Task {
    private String startEndTime;
    private String preposition;
    Event(String description, String preposition, String startEndTime) {
        super(description);
        this.startEndTime = startEndTime;
        this.preposition = preposition;
    }
    @Override
    public String getStatus() {
        return "[E][" + getStatusIcon() + "] " + description + " (" + preposition + ": " + startEndTime + ")";
    }
    @Override
    public String saveFormat() {
        return "E" + (isDone ? "1" : "0") + " " + description + " /" + preposition + " " + startEndTime;
    }
}

