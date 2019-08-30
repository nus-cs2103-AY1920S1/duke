import java.io.PrintStream;
import java.text.ParseException;

public class Parser {

    public static String[] processTask(String[] s) {
        String[] task = new String[3];// [todo/deadline/event], [desc], [dateline if applicable];
        task[0] = s[0];
        try {
            switch (task[0]) {
            case "todo":
                if (s.length == 1) {
                    throw new DukeEmptyException(task[0]);
                } else {
                    s[0] = "";
                    task[1] = String.join(" ", s);
                    task[1] = task[1].substring(1);
                    task[2] = "";
                    return task;
                }
            case "deadline":
                if (s.length == 1) {
                    throw new DukeEmptyException(s[0]);
                } else {
                    s[0] = "";
                    String d2 = String.join(" ", s);
                    String[] d3 = d2.split("/by");
                    if (d3.length == 1) {
                        throw new DukeDeadlineException("The date of a deadline must be in the format"
                                                        + " \"dd/MM/yyyy HHmm\"");
                    }
                    String[] d4 = d3[1].split("/");
                    String[] d5 = d3[1].split("-");
                    if (d4.length != 3 && d5.length != 0) {
                        throw new DukeDeadlineException("The date of a deadline must be in the format"
                                                        + " \"dd/MM/yyyy HHmm\"");
                    }
                    task[1] = d3[0];
                    task[1] = task[1].substring(1).trim();
                    task[2] = d3[1];
                    task[2] = task[2].substring(1);
                    return task;
                }
            case "event":
                if (s.length == 1) {
                    throw new DukeEmptyException(s[0]);
                } else {
                    s[0] = "";
                    String e2 = String.join(" ", s);
                    String[] e3 = e2.split("/at");
                    if (e3.length == 1) {
                        throw new DukeDeadlineException("The date of a event must be in the format"
                                                        + " \"dd/MM/yyyy HHmm-HHmm\"");
                    }
                    String[] e4 = e3[1].split("/");
                    String[] e5 = e3[1].split("-");
                    if (e4.length != 3 && e5.length != 2) {
                        throw new DukeDeadlineException("The date of a event must be in the format"
                                                        + " \"dd/MM/yyyy HHmm-HHmm\"");
                    }
                    task[1] = e3[0];
                    task[1] = task[1].substring(1).trim();
                    task[2] = e3[1];
                    task[2] = task[2].substring(1);
                    return task;
                }
            case "list":
                return task;
            case "done":
                task[1] = s[1];
                return task;
            case "delete":
                task[1] = s[1];
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
