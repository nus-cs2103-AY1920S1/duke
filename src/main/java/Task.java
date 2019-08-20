import java.io.IOException;

public interface Task {
    static final char UNICODE_TICK = (char) 0x2713;
    static final char UNICODE_CROSS = (char) 0x2718;
    static final String DEMARCATOR_STRING = ":split:";

    //Returns the character for a Tick for true and a Cross for false
    static char getStatusIcon(boolean isDone) {
        return isDone ? UNICODE_TICK : UNICODE_CROSS;
    }

    //Returns a copy of this task but with its completion status marked as done
    public Task getTaskMarkedAsDone();
    
    //Returns a copy of this task but with its completion status marked as undone
    public Task getTaskMarkedUndone();

    //Converts the task into a computer-readable string
    //Strings should take the form <Task-Type> | <Description> | <Completion Status> | <Additional Timing Data>
    public String toSaveFileString();

    //Converts a computer-readable string into a Task, and returns that Task
    public static Task createTaskFromSaveFileString(String saveFileString) throws IOException {
        String [] splitString = saveFileString.split(DEMARCATOR_STRING);
        
        switch(splitString[0]) {
            case "T":
                return new ToDoTask(splitString[1], Integer.parseInt(splitString[2]) == 1);
                //Fallthrough
            case "E": 
                return new EventTask(splitString[1], splitString[3], Integer.parseInt(splitString[2]) == 1);
                //Fallthrough
            case "D": 
                return new DeadlineTask(splitString[1], splitString[3], Integer.parseInt(splitString[2]) == 1);
                //Fallthrough
            default:
                throw new IOException("Unreadable Task Type " + splitString[0] + " in " + saveFileString);
        }
    }
}
