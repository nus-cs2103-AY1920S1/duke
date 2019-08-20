import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //greet user
        String liner = "    ____________________________________________________________";
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        String input;
        Task[] tasks = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                System.out.println(liner);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < counter; i++ ) {
                    int num = i + 1;
                    Task currTask = tasks[i];
                    System.out.println("     " + num + ". [" + currTask.getStatusIcon() + "]" + currTask.getDescription());
                }
                System.out.println(liner);
            } else if (input.startsWith("done")) {
                String[] arr = input.split(" ");
                if (arr.length == 2 && arr[1].matches("\\d+")) {
                 Task currTask = tasks[Integer.parseInt(arr[1]) - 1];
                 currTask.markAsDone();
                 System.out.println(liner);
                 System.out.println("     Nice! I've marked this task as done: ");
                 System.out.println("       [" + currTask.getStatusIcon() + "]" + currTask.getDescription());
                 System.out.println(liner);
                }
            } else {
                Task newtask = new Task(input);
                tasks[counter] = newtask;
                System.out.println(liner);
                System.out.println("     added: " + input);
                System.out.println(liner);
                counter++;
            }
        }

        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }
}
