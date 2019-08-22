import java.util.Scanner;

public class Duke {

    private static Task[] list = new Task[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        boolean continues = true;
        String description = sc.nextLine();
        int counter = 0;

        while (!description.equals("bye")) {
            try {
                boolean valid = checkValidity(description);
                if(valid) {
                    String[] part = description.split(" ");
                    String command = part[0]; // to extract the first word of input description.
                    switch (command) {
                        case "list":
                            showList(counter);
                            break;
                        case "done":
                            int indexToMark = Integer.parseInt(part[1]);
                            list[indexToMark - 1].markAsDone();
                            System.out.println("Nice! I've marked this task as done: \n  " +
                                    list[indexToMark - 1]);
                            break;
                        case "todo":
                        case "deadline":
                        case "event":
                            addTask(description, counter, command);
                            counter++;
                            break;
                    }
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            description = sc.nextLine();
        }

        if (description.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            sc.close(); //exit the program
        }
    }

    // To check validity of the input description.
    public static boolean checkValidity(String description) throws DukeException {
            String[] part = description.split(" ");
            String firstWord = part[0];
            switch (firstWord) {
                case "list": case "done":
                    break;
                case "todo":
                    // check task description.
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    break;
                case "deadline":
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    break;
                case "event":
                    if (part.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return true;
    }


    // To print out the items stored in the list.
    public static void showList(int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i+1) + ". " + list[i]);
        }
    }

    // To add a new task into the list array.
    public static void addTask(String description, int counter, String command) {
        Task newTask;
        switch(command)
        {
            case "todo":
                String[] activity = description.split("todo");
                newTask = new Todo(activity[1]);
                break;
            case "deadline":
                String[] details = description.split("deadline");
                String[] activity1 = details[1].split("/");
                String[] time = activity1[1].split("by");
                newTask = new Deadline(activity1[0], time[1]);
                break;
            case "event":
                String[] details2 = description.split("event");
                String[] activity2 = details2[1].split("/");
                String[] time2 = activity2[1].split("at");
                newTask = new Event(activity2[0], time2[1]);
                break;
            default:
                newTask = new Task(command);
        }

        list[counter] = newTask;
        System.out.println("Got it. I've added this task: \n  " +
                newTask + "\nNow you have " + (counter + 1) + " tasks in the list.");
    }
}
