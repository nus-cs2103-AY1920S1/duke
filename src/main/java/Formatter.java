public class Formatter {

    public static String formatTaskForWriting(Task task) {
        StringBuilder sb = new StringBuilder();
        String className = task.getClass().getSimpleName();
        sb.append(className);
        sb.append("|");
        if (task.done) {
            sb.append("1");
        } else {
            sb.append("0");
        }
        sb.append("|");
        sb.append(task.content);
        if (className.equals("ToDo")) {

        } else {
            sb.append("|");
            if (task instanceof Deadline) {
                sb.append(task.getTime());
            } else {
                sb.append(task.getTime());
            }
        }
        return sb.toString();
    }
}
