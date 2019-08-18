import java.util.Scanner;

public class Duke { // handles all input and output
    final static String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    final static String line = "____________________________________________________________";

    public static String intro() {
        String intro = String.format("%s%n%s%n Hello! I'm Duke%n What can I do for you?%n%s%n",
                        line, logo, line);
        return intro;
    }

    public static String list() {
        String list = String.format("%s%n list%n%s%n",
                        line, line);
        return list;
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

    public static void main(String[] args) { // handles all input and output
        Scanner sc = new Scanner(System.in);
        System.out.println(intro());

        while (sc.hasNext()) {
            String cmd = sc.next();

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
            }
        }
    }
}
