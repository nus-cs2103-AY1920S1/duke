import java.util.Scanner;

public class Main {
    static String format(String command) {
        return "    ____________________________________________________________\n" +
                indent(command) +
                "    ____________________________________________________________\n";
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
        Scanner sc = new Scanner(System.in);
        response("Hello! I'm Duke\n" +
                "What can I do for you?");
        String[] list = new String[100];
        int len = 0;
        while(true) {
            String in = sc.nextLine();
            if(in.equals("bye")) {
                response("Bye. Hope to see you again soon!");
                break;
            }
            response(in);
        }
    }
}