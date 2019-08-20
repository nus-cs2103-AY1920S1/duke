import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String liner = "    ____________________________________________________________";

    public static void main(String[] args) {
        //greet user
        String greeting = "     Hello! I'm Duke\n     What can I do for you?";
        System.out.println(liner + "\n" + greeting + "\n" + liner);

        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                System.out.println(liner);
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < counter; i++ ) {
                    int num = i + 1;
                    Task currTask = tasks.get(i);
                    System.out.println("     " + num + ". " + currTask.toString());
                }
                System.out.println(liner);
            } else if (input.startsWith("done")) {
                String[] arr = input.split(" ");
                if (arr.length == 2 && arr[1].matches("\\d+")) {
                 Task currTask = tasks.get(Integer.parseInt(arr[1]) - 1);
                 currTask.markAsDone();
                 printDoneTask(currTask);
                }
            } else if (input.startsWith("todo")) {
                Task newTask = new Todo(input.replaceFirst("todo", "").trim());
                tasks.add(newTask);
                printAddTask(newTask, tasks.size());
                counter++;
            } else if (input.startsWith("deadline")) {
                String task = input.replaceFirst("deadline", "").trim();
                String[] arr = task.split("/by");
                Task newTask = new Deadline(arr[0].trim(), arr[1].trim());
                tasks.add(newTask);
                printAddTask(newTask, tasks.size());
                counter++;
            } else if (input.startsWith("event")) {
                String task = input.replace("Event", "").trim();
                String[] arr = task.split("/at");
                Task newTask = new Event(arr[0].trim(), arr[1].trim());
                tasks.add(newTask);
                printAddTask(newTask, tasks.size());
                counter++;
            } else {
                System.out.println("Invalid Command: Please try again!");
            }
        }
        System.out.println(liner + "\n     Bye. Hope to see you again soon!\n" + liner);
    }

    public static void printAddTask(Task newTask, int totalTasks) {
        System.out.println(liner);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask.toString());
        System.out.println("     Now you have " + totalTasks + " tasks in the list.");
        System.out.println(liner);
    }

    public static void printDoneTask(Task currTask) {
        System.out.println(liner);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       "  + currTask.toString());
        System.out.println(liner);
    }
}
