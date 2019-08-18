public interface Task {
    static final char tickUnicode = (char) 0x2713;
    static final char crossUnicode = (char) 0x2718;
    
    static char getStatusIcon(boolean isDone) {
        return isDone ? tickUnicode : crossUnicode;
    }

    public Task getTaskMarkedAsDone();
    public Task getTaskMarkedUndone();
}
