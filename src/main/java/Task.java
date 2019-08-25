public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task() {

    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[\u2713] " + this.description;
        } else {
            return "[\u2718] " + this.description;
        }
    }

    public String createTaskInFileFormat() {
        String temp = "";
        if (this.isDone) {
            temp += "1 ";
        } else {
            temp += "0 ";
        }
        temp += this.description;
        return temp;
    }
}
