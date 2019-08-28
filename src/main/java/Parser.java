public class Parser {
    String writeTaskAsText(Task task) {
        int isDoneStatus = task.isDone ? 1 : 0;
        
        if (task instanceof ToDo) {
            return String.format("T | %d | %s", isDoneStatus, task.description);
        } else if (task instanceof Deadline) {
            return String.format("D | %d | %s | %s", isDoneStatus, task.description,
                    ((Deadline)task).date + " " + ((Deadline)task).time);
        } else if (task instanceof Event) {
            return String.format("E | %d | %s | %s", isDoneStatus, task.description,
                    ((Event)task).date + " " + ((Event)task).time);
        }

        return null;
    }
}