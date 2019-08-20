import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String s;
        List<Task> ls = new ArrayList<>();
        while (!(s = sc.nextLine()).equals("bye")) {
            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= ls.size(); i++) {
                    System.out.println(String.format("%d. %s", i, ls.get(i - 1)));
                }
            } else if (s.contains("done ")){
                int index = Integer.parseInt(s.substring(5)) - 1;
                ls.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(ls.get(index));
            } else {
                Task newTask;
                if (s.startsWith("deadline")) {
                    String[] phrases = s.substring(9).split(" /by ");
                    newTask = new Deadline(phrases[0], phrases[1]);
                } else if (s.startsWith("event")) {
                    String[] phrases = s.substring(6).split(" /at ");
                    newTask = new Event(phrases[0], phrases[1]);
                } else {
                    newTask = new Todo(s.substring(5));
                }
                ls.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println(String.format("Now you have %d tasks in the list.", Task.getNumberOfTasks()));
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
