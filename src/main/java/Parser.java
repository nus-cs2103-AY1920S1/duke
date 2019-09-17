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
        try {
            if (raw[0].equals("todo") || raw[0].equals("deadline") || raw[0].equals("event")) {
                return taskCommand(raw); // {todo/deadline/event, desc, dateline if applicable}
            } else {
                String[] task = new String[3];
                task[0] = raw[0];
                String action = task[0];
                if (action.equals("list") || action.equals("bye") || action.equals("help")) {
                    if (raw.length > 1) {
                        throw new DukeActionException("\u2639 OOPS!!! I'm sorry, but " + raw[0] + " should not have" +
                                " more than 1 argument :-(");
                    }
                    task[1] = "";
                    task[2] = ""; // {list/bye, "", ""}
                } else if (action.equals("done") || action.equals("delete") || action.equals("find")) {
                    if (raw.length < 2) {
                        throw new DukeActionException("\u2639 OOPS!!! I'm sorry, but " + raw[0] + " should not have" +
                                " less than 2 arguments :-(");
                    } else if (raw.length > 2) {
                        throw new DukeActionException("\u2639 OOPS!!! I'm sorry, but " + raw[0] + " should not have" +
                                " more than 2 arguments :-(");
                    } else if (Integer.parseInt(raw[1]) <= 0) {
                        throw new DukeActionException("\u2639 OOPS!!! I'm sorry, but " + raw[0] + " should not have" +
                                " a value 0 or less :-(");
                    }
                    task[1] = raw[1];
                    task[2] = ""; // {done/delete/find, number for done or delete, word for find, ""}
                } else {
                    task[0] = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\nEnter \"help\" for "
                            + "commands in duke.";
                    task[1] = "";
                    task[2] = "";
                }
                return task;
            }
        } catch (DukeException de) {
            String[] exception = new String[3];
            exception[0] = de.getMessage();
            exception[1] = "";
            exception[2] = "";
            return exception;
        }
    }

    private static String[] taskCommand(String[] raw) throws DukeException {
        String[] task = new String[3];
        task[0] = raw[0];
        if (raw.length == 1) {
            throw new DukeEmptyException(raw[0]);
        } else {
            raw[0] = "";
            if (raw[1].contains("/by") || raw[1].contains("/at")) {
                throw new DukeEmptyException(task[0]);
            }
            task[1] = String.join(" ", raw).substring(1); //{command, desc+date, ""}
            if (task[0].equals("deadline")) {
                return deadlineCommand(task);
            } else if (task[0].equals("event")) {
                return eventCommand(task);
            } else {
                task[2] = "";
                return task;
            }
        }
    }

    private static String[] deadlineCommand(String[] task) throws DukeDeadlineException {
        String[] desc_By = task[1].split("/by");
        if (desc_By.length == 1) {
            throw new DukeDeadlineException("\u2639 OOPS!!! I'm sorry, but the date of a deadline must be in the format"
                    + " \"dd/MM/yyyy HHmm\"");
        }
        boolean hasDateFormat = (desc_By[1].split("/").length == 3); // {"18", "12", "2019 1800"}
        boolean hasWrongTimeFormat = (desc_By[1].split(" ")[1].length() > 4); // {"18/12/2019", "18003dse3"}
        boolean hasExtraTimeFormat = (desc_By[1].split("-").length == 1); // {"18/12/2019 1800"}
        if (!hasDateFormat || !hasExtraTimeFormat || hasWrongTimeFormat) {
            throw new DukeDeadlineException("\u2639 OOPS!!! I'm sorry, but the date of a deadline must be in the format"
                    + " \"dd/MM/yyyy HHmm\"");
        }
        task[1] = desc_By[0].trim();
        task[2] = desc_By[1].trim();
        return task;
    }

    private static String[] eventCommand(String[] task) throws DukeEventException {
        String[] desc_At = task[1].split("/at");
        if (desc_At.length == 1) {
            throw new DukeEventException("\u2639 OOPS!!! I'm sorry, but the date of a event must be in the format"
                    + " \"dd/MM/yyyy HHmm-HHmm\"");
        }
        boolean hasDateFormat = (desc_At[1].split("/").length == 3); // {"18", "12", "2019 1800"}
        boolean hasWrongTimeFormat = (desc_At[1].split(" ")[1].length() > 9); // {"18/12/2019", "1800-20003dse3"}
        boolean hasExtraTimeFormat = (desc_At[1].split("-").length == 2); // {"18/12/2019 1800", "1800-2000"}
        if (!hasDateFormat || !hasExtraTimeFormat || hasWrongTimeFormat) {
            throw new DukeEventException("\u2639 OOPS!!! I'm sorry, but the date of a event must be in the format"
                    + " \"dd/MM/yyyy HHmm-HHmm\"");
        }
        task[1] = desc_At[0].trim();
        task[2] = desc_At[1].trim();
        return task;
    }
}
