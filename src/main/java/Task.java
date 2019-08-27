public abstract class Task {
    protected String description;
    protected Boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void doTask() throws DukeException{
        if (this.done) {
            throw new DukeException("The task specified has already been done.");
        } else {
            this.done = true;
            this.printDone();
        }
    }

    public void markDone() {
        this.done = true;
    }

    private void printDone() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.printf("\t   %s\n", this.toString());
        System.out.println("\t____________________________________________________________");
    }

    public String getStatusIcon() {
        return done ? "[\u2713]" : "[\u2718]";
    }

    protected abstract String formatToWrite();

    @Override
    public String toString() {
       return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}
