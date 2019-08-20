import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);

    String lineWrap(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________________________________\n");
        sb.append(input + "\n");
        sb.append("____________________________________________________________\n");
        return sb.toString();
    }

    private String greet() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?");
        return sb.toString();
    }

    private String bye() {
        return lineWrap("Bye. Hope to see you again soon!");
    }

    void start() {
        System.out.println(greet());
        while(sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(bye());
                break;
            } else {
                System.out.println(lineWrap(command));
            }
        }
    }


}
