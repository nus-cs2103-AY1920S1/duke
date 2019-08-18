public interface Task {
    static final char UNICODE_TICK = (char) 0x2713;
    static final char UNICODE_CROSS = (char) 0x2718;
    
    //Returns the character for a Tick for true and a Cross for false
    static char getStatusIcon(boolean isDone) {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }

    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone();
    
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone();
}
