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

            try {
                if (input.equals("list")) {
                    readList();
                } else if (first.equals("done")) {
                    int taskNo = Integer.parseInt(input.substring(i + 1));
                    list.get(taskNo).markAsDone();
                } else if (first.equals("delete")) {
                    int taskNo = Integer.parseInt(input.substring(i + 1));
                    delete(taskNo);
                } else if (input.equals("bye")) {
                    exit();
                } else if (first.equals("todo")) {
                    String task = input.substring(i + 1);
                    if (input.length() == 4) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        ToDos todo = new ToDos(task);
                        todo.setTaskType("T");
                        addList(todo);
                    }
                } else if (first.equals("deadline") || first.equals("event")) {

                    int j = input.indexOf("/");
                    int k = input.indexOf(" ");
                    String task = input.substring(k + 1, j - 1);
                    String time = input.substring(j + 4);

                    if (first.equals("deadline")) {
                        if (input.length() == 8) {
                            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            Deadlines ddl = new Deadlines(task);
                            ddl.setTime(time);
                            ddl.setTaskType("D");
                            addList(ddl);
                        }
                    } else {
                        if (input.length() == 5) {
                            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        } else {
                            Events e = new Events(task);
                            e.setTime(time);
                            e.setTaskType("E");
                            addList(e);
                        }
                    }
                } else {
                    DukeException e2 = new DukeException(input);
                    throw e2;
                }
            } catch (DukeException e2) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
            }
        }
    }

    private static void delete(int taskNo) {
        System.out.println("Noted. I've removed this task: ");

        if (list.get(taskNo - 1).taskType.equals("T")) {
            System.out.println("  [" + list.get(taskNo - 1).taskType + "][" + list.get(taskNo - 1).getStatusIcon() + "] " + list.get(taskNo - 1).description);
        } else if (list.get(taskNo - 1).taskType.equals("D")) {
            System.out.println("  [" + list.get(taskNo - 1).taskType + "][" + list.get(taskNo - 1).getStatusIcon() + "] " + list.get(taskNo - 1).description + " (by: " + list.get(taskNo - 1).time + ")");
        } else {
            System.out.println("  [" + list.get(taskNo - 1).taskType + "][" + list.get(taskNo - 1).getStatusIcon() + "] " + list.get(taskNo - 1).description + " (at: " + list.get(taskNo - 1).time + ")");
        }
        list.remove(taskNo - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    private static void readList() {
        System.out.println("Here are the tasks in your list:\n");
        for(int i = 1;i <= list.size();i++) {
            if (list.get(i - 1).taskType.equals("D")) {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description + " (by: " + list.get(i - 1).time + ")");
            } else if (list.get(i - 1).taskType.equals("E")) {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description + " (at: " + list.get(i - 1).time + ")");
            } else {
                System.out.println(i + "." + "[" + list.get(i - 1).taskType + "][" + list.get(i - 1).getStatusIcon() + "] " + list.get(i - 1).description);
            }

        }
    }

    private static void addList(Task t) {
        list.add(t);
        System.out.println("Got it. I've added this task:");
        if (t.taskType.equals("D")) {
            System.out.println("[" + t.taskType + "][" + t.getStatusIcon() + "] " + t.description + " (by: " + t.time + ")");
        } else if (t.taskType.equals("E")) {
            System.out.println("[" + t.taskType + "][" + t.getStatusIcon() + "] " + t.description + " (at: " + t.time + ")");
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
