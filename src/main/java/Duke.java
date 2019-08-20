import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    boolean exited = false;
    List<Task> toDoList = new ArrayList<>();

    public void respond() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (!exited && scanner.hasNextLine()) {
            String input = scanner.nextLine();

            switch (input) {
                case "bye":
                    exited = true;
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                case "list":

                    System.out.println("Here are the tasks in your list:");
                    int index = 1;
                    for (Task s : toDoList) {
                        System.out.print(index + ". " + s);
                        index++;
                    }
                    break;

                default:
                    if (input.contains("done")) {
                        int taskNum = Integer.parseInt(input.substring(5)) - 1;
                        Task updatedTask = toDoList.get(taskNum);
                        updatedTask.markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(updatedTask);


                    } else if (input.contains("todo")) {

                        String desc = input.substring(5);

                        Todo newTodo = new Todo(desc);

                        toDoList.add(newTodo);

                        int numTask = toDoList.size();


                        System.out.println("Got it. I've added this task: \n" + "  "
                                + newTodo + "Now you have " +
                                numTask + " tasks in the list.");


                    } else if (input.contains("deadline")) {

                        int deadlineIndex = input.indexOf('/') + 4;
                        String deadline = input.substring(deadlineIndex);
                        String desc = input.substring(9, deadlineIndex - 5);

                        Deadline newDeadline = new Deadline(desc, deadline);

                        toDoList.add(newDeadline);

                        int numTask = toDoList.size();


                        System.out.println("Got it. I've added this task: \n" + "  "
                                + newDeadline + "Now you have " +
                                numTask + " tasks in the list.");
                    } else if (input.contains("event")) {

                        int timeIndex = input.indexOf('/') + 4;
                        String time = input.substring(timeIndex);

                        String desc = input.substring(6, timeIndex - 5);


                        Event newEvent = new Event(desc, time);

                        toDoList.add(newEvent);

                        int numTask = toDoList.size();


                        System.out.println("Got it. I've added this task: \n" + "  "
                                + newEvent + " Now you have" +
                                numTask + " tasks in the list.");
                    }
            }
        }
    }

    public static void main(String[] args) {
        Duke D1 = new Duke();
        D1.respond();
    }

}
