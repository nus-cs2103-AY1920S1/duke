import java.util.List;
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
//            s.append(t.getStatusIcon()).append("] ");
//            s.append(t.getDesc());
        }
        s.append(System.getProperty("line.separator"));
        s.append(line);
        s.append(System.getProperty("line.separator"));
        return s.toString();
    }

    public static String blah() {
        String blah = String.format("%s%n blah%n%s%n",
                        line, line);
        return blah;
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

    public static void main(String[] args) { // handles all input and output
        Scanner sc = new Scanner(System.in);
        System.out.println(intro());

        while (sc.hasNext()) {
            String cmdWord = sc.next(); // extract first only
            String cmdLine = sc.nextLine(); // extract the rest of the line

            switch(cmdWord) {
                case "list":
                    System.out.println(list());
                    break;
                case "blah":
                    System.out.println(blah());
                    break;
                case "bye":
                    System.out.println(bye());
                    break;
                case "done":
                    int taskNo = Integer.parseInt(cmdLine.substring(1));
                    System.out.println(done(taskNo));
                    break;
                case "todo":
                    ToDo td = new ToDo(cmdLine);
                    System.out.println(newTask(td));
                    break;
                case "deadline":
                    String[] cmdSplit = cmdLine.split("/by");
                    Deadline dl = new Deadline(cmdSplit[0], cmdSplit[1]);
                    System.out.println(newTask(dl));
                    break;
                case "event":
                    String[] cmdSplitt = cmdLine.split("/at");
                    Event e = new Event(cmdSplitt[0], cmdSplitt[1]);
                    System.out.println(newTask(e));
                    break;
            }
        }
    }
}
