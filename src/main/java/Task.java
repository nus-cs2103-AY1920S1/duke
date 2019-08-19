class Task {
    String taskName;
    boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String toString() {
        String task;
        if (isDone) {
            return 	"[+] " + taskName; //Task is Done
        } else {
            return 	"[-] " + taskName; //Task not Done
        }
    }
}