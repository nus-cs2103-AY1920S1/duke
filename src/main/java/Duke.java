import java.util.Scanner;

public class Duke {
    static Task[] arr = new Task[100];
    static int count = 1;
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String word = sc.next();
            System.out.println("    ____________________________________________________________");
            if (word.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (word.equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (Task task: arr) {
                    if (task != null) {
                        System.out.println(task.listify());
                    }
                }
            } else if (word.equals("done")) {
                int number = Integer.parseInt(sc.next());
                arr[number].setDone();
                System.out.println(arr[number].doneify());
            } else {
                Task task = new Task(count, word + " " + sc.nextLine());
                arr[count] = task;
                System.out.println(task);
                count++;
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
