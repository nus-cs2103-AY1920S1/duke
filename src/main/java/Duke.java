import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printList(ArrayList<Task> list) {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : list) {
            System.out.println(idx + "." + t);
            idx++;
        }
    }

    public static String subString(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }

    public static int findIdx(String[] words, String s) {
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(s))
                return i;
        }
        return -1;
    }

    public static Task parseTask(String[] words) {
        if(words[0].equals("todo")) {
            return new ToDo(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String by = subString(words, i + 1, words.length);
            return new Deadline(description, by);
        } else {
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String at = subString(words, i + 1, words.length);
            return new Event(description, at);
        }
    }

    public static void checkIllegalInstruction(String[] words) throws DukeException {
        String fw = words[0];
        if (!(fw.equals("done") || fw.equals("todo") || fw.equals("deadline") || fw.equals("event")
                    || fw.equals("delete"))) {
            throw new DukeException(" \u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
        if ((fw.equals("todo") || fw.equals("deadline") || fw.equals("event")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The description of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("deadline") && findIdx(words, "/by") == -1) || 
                (fw.equals("event") && findIdx(words, "/at") == -1)) {
            throw new DukeException(" \u2639  OOPS!!! The time of a " + fw + " cannot be empty.");
                }
        if ((fw.equals("done") || fw.equals("delete")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a " + fw + " cannot be empty.");
        }
    }

    public static void processor(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String command;
        while(!(command = sc.nextLine()).equals("bye")) {
            if(command.equals("list")) {
                printList(tasks);
            } else {
                String[] words = command.split(" ");
                try {
                    checkIllegalInstruction(words);
                    if (words[0].equals("done")) {
                        int i = Integer.parseInt(words[1]);
                        Task t = tasks.get(i - 1);
                        t.markAsDone();
                        System.out.println("Nice! I've marked this task as done: \n  " + t);
                    } else if (words[0].equals("delete")) {
                        int i = Integer.parseInt(words[1]);
                        Task t = tasks.get(i - 1);
                        tasks.remove (i - 1);
                        System.out.println("Noted. I've removed this task: \n  " + t + "\nNow you have "
                                + tasks.size() + " tasks in the list.");
                    } else {
                        Task t = parseTask(words);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task: \n  " + t + "\nNow you have "
                                + tasks.size() + " tasks in the list.");
                    }
                }
                catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        processor();
    }
}
