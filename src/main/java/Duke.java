import java.util.*;
import java.io.*;

public class Duke {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] token;
    String tokenString;
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String underline = "____________________________________________________________\n";
    ArrayList<Task> tasks = new ArrayList<Task>();

    private String doubleLine(String msg) {
        return underline + msg + "\n" + underline;
    }

    private void printAddTask(Task task) {
        System.out.println(underline + "Got it. I've added this task:\n  " + task + "\n" + "Now you have " + tasks.size() + " task" + (tasks.size()==1?" ":"s ") + "in the list.\n" + underline);
    }

    private void addTask(String tokenString, String[] token) {
        try {
            if (token[0].equals("todo")) {
                String taskDesc = tokenString.substring(4).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Task task = new ToDo(taskDesc);
                tasks.add(task);
                printAddTask(task);
            } else if (token[0].equals("deadline")) {
                String[] temp = tokenString.split("/by");
                String taskDesc = temp[0].substring(8).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                } else if(temp.length < 2) {
                    throw new DukeException("Event tasks require an deadline time. Did you remember to use \"/by\"?");
                }
                String time = temp[1].trim();
                Task task = new Event(taskDesc, time);
                tasks.add(task);
                printAddTask(task);
            } else if (token[0].equals("event")) {
                String[] temp = tokenString.split("/at");
                String taskDesc = temp[0].substring(5).trim();
                if(taskDesc.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                } else if (temp.length < 2) {
                    throw new DukeException("Event tasks require an event time. Did you remember to use \"/at\"?");
                }
                String time = temp[1].trim();
                Task task = new Event(taskDesc, time);
                tasks.add(task);
                printAddTask(task);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means xd");
            }
        } catch (DukeException e) {
            System.out.print(doubleLine(e.getMessage()));
        }
    }

    private void doneTask(String tokenString, String[] token) {
        int taskDone = Integer.parseInt(token[1]) - 1;
        try {
            if(tasks.size() == 0) {
                throw new DukeException("You have no tasks to be done.");
            } else if(taskDone >= tasks.size() || taskDone < 0) {
                throw new DukeException("Invalid task done. Insert a number from 1 to " + tasks.size() + ".");
            }
            tasks.get(taskDone).markAsDone();
            System.out.println(underline + "Nice! I've marked this task as done:\n  " + tasks.get(taskDone) + "\n" + underline);
        } catch (DukeException e) {
            System.out.print(doubleLine(e.getMessage()));
        }
    }

    private void printList() {
        System.out.print(underline + "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.print(underline);
    }

    private void run() throws IOException {
        System.out.println(underline + "Hello from\n" + logo + "\nWhat can i do for you?\n" + underline);

        tokenString = br.readLine();
        token = tokenString.split(" ");
        while(!(token[0].equals("bye"))) {
            if (token[0].equals("list")) {
                printList();
            } else if (token[0].equals("done")) {
                doneTask(tokenString, token);
            } else if ( token[0].equals("todo") || token[0].equals("deadline") || token[0].equals("event")  ) {
                addTask(tokenString, token);
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.print(doubleLine(e.getMessage()));
                }
            }
            tokenString = br.readLine();
            token = tokenString.split(" ");
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

