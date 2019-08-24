public class DeadlineTask extends Task{

    public String deadline;

    public DeadlineTask(String todo, String deadline) {
        super(todo);
        this.deadline = deadline;
    }

    public DeadlineTask(String todo, boolean isCompleted, String deadline) {
        super(todo, isCompleted);
        this.deadline = deadline;
    }

    public String toString() {
        if (isCompleted) {
            return  String.format("[D][Y] %s (by: %s)", this.todo, this.deadline);
        } else {
            return  String.format("[D][N] %s (by: %s)", this.todo, this.deadline);
        }
    }
}
