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

    final static ArrayList _todo = new ArrayList<String>();

    public static String intro() {
        String intro = String.format("%s%n%s%n Hello! I'm Duke%n What can I do for you?%n%s%n",
                        line, logo, line);
        return intro;
    }

    public static String list() {
        StringBuilder s = new StringBuilder(line);
        for (int i = 1; i <= _todo.size(); i++) {
            s.append(System.getProperty("line.separator"));
            s.append(" ").append(i).append(". ");
            s.append(_todo.get(i - 1));
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

    public static String addToList(String task) {
        _todo.add(task);
        String added = String.format("%s%n added: %s%n%s%n",
                        line, task, line);
        return added;
    }

    public static void main(String[] args) { // handles all input and output
        Scanner sc = new Scanner(System.in);
        System.out.println(intro());

        while (sc.hasNext()) {
            String cmd = sc.nextLine();
//            if (sc.hasNext("list") || sc.hasNext("blah") || sc.hasNext("bye")) {
//                cmd = sc.next();
//            } else {
//                cmd = sc.nextLine();
//            }

            switch(cmd) {
                case "list":
                    System.out.println(list());
                    break;
                case "blah":
                    System.out.println(blah());
                    break;
                case "bye":
                    System.out.println(bye());
                    break;
                default:
                    System.out.println(addToList(cmd));
                    break;
            }
        }
    }
}
