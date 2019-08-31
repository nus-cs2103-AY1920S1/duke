package duke.frontend;

import java.util.Scanner;
import java.util.ArrayList;
import duke.exception.*;
import duke.task.*;

public class DukeBot {
    private static int cnt = 0;
    // a utility function for performing actions based on commands
    public static void action(String cmd, ArrayList<Task> list) throws DukeWrongTaskException, UnknownCmdException, DeleteTaskException, CompleteTaskException {
        Task t;

        if (cmd.startsWith("done")) {
            if (cmd.length() <= 5 || Integer.valueOf(cmd.substring(5)) >= list.size() + 1) throw (new CompleteTaskException());
            int index = Integer.valueOf(cmd.substring(5));
            list.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(index - 1).toString());
            return;
        } else if (cmd.startsWith("delete")) {
            if (cmd.length() <= 7 || Integer.valueOf(cmd.substring(7)) >= list.size() + 1) throw (new DeleteTaskException());
            int index = Integer.valueOf(cmd.substring(7));
            System.out.println("Noted! I've removed this task:");
            System.out.println(list.get(index - 1).toString());
            list.remove(index - 1);
            cnt--;
            System.out.printf("Now you have %d tasks in the list.\n", list.size());
            return;
        } else if (cmd.startsWith("deadline")) {
            if (cmd.length() <= 9 || !cmd.contains("/")) throw (new DukeWrongTaskException("deadline"));
            int index = cmd.indexOf("/"); // finding the position of "/"
            String desc = cmd.substring(9, index - 1);
            String ddl = cmd.substring(index + 4);
            t = new Deadline(desc, ddl);
            list.add(cnt++, t);
        } else if (cmd.startsWith("event")) {
            if (cmd.length() <= 6 || !cmd.contains("/")) throw (new DukeWrongTaskException("event"));
            int index = cmd.indexOf("/");
            String desc = cmd.substring(6, index - 1);
            String dt = cmd.substring(index + 4);
            t = new Event(desc, dt);
            list.add(cnt++, t);
        } else if (cmd.startsWith("todo")){
            if (cmd.length() <= 5) throw (new DukeWrongTaskException("toDo"));
            t = new toDo(cmd.substring(5));
            list.add(cnt++, t);
        } else {
            throw (new UnknownCmdException());
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    public void start() throws EmptyListException {
        Scanner sc =  new Scanner(System.in);

        // create storage for tasks
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                case "Bye":
                    System.out.println("Bye. Hope to see you again soon!"); return;
                case "list":
                    try {
                        if (list.size() == 0) throw (new EmptyListException());
                        System.out.println("Here are the tasks in your list:");
                        for(int i = 0; i < list.size(); i++) System.out.println(((i + 1) + ".").concat(list.get(i).toString()));
                        break;
                    } catch (EmptyListException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                default:
                    try {
                        action(input, list);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
