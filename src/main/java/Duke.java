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
            String msg = sc.next();
            if(msg.equals("bye")) {
                Message.leavingMsg();
                break;
            }else if(msg.equals("list")) {
                list.listing();
            } else if (msg.equals("done")) {
                int index = sc.nextInt();
                list.mark(index);
            } else if(msg.equals("todo")) {
                String msgs = sc.nextLine();
                list.addTodo(msgs);
            } else if(msg.equals("deadline")) {
                String msgs = sc.nextLine();
                list.addDeadline(msgs);
            } else if(msg.equals("event")) {
                String msgs = sc.nextLine();
                list.addEvent(msgs);
            }
        }
    }
}
