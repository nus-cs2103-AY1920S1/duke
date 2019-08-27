public class Task {
    String description;
    Boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void doTask() throws DukeException{
        if (this.status) {
            throw new DukeException("The task specified has already been done.");
        } else {
            this.status = true;
            this.printDone();
        }
    }

    private void printDone() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done: ");
        System.out.printf("\t   %s\n", this.toString());
        System.out.println("\t____________________________________________________________");
    }

    public String getStatusIcon() {
        return status ? "[\u2713]" : "[\u2718]";
    }

    @Override
    public String toString() {
       return String.format("%s %s", this.getStatusIcon(), this.description);
    }
}
