import java.util.Scanner;
import java.util.ArrayList;

public class dukeBot {
    // a utility function for performing actions based on commands
    public static void action(String cmd, ArrayList<Task> list, int cnt) {
        if (cmd.equals("")) return;

        Task t;

        if(cmd.startsWith("done")) {
            int index = Integer.valueOf(cmd.substring(5, 6));
            list.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index - 1).toString());
            return;
        } else if (cmd.startsWith("deadline")) {
            int index = cmd.indexOf("/"); // finding the position of "/"
            String desc = cmd.substring(9, index - 1);
            String ddl = cmd.substring(index + 4);
            t = new Deadline(desc, ddl);
            list.add(cnt++, t);
        } else if (cmd.startsWith("event")) {
            int index = cmd.indexOf("/");
            String desc = cmd.substring(6, index - 1);
            String dt = cmd.substring(index + 4);
            t = new Event(desc, dt);
            list.add(cnt++, t);
        } else {
            t = new toDo(cmd.substring(5));
            list.add(cnt++, t);
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    public void start() {
        Scanner sc =  new Scanner(System.in);

        // create storage for tasks
        ArrayList<Task> list = new ArrayList<>();
        int cnt = 0;

        while(true) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                case "Bye":
                    System.out.println("Bye. Hope to see you again soon!"); return;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < list.size(); i++) System.out.println(((i + 1) + ".").concat(list.get(i).toString()));
                    break;
                default: action(input, list, cnt);
                }
            }
        }
    }
