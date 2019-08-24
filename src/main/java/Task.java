public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) throws DukeException{
        this.description = description.trim();
        if(this.description.isBlank())
            throw new EmptyFieldDukeException("description", this.childClass());
        this.isDone = false;
    }

    protected char getStatusIcon() {
        return this.isDone ? '\u2713' : '\u2718'; //return tick or X symbols
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +
                this.description;
    }

    protected String toFileString(){
        return (char) 31 + (this.isDone ? "1" : "0") + (char) 31 +
                this.description;
    };

    abstract protected String childClass();

    public static Task parseFileTask(String str) throws DukeException{
        String[] prop = str.split("\\x1f");
        Task t = null;
        switch (prop[0]){
            case "D":
                t = new Deadline(prop[2], prop[3]);
                break;
            case "E":
                t = new Event(prop[2], prop[3]);
                break;
            case "T":
                t = new Todo(prop[2]);
                break;
        }
        if(t != null && prop[1].equals("1"))
            t.setDone();
        return t;
    }

    public static Task parseTask(String str) throws DukeException{
        if (str.startsWith("deadline")) {
            String[] temp = str.substring(8).split(" /by ");
            if (temp.length < 1 || temp[0].isBlank())
                throw new EmptyFieldDukeException("description", "deadline");
            if (temp.length < 2 || temp[1].isBlank())
                throw new EmptyFieldDukeException("time", "deadline");
            return new Deadline(temp[0], temp[1]);
        }
        if (str.startsWith("event")) {
            String[] temp = str.substring(5).split(" /at ");
            if (temp.length < 1 || temp[0].isBlank())
                throw new EmptyFieldDukeException("description", "event");
            if (temp.length < 2 || temp[1].isBlank())
                throw new EmptyFieldDukeException("time", "event");
            return new Event(temp[0], temp[1]);
        }
        if (str.startsWith("todo")) {
            return new Todo(str.substring(4));
        }
        return null;
    }
}

class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) throws DukeException{
        super(description);
        this.by = by.trim();
        if(this.by.isBlank())
            throw new EmptyFieldDukeException("time", this.childClass());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")\n";
    }

    protected String childClass(){
        return "deadline";
    }

    public String toFileString(){
        return "D" + super.toFileString() + (char) 31 + this.by;
    }
}

class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException{
        super(description);
        this.at = at.trim();
        if(this.at.isBlank())
            throw new EmptyFieldDukeException("time", this.childClass());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")\n";
    }

    protected String childClass(){
        return "event";
    }

    @Override
    public String toFileString(){
        return "E" + super.toFileString() + (char) 31 + this.at;
    }
}

class Todo extends Task {

    public Todo(String description) throws DukeException{
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + '\n';
    }

    protected String childClass(){
        return "todo";
    }

    @Override
    public String toFileString(){
        return "T" + super.toFileString();
    }
}