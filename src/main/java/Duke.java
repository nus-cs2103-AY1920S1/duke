import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static int totalTasks = 0;
    private static Task[] taskArray = new Task[100];

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals("list")) {
                listTasks(taskArray);
            } else if(input.contains("done")) {
                String[] splitInputs = input.split(" ");
                int index = Integer.parseInt(splitInputs[1]) - 1;
                taskArray[index].markAsDone();
            } else if (!input.equals("bye")) {
                handleTask(input);
            } else {
                exit();
                break;
            }
        }
    }

    public static void greet() {
        String message = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(message);
    }

    public static void echo(String s) {
        System.out.println("added: " + s);
    }

    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    public static void listTasks(Task[] arr) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < arr.length) && arr[j] != null; j++) {
            System.out.println(j + 1 + "." + arr[j]);
        }
    }

    public static void handleTask(String task) {
        System.out.println("Got it. I've added this task:");
        Task t = null;
        String[] detailsArray = task.split(" ");
        if(detailsArray[0].equals("todo")) {
            String description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, detailsArray.length));
            t = new Todo(description);
            taskArray[totalTasks] = t;
            totalTasks++;
        } else if(detailsArray[0].equals("deadline")) {
            String by = "";
            String description = "";
            for(int i = 0; i < detailsArray.length; i++) {
                if(detailsArray[i].equals("/by")) {
                    by = String.join(" ", Arrays.copyOfRange(detailsArray, i+1, detailsArray.length));
                    description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                    break;
                }
            }
            t = new Deadline(description, by);
            taskArray[totalTasks] = t;
            totalTasks++;
        } else if(detailsArray[0].equals("event")) {
            String at = "";
            String description = "";
            for(int i = 0; i < detailsArray.length; i++) {
                if(detailsArray[i].equals("/at")) {
                    at = String.join(" ", Arrays.copyOfRange(detailsArray, i+1, detailsArray.length));
                    description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                    break;
                }
            }
            t = new Event(description, at);
            taskArray[totalTasks] = t;
            totalTasks++;
        }
        System.out.println("  " + t);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }
}

