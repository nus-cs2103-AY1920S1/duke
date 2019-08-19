import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> taskList = new ArrayList<>();

        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        printMessage(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");

        while(sc.hasNext()) {
            String[] inputs = sc.nextLine().split(" ");

            if(inputs[0].equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                return;
            } else if(inputs[0].equals("list")) {
                printTaskList(taskList);
            } else if(inputs[0].equals("done")) {
                int index = Integer.parseInt(inputs[1]) - 1;
                taskList.set(index, taskList.get(index).isDone());
                printMessage("Nice! I've marked this task as done:"
                        + "\n\t   " + taskList.get(index).toString());
            } else {
                String newTaskName = fetchString(inputs);
                Task newTask = new Task(newTaskName);
                taskList.add(newTask);
                printMessage("added: " + newTaskName);
            }
        }

    }

    public static void printMessage(String output) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + output);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void printTaskList(List<Task> taskList) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + " " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static String fetchString(String[] arr) {
        String result = "";

        int index = 0;

        while(index < arr.length) {
            result = result + arr[index] + " ";
            index++;
        }

        return result.substring(0, result.length() - 1);
    }
}


