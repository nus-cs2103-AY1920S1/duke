import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.String;
import java.lang.Integer;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        String input;
        List<Task> toDoList = new ArrayList<>();
        boolean isBye = false;
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Hello from\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(indentation + horizontalLine + "\n");
        while (!isBye) {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println(indentation + horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    int index = 1;
                    for (Iterator iterator = toDoList.iterator(); iterator.hasNext(); index++) {
                        System.out.println(indentation + index + "." + iterator.next());
                    }
                    System.out.println(indentation + horizontalLine + "\n");
                } else if (input.startsWith("done")) {
                    System.out.println(indentation + horizontalLine);
                    System.out.println("Nice! I've marked this task as done:");
                    int doneIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task doneTask = toDoList.get(doneIndex);
                    doneTask.markAsDone();
                    System.out.println(indentation + doneTask);
                    System.out.println(indentation + horizontalLine + "\n");
                } else {
                    System.out.println(indentation + horizontalLine);
                    Task task = new Task(input);
                    toDoList.add(task);
                    System.out.println(indentation + "added: " + input);
                    System.out.println(indentation + horizontalLine + "\n");
                }
            }
            else {
                isBye = true;
            }
        }
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + horizontalLine);
    }
}