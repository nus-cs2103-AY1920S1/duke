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
            String firstWord = text.split(" ")[0];
            if (firstWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                IntStream.rangeClosed(1, list.size()).forEach(x -> {
                    Task task = list.get(x - 1);
                    System.out.println(x + "." + task.toString());
                });
            } else if (firstWord.equals("done")) {
                String[] words = text.split(" ");
                Task task = list.get(Integer.parseInt(words[1]) - 1);
                task.setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task.toString());
            } else {
                Task task;
                if (firstWord.equals("todo")) {
                    task = new ToDo(text.split(" ", 2)[1]);
                } else if (firstWord.equals("deadline")) {
                    String description = text.split(" ", 2)[1].split(" /", 2)[0];
                    String by = text.split(" ", 2)[1].split(" /by ", 2)[1];
                    task = new Deadline(description, by);
                } else {
                    String description = text.split(" ", 2)[1].split(" /", 2)[0];
                    String at = text.split(" ", 2)[1].split(" /at ", 2)[1];
                    task = new Event(description, at);
                }
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list");
            }
            text = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
