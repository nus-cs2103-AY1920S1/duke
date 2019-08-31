/**
 * Represents a Parser which makes sense of user-input.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Parser {

    /**
     * Processes the user-input's statement for TaskList to make-sense of.
     * This method throws DukeExceptions because of error in input.
     * Exceptions caught would return an error message, otherwise a formatted
     * and processed user-input string would be returned.
     *
     * @param raw User-input from TaskList.
     * @return A formatted and processed user-input string for TaskList to use.
     */
    public static String[] processTask(String[] raw) {
        String[] task = new String[3];// [todo/deadline/event], [desc], [dateline if applicable];
        task[0] = raw[0];
        try {
            switch (task[0]) {
            case "todo":
                if (raw.length == 1) {
                    throw new DukeEmptyException(task[0]);
                } else {
                    raw[0] = "";
                    task[1] = String.join(" ", raw);
                    task[1] = task[1].substring(1);
                    task[2] = "";
                    return task;
                }
            case "deadline":
                if (raw.length == 1) {
                    throw new DukeEmptyException(raw[0]);
                } else {
                    raw[0] = "";
                    String d2 = String.join(" ", raw);
                    String[] desc_By = d2.split("/by");
                    if (desc_By.length == 1) {
                        throw new DukeDeadlineException("The date of a deadline must be in the format"
                                                        + " \"dd/MM/yyyy HHmm\"");
                    }
                    String[] dDate = desc_By[1].split("/");
                    String[] dTime = desc_By[1].split("-");
                    if (dDate.length != 3 && dTime.length != 0) {
                        throw new DukeDeadlineException("The date of a deadline must be in the format"
                                                        + " \"dd/MM/yyyy HHmm\"");
                    }
                    task[1] = desc_By[0];
                    task[1] = task[1].substring(1).trim();
                    task[2] = desc_By[1];
                    task[2] = task[2].substring(1);
                    return task;
                }
            case "event":
                if (raw.length == 1) {
                    throw new DukeEmptyException(raw[0]);
                } else {
                    raw[0] = "";
                    String e2 = String.join(" ", raw);
                    String[] desc_At = e2.split("/at");
                    if (desc_At.length == 1) {
                        throw new DukeDeadlineException("The date of a event must be in the format"
                                                        + " \"dd/MM/yyyy HHmm-HHmm\"");
                    }
                    String[] eDate = desc_At[1].split("/");
                    String[] eTime = desc_At[1].split("-");
                    if (eDate.length != 3 && eTime.length != 2) {
                        throw new DukeDeadlineException("The date of a event must be in the format"
                                                        + " \"dd/MM/yyyy HHmm-HHmm\"");
                    }
                    task[1] = desc_At[0];
                    task[1] = task[1].substring(1).trim();
                    task[2] = desc_At[1];
                    task[2] = task[2].substring(1);
                    return task;
                }
            case "list":
                return task;
            case "done":
                task[1] = raw[1];
                return task;
            case "delete":
                task[1] = raw[1];
                return task;
            case "find":
                task[1] = raw[1];
                return task;
            case "bye":
                task[0] = "bye";
                task[1] = "";
                task[2] = "";
                return task;
            default:
                task[0] = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
                task[1] = "";
                task[2] = "";
                return task;
            }
        } catch (DukeException de) {
            task[0] = de.getMessage();
            task[1] = "";
            task[2] = "";
            return task;
        }
    }
}
