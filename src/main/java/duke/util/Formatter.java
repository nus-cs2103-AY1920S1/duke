package duke.util;

import duke.task.Task;

public class Formatter {

    public static String formatTaskForWriting(Task task) {
        StringBuilder sb = new StringBuilder();
        String className = task.getClass().getSimpleName();
        sb.append(className);
        sb.append("|");
        if (task.isDone()) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("|");
        sb.append(task.getContent());
        if (className.equals("ToDo")) {

        } else {
            sb.append("|");
            sb.append(task.getTime());
        }
        return sb.toString();
    }
}
