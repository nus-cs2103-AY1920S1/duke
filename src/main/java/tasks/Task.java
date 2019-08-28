package tasks;

/*
A task class to represent a task object.
Properties: id, name, done
*/
public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String printForStorage() {
        return "";
    }

    @Override
    public String toString() {
        String str = "";
        if (this.isDone) {
            str += "[✓]";
        } else {
            str += "[✗]";
        } 
        return str += " " + this.name;
    }

}