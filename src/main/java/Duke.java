import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static int totalTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                listTasks(taskList);
            } else if (input.contains("done")) {
                String[] splitInputs = input.split(" ");
                int index = Integer.parseInt(splitInputs[1]) - 1;
                taskList.get(index).markAsDone();
            } else if (!input.equals("bye")) {
                try {
                    handleTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
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

    public static void listTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < list.size()) && list.get(j) != null; j++) {
            System.out.println(j + 1 + "." + list.get(j));
        }
    }

    public static void handleTask(String task) throws EmptyDescriptionException, InvalidInputException {
        Task t = null;
        String[] detailsArray = task.split(" ");
        if (detailsArray.length == 1 && (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline") ||
                detailsArray[0].equals("event"))) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + detailsArray[0] + " cannot be empty.");
        } else if (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline") ||
                detailsArray[0].equals("event")) {
            System.out.println("Got it. I've added this task:");
            if (detailsArray[0].equals("todo")) {
                String description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, detailsArray.length));
                t = new Todo(description);
                taskList.add(t);
                totalTasks++;
            } else if (detailsArray[0].equals("deadline")) {
                String by = "";
                String description = "";
                for (int i = 0; i < detailsArray.length; i++) {
                    if (detailsArray[i].equals("/by")) {
                        by = String.join(" ", Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length));
                        description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                        break;
                    }
                }
                t = new Deadline(description, by);
                taskList.add(t);
                totalTasks++;
            } else if (detailsArray[0].equals("event")) {
                String at = "";
                String description = "";
                for (int i = 0; i < detailsArray.length; i++) {
                    if (detailsArray[i].equals("/at")) {
                        at = String.join(" ", Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length));
                        description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                        break;
                    }
                }
                t = new Event(description, at);
                taskList.add(t);
                totalTasks++;
            }
            System.out.println("  " + t);
            System.out.println("Now you have " + totalTasks + " tasks in the list.");
        } else if(detailsArray[0].equals("delete")) {
            Task toDelete = taskList.get(Integer.parseInt(detailsArray[1]) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + toDelete);
            taskList.remove(toDelete);
            totalTasks--;
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

