import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Duke { // handles all input and output
    final static String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    final static String line = "____________________________________________________________";

    final static ArrayList<Task> _task = new ArrayList<Task>();

    public static String intro() {
        String intro = String.format("%s%n%s%n Hello! I'm Duke%n What can I do for you?%n%s%n",
                        line, logo, line);
        return intro;
    }

    public static String list() {
        StringBuilder s = new StringBuilder(line);
        s.append(System.getProperty("line.separator"));
        s.append(" Here are the tasks in your list:");
        for (int i = 1; i <= _task.size(); i++) {
            s.append(System.getProperty("line.separator"));
            s.append(" ").append(i).append(".");
            Task t = _task.get(i - 1);
            s.append(t.toString());
        }
        s.append(System.getProperty("line.separator"));
        s.append(line);
        s.append(System.getProperty("line.separator"));
        return s.toString();
    }

    public static String bye() {
        String bye = String.format("%s%n Bye! Hope to see you again soon!%n%s%n",
                        line, line);
        return bye;
    }

    public static String newTask(Task t) {
        _task.add(t);
        String added = String.format("%s%n Got it! I've added this task:" +
                        "%n   %s%n Now you have %d task in the list.%n%s%n",
                        line, t.toString(), _task.size(), line);
        return added;
    }

    public static String done(int n) {
        Task t = _task.get(n - 1);
        t.markAsDone();
        String done = String.format("%s%n Nice! I've marked this task as done:%n [%s] %s%n",
                        line, t.getStatusIcon(), t.getDesc(), line);
        return done;
    }

    public static String delete(int n) {
        Task t = _task.get(n - 1);
        _task.remove(n - 1);
        String del = String.format("%s%n Noted. I've removed this task:%n   %s%n" +
                        "Now you have %d tasks in the list.%n%s%n",
                        line, t.toString(), _task.size(), line);
        return del;
    }

    public static void main(String[] args) { // handles all input and output
        Scanner sc = new Scanner(System.in);
        System.out.println(intro());

        while (sc.hasNext()) {
            String cmdWord = sc.next(); // extract first only
            String cmdLine = sc.nextLine(); // extract the rest of the line

            try {
                switch (cmdWord) {
                case "list":
                    System.out.println(list());
                    break;
                case "bye":
                    System.out.println(bye());
                    break;
                case "done":
                    int taskNo = Integer.parseInt(cmdLine.substring(1));
                    System.out.println(done(taskNo));
                    break;
                case "delete":
                    int taskNum = Integer.parseInt(cmdLine.substring(1));
                    System.out.println(delete(taskNum));
                    break;
                case "todo":
                    if (cmdLine.isEmpty()) {
                        throw new ToDoException(cmdLine);
                    } else {
                        ToDo td = new ToDo(cmdLine);
                        System.out.println(newTask(td));
                    }
                    break;
                case "deadline":
                    if (cmdLine.isEmpty()) {
                        throw new DeadlineException(cmdLine, 3);
                    } else if (!cmdLine.contains("/by")) {
                        throw new DeadlineException(cmdLine, 1);
                    } else {
                        String[] cmdSplit = cmdLine.split("/by");
                        if (cmdSplit.length <= 1) {
                            throw new DeadlineException(cmdLine, 3);
                        } else if (cmdSplit[0].equals(" ")) {
                            throw new DeadlineException(cmdLine, 2);
                        } else {
                            Deadline dl = new Deadline(cmdSplit[0], cmdSplit[1]);
                            System.out.println(newTask(dl));
                        }
                    }
                    break;
                case "event":
                    if (cmdLine.isEmpty()) {
                        throw new EventException(cmdLine, 3);
                    } else if (!cmdLine.contains("/at")) {
                        throw new EventException(cmdLine, 1);
                    } else {
                        String[] cmdSplitt = cmdLine.split("/at");
                        if (cmdSplitt.length <= 1) {
                            throw new EventException(cmdLine, 3);
                        } else if (cmdSplitt[0].equals(" ")) {
                            throw new EventException(cmdLine, 2);
                        } else {
                            Event e = new Event(cmdSplitt[0], cmdSplitt[1]);
                            System.out.println(newTask(e));
                        }
                    }
                    break;
                default: // if it is not any of the above commands
                    throw new DukeException(cmdWord + cmdLine);
                }
            } catch(DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch(ToDoException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch(DeadlineException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch(EventException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
    }
}
