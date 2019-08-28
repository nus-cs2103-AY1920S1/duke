public class Parser {
    String writeTaskAsText(Task task) {
        int isDoneStatus = task.isDone ? 1 : 0;
        
        if (task instanceof ToDo) {
            return String.format("T : %d : %s", isDoneStatus, task.description);
        } else if (task instanceof Deadline) {
            return String.format("D : %d : %s : %s", isDoneStatus, task.description,
                    ((Deadline)task).date + " " + ((Deadline)task).time);
        } else if (task instanceof Event) {
            return String.format("E : %d : %s : %s", isDoneStatus, task.description,
                    ((Event)task).date + " " + ((Event)task).time);
        }

        return null;
    }

    Task readTextAsTask(String stringTask) {
        String[] taskContents = stringTask.split(" : ");

        String taskTag = taskContents[0];
        boolean isDone = taskContents[1].equals("0") ? false : true;
        String taskDetails = taskContents[2];
        String dateTime;

        Task outputTask;

        switch (taskTag) {
        case "T":
            ToDo existingTodo = new ToDo(taskDetails, isDone);
            outputTask = existingTodo;
            break;
        case "D":
            dateTime = taskContents[3];
            Deadline existingDeadline = new Deadline(taskDetails, dateTime, isDone);

            outputTask = existingDeadline;            
            break;
        case "E":
            dateTime = taskContents[3];
            Event existingEvent = new Event(taskDetails, dateTime, isDone);

            outputTask = existingEvent;            
            break;
        default:
            outputTask = null;
            break;
        }

        return outputTask;
    }
    
}