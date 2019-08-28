import java.util.Scanner;

public class Main {
    static String format(String command) {
        return "    ____________________________________________________________\n"
                + indent(command)
                + "    ____________________________________________________________\n";
    }

    static String indent(String command) {
        StringBuffer strb = new StringBuffer();
        Scanner sc = new Scanner(command);
        while(sc.hasNext()) {
            String temp = sc.nextLine();
            strb.append("     " + temp + "\n");
        }
        sc.close();
        return strb.toString();
    }

    static void response(String command) {
        System.out.println(format(command));
    }

    public static void main(String[] args) {

        response("Hello! I'm Duke\n"
                + "What can I do for you?");

        Task[] list = new Task[100];
        int len = 0;

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            String in = sc.next();
            switch (in) {
            case "bye":
                response("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                StringBuffer listBuffer = new StringBuffer();
                for(int i = 0; i < len; i++) {
                    listBuffer.append((i + 1)
                            + "."
                            + list[i].toString()
                            + "\n");
                }
                response(listBuffer.toString());
                break;
            case "done":
                int item = sc.nextInt() - 1;
                list[item].markAsDone();
                response("Nice! I've marked this task as done: \n  "
                        + list[item].toString());
                break;
            default:
                String description = in + sc.nextLine();
                response("added: " + description);
                list[len] = new Task(in);
                len++;
                break;
            }
        }
        sc.close();
    }
}