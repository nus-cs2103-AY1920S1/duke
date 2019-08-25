import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                int index = 0;
                for (Task task : list) {
                    index++;
                    System.out.println(index + ". " + task.toString());
                }
                str = sc.nextLine();
            } else if (str.contains("done")) {
                int index = Integer.valueOf(str.split(" ")[1]);
                list.get(index - 1).complete();
                str = sc.nextLine();
            } else {
                System.out.println("added: " + str);
                list.add(new Task(str));
                str = sc.nextLine();
            }
        }
    }
}

class Task {
    String content;
    boolean done;
    static final int tick = '\u2713';
    static final int cross = '\u2717';

    public Task(String content) {
        this.content = content;
    }

    public void complete() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done: \n  [" + (char)tick + "] " + this.content);
    }

    @Override
    public String toString() {
        return done ? "[" + (char)tick + "] " + content : "[" + (char)cross + "] " + content;
    }
}