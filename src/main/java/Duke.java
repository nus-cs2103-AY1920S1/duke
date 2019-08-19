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
            String name = "";
            String time = "";

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
            } else if(inputs[0].equals("todo")){

                for(int i = 1; i < inputs.length; i++) {
                    name = name + inputs[i] + " ";
                }
                name = name.substring(0, name.length() - 1);
                Todo newTask = new Todo(name);
                taskList.add(newTask);
                printMessage("Got it. I've added this task: " +
                        "\n\t   " + newTask.toString()
                        + "\n\t Now you have " + taskList.size() + " tasks in the list.");

            } else if(inputs[0].equals("deadline") || inputs[0].equals("event")) {
                String inputType = inputs[0];

                inputs = fetchString(inputs);

                name = inputs[0].substring(0, inputs[0].length() - 1);
                time = inputs[1].substring(0, inputs[1].length() - 1);

                Task newTask;

                if(inputType.equals("deadline")) {
                    newTask = new Deadline(name, time);
                } else {
                    newTask = new Event(name, time);
                }
                taskList.add(newTask);
                printMessage("Got it. I've added this task: " +
                        "\n\t   " + newTask.toString()
                        + "\n\t Now you have " + taskList.size() + " tasks in the list.");
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

    public static String[] fetchString(String[] arr) {
        String[] result = {"", ""};

        int index = 1;

        while(index < arr.length && !(arr[index].charAt(0) == '/')) {
            result[0] = result[0] + arr[index] + " ";
            index++;
        }

        //Skip the /at or /by
        index++;

        while(index < arr.length) {
            result[1] = result[1] + arr[index] + " ";
            index++;
        }

        return result;

    }
}


