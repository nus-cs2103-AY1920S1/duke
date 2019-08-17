public class DeadlineTask extends Task{

    public String deadline;

    public DeadlineTask(String todo, String deadline) {
        super(todo);
        this.deadline = deadline;
    }

    public String toString() {
        if (completed) {
            return  String.format("[D}[✓] %s (by: %s)", this.todo, this.deadline);
        } else {
            return  String.format("[D}[✗] %s (by: %s)", this.todo, this.deadline);
        }
    }
}
