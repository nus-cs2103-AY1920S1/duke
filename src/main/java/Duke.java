import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Listing list = new Listing();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Message.greeting();
        while (true) {
            try {
                String[] msg = sc.nextLine().split(" ", 2);
                if (msg[0].equals("bye")) {
                    Message.leavingMsg();
                    break;
                } else if (msg[0].equals("list")) {
                    list.listing(msg);
                } else if (msg[0].equals("done")) {
                    list.mark(msg);
                } else if (msg[0].equals("todo")) {
                    list.addTodo(msg);
                } else if (msg[0].equals("deadline")) {
                    list.addDeadline(msg);
                } else if (msg[0].equals("event")) {
                    list.addEvent(msg);
                } else {
                    list.invalidInput();
                }
            }catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
