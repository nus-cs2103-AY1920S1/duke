/* 
A task class to represent a task object.
Properties: id, name, done
*/
public class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String str = "";
        if (this.done) {
            str += "[✓]";
        } else {
            str += "[✗]";
        } 
        return str += " " + this.name;
    }

}