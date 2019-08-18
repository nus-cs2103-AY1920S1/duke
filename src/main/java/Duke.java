import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String line = "\t____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String greeting = line
                + "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + line;
        System.out.println(greeting);
        while (!sc.hasNext("bye")) {
            String s = sc.nextLine();
            String arr[] = s.split(" ");
            String cmd = arr[0];
            switch (cmd) {
                case "done":
                    int index = Integer.valueOf(arr[1]);
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println(String.format(line
                            + "\t Nice! I've marked this task as done:\n"
                            + String.format("\t   %s %s\n", task.getStatusIcon(), task.getDescription())
                            + line
                    ));
                    break;
                case "list":
                    String stringlist = line
                            + IntStream
                            .range(0, list.size())
                            .mapToObj(i -> {
                                Task t = list.get(i);
                                return "\t " + String.format("%d.%s %s", i + 1, t.getStatusIcon(), t.getDescription()) + "\n";
                            })
                            .reduce((x,y) -> x + y)
                            .get()
                            + line;
                    System.out.println(stringlist);
                    break;
                default:
                    //Task t = new Task("Hey");
                    //t.markAsDone();
                    list.add(new Task(s));
                    System.out.println(String.format(line + String.format("\t added: %s\n", s) + line));

            }
        }
        System.out.println(line + "\t Bye. Hope to see you again soon!\n" + line);
    }
}
