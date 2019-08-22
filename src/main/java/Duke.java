import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");

        while(sc.hasNext()) {
            String inputString = sc.nextLine();
            String inputCommand = inputString.split(" ")[0];
            if (inputCommand.equals("bye")) {
                System.out.println("___________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("___________________________________");
                break;
            } else if (inputCommand.equals("list")) {
                System.out.println("__________________________________");
                System.out.println("Here are the tasks in your list:");
                if (taskList.size() == 0) {
                    System.out.println("List is Empty");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + "." + taskList.get(i));
                    }
                }
                System.out.println("___________________________________");
            } else if (inputCommand.equals("done")) {
                String[] taskNumString = inputString.split(" ");
                int taskNum = Integer.parseInt(taskNumString[1]);
                Task currentTask = taskList.get(taskNum - 1);
                currentTask.markAsDone();
                System.out.println("___________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(currentTask);
                System.out.println("___________________________________");
            } else if (inputCommand.equals("todo")) {
                try {
                    if (inputString.length() == 4 || inputString.length() == 5) {
                        throw new DukeException("todo");
                    }
                    String subInput = inputString.substring(5);
                    Task newTask = new Todo(subInput);
                    taskList.add(newTask);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:\n" + newTask);
                    System.out.println("Now you have " + Task.total + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch (Exception e) {
                    System.out.println("___________________________________");
                    System.out.println(e);
                    System.out.println("___________________________________");
                }
            } else if (inputCommand.equals("deadline")) {
                try {
                    if (!inputString.contains("/by") || inputString.length() == 8 || inputString.length() == 9) {
                        throw new DukeException("deadline");
                    }
                    String subInput1 = inputString.substring(9, inputString.lastIndexOf("/by") - 1);
                    String subInput2 = inputString.substring(inputString.lastIndexOf("/by") + 4);
                    Task newTask = new Deadline(subInput1, subInput2);
                    taskList.add(newTask);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:\n" + newTask);
                    System.out.println("Now you have " + Task.total + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch (Exception e) {
                    System.out.println("___________________________________");
                    System.out.println(e);
                    System.out.println("___________________________________");
                }
            } else if (inputCommand.equals("event")) {
                try {
                    if (!inputString.contains("/at") || inputString.length() == 5 || inputString.length() == 6) {
                        throw new DukeException("event");
                    }
                    String subInput1 = inputString.substring(6, inputString.lastIndexOf("/at"));
                    String subInput2 = inputString.substring(inputString.lastIndexOf("/at") + 4);
                    Task newTask = new Event(subInput1, subInput2);
                    taskList.add(newTask);
                    System.out.println("___________________________________");
                    System.out.println("Got it. I've added this task:\n" + newTask);
                    System.out.println("Now you have " + Task.total + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch (Exception e) {
                    System.out.println("___________________________________");
                    System.out.println(e);
                    System.out.println("___________________________________");
                }
            } else if (inputCommand.equals("delete")) {
                try {
                    if (inputString.length() == 6 || inputString.length() == 7) {
                        throw new DukeException("delete");
                    }
                    String[] words = inputString.split(" ");
                    int taskNum = Integer.parseInt(words[1]);
                    if (taskNum > taskList.size()) {
                        throw new DukeException("index");
                    }
                    Task currentTask = taskList.get(taskNum - 1);
                    taskList.remove(taskNum - 1);
                    System.out.println("___________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(currentTask);
                    Task.total--;
                    System.out.println("Now you have " + Task.total + " tasks in the list.");
                    System.out.println("___________________________________");
                } catch (Exception e) {
                    System.out.println("___________________________________");
                    System.out.println(e);
                    System.out.println("___________________________________");
                }
            } else {
                try {
                    throw new DukeException("invalid");
                } catch (Exception e) {
                    System.out.println("___________________________________");
                    System.out.println(e);
                    System.out.println("___________________________________");
                }
            }
        }
    }
}
