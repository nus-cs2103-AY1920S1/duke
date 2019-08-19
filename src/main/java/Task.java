public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String task;
        if (isDone) {
            return 	"[+] " + description; //Task is Done
        } else {
            return 	"[-] " + description; //Task not Done
        }
    }
}