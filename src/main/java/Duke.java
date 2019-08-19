import java.util.*;
import java.io.*;

public class Duke {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] token;
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String underline = "____________________________________________________________\n";
    ArrayList<Task> tasks = new ArrayList<Task>();

    private void printAddTask(Task task) {
        System.out.println(underline + "Got it. I've added this task:\n  " + task + "\n" + "Now you have " + tasks.size() + " task" + (tasks.size()==1?" ":"s ") + "in the list.\n" + underline);
    }

    private Task parseAddTask(String[] token, String delimiter) {
        StringBuilder desc = new StringBuilder();
        StringBuilder time = new StringBuilder();
        int j = token.length;
        for( int i = 1 ; i < token.length ; i++ ) {
            if (token[i].equals(delimiter)) {
                j = i;
                break;
            } else {
                desc.append(token[i]) ;
                if(i != token.length-1) {
                    desc.append(" ");
                }
            }
        }
        for ( int k = j + 1 ; k < token.length ; k++ ) {
            time.append(token[k]);
        }
        if(token[0].equals("deadline")) {
            return new Deadline(desc.toString(), time.toString());
        } else if(token[0].equals("event")) {
            return new Event(desc.toString(), time.toString());
        } else {
            return null;
            //throw smth
        }
    }

    private void run() throws IOException {
        System.out.println(underline + "Hello from\n" + logo + "\nWhat can i do for you?\n" + underline);

        token = br.readLine().split(" ");
        while(!(token[0].equals("bye"))) {
            if (token[0].equals("list")) {
                System.out.print(underline + "Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.print(underline);
                token = br.readLine().split(" ");
            } else if (token[0].equals("done")) {
                int taskDone = Integer.parseInt(token[1]) - 1;
                tasks.get(taskDone).markAsDone();
                System.out.println(underline + "Nice! I've marked this task as done:\n  " + tasks.get(taskDone) + "\n" + underline);
                token = br.readLine().split(" ");
            } else if (token[0].equals("deadline")) {
                Task task = parseAddTask(token, "/by");
                tasks.add(task);
                printAddTask(task);
                token = br.readLine().split(" ");
            } else if (token[0].equals("event")) {
                Task task = parseAddTask(token, "/at");
                tasks.add(task);
                printAddTask(task);
                token = br.readLine().split(" ");
            } else if (token[0].equals("todo")) {
                StringBuilder desc = new StringBuilder();
                for( int i = 1 ; i < token.length ; i++ ) {
                    desc.append(token[i]);
                    if(i != token.length-1) {
                        desc.append(" ");
                    }
                }
                Task task = new ToDo(desc.toString());
                tasks.add(task);
                printAddTask(task);
                token = br.readLine().split(" ");
            } else {
                //token = br.readLine().split(" ");
                //throw smth
            }
        }
        System.out.print(underline + "Bye. Hope to see you again soon!\n" + underline);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

