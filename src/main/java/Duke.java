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

    private void run() throws IOException {
        System.out.println(underline + "Hello from\n" + logo + "\nWhat can i do for you?\n" + underline);

        token = br.readLine().split(" ");
        while(!(token[0].equals("bye"))) {
            if(token[0].equals("list")) {
                System.out.print(underline + "Here are the tasks in your list:\n");
                for(int i = 0 ; i < tasks.size() ; i++ ) {
                    System.out.println( (i+1) + "." + tasks.get(i));
                }
                System.out.print(underline);
                token = br.readLine().split(" ");
            } else if (token[0].equals("done")) {
                int taskDone = Integer.parseInt(token[1]) - 1;
                tasks.get(taskDone).markAsDone();
                System.out.println(underline + "Nice! I've marked this task as done:\n  " + tasks.get(taskDone) + "\n" + underline);
                token = br.readLine().split(" ");
            } else {
                StringBuilder task = new StringBuilder();
                for( int i = 0 ; i < token.length ; i++) {
                    if( i != (token.length - 1 ) ) {
                        task.append(token[i]);
                        task.append(" ");
                    } else {
                        task.append(token[i]);
                    }
                }
                tasks.add(new Task(task.toString()));
                System.out.println(underline + "added: " + task + "\n" + underline);
                token = br.readLine().split(" ");
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

