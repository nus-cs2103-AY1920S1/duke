import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>(100);

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {

            String input = sc.nextLine();
            int i = input.indexOf(' ');
            String first = getFirstWord(input);
            System.out.println(first);

            if (input.equals("list")) {
                readList();
            } else if (first.equals("done")) {
                int taskNo = Integer.parseInt(input.substring(i + 1));
                list.get(taskNo).markAsDone();
            } else if (input.equals("bye")) {
                exit();
            } else if (first.equals("todo")) {
                String task = input.substring(i + 1);
                ToDos todo = new ToDos(task);
                todo.setTaskType("T");
                addList(todo);
            } else {

                int j = input.indexOf("/");
                int k = input.indexOf(" ");
                String task = input.substring(k + 1, j + 1);
                String time = input.substring(j + 4);

                if (first.equals("deadline")) {
                    Deadlines ddl = new Deadlines(task);
                    ddl.setTime(time);
                    ddl.setTaskType("D");
                    addList(ddl);
                } else {
                    Events e = new Events(task);
                    e.setTime(time);
                    e.setTaskType("E");
                    addList(e);
                }
            }
        }
    }

    private static void readList() {
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 1;i <= list.size();i++) {
            if (list.get(i - 1).taskType.equals("D")) {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description + "(by: " + list.get(i - 1).time + ")");
            } else if (list.get(i - 1).taskType.equals("E")) {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description + "(at: " + list.get(i - 1).time + ")");
            } else {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description);
            }

        }
    }

    private static void addList(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:");
        if (t.taskType.equals("D")) {
            System.out.println("[" + t.taskType + "][" + t.getStatusIcon() + "] " + t.description + "(by: " + t.time + ")");
        } else if (t.taskType.equals("E")) {
            System.out.println("[" + t.taskType + "][" + t.getStatusIcon() + "] " + t.description + "(at: " + t.time + ")");
        } else {
            System.out.println("[" + t.taskType + "][" + t.getStatusIcon() + "] " + t.description);
        }
        System.out.println("Now you have " + list.size() + " tasks in the list");

    }


    private static void exit() {
        System.out.println("Bye! Hope to see you again soon!");
        System.exit(0);
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    private static String getFirstWord(String input) {
        if (input.indexOf(" ") > -1) {
            return input.substring(0, input.indexOf(" "));
        } else {
            return input;
        }
    }
}
