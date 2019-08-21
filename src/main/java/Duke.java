import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String text = sc.nextLine();

        while (!text.equals("bye")) {
            if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                IntStream.rangeClosed(1, list.size()).forEach(x -> {
                    Task task = list.get(x - 1);
                    System.out.println(x + ".[" + task.getStatusIcon()  + "] " + task.getTaskName());
                });
            } else {
                String[] words = text.split(" ");
                if (words[0].equals("done")) {
                    list.get(Integer.parseInt(words[1]) - 1).setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [\u2713] " + list.get(Integer.parseInt(words[1]) - 1).getTaskName());
                } else {
                    System.out.println("added: " + text);
                    list.add(new Task(text));
                }
            }
            text = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
