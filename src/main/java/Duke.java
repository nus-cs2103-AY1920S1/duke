import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        while(input.hasNext()) {
            String command = input.next();

            switch(command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.exit(0);
                    break;

                case "done":
                    try {
                        int taskNumber = input.nextInt();
                        Task current = tasks.get(taskNumber - 1);
                        if(current.getStatus()) {
                            throw new DukeTaskDoneException();
                        }
                        current.markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(current.toString());
                    } catch (InputMismatchException e) {
                        System.out.println("OOPS!!! Number of completed task required.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("OOPS!!! Input is an invalid task number.");
                    } catch (DukeTaskDoneException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i++) {
                        Task current = tasks.get(i);
                        System.out.println((i + 1) + "." + current.toString());
                    }
                    break;

                case "todo":
                    try {
                        String toDo = input.nextLine().trim();
                        if(toDo.isEmpty()) {
                            throw new DukeEmptyDescriptionException("todo");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            Task current = new Todo(toDo);
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() +" task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "deadline":
                    try {
                        String deadlineTask = input.nextLine().trim();
                        String[] information = deadlineTask.split("/by");
                        if (information.length != 2 && deadlineTask.isEmpty()) {
                            throw new DukeEmptyDescriptionException("deadline");
                        } else if (information.length != 2 && !deadlineTask.isEmpty()) {
                            throw new DukeMissingDescriptionException(("deadline"));
                        } else {
                            System.out.println("Got it. I've added this task:");
                            Task current = new Deadline(information[0].trim(), information[1].trim());
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() + " task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (DukeMissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "event":
                    try {
                        String eventTask = input.nextLine().trim();
                        String[] information = eventTask.split("/at");
                        if (information.length != 2 && eventTask.isEmpty()) {
                            throw new DukeEmptyDescriptionException("event");
                        } else if (information.length != 2 && !eventTask.isEmpty()) {
                            throw new DukeMissingDescriptionException("event");
                        } else {
                            System.out.println("Got it. I've added this task:");
                            Task current = new Event(information[0].trim(), information[1].trim());
                            tasks.add(current);
                            System.out.println(current.toString());
                            System.out.println("Now you have " + tasks.size() + " task in the list.");
                        }
                    } catch (DukeEmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (DukeMissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                default:
                    try {
                        throw new DukeWrongInputException();
                    } catch (DukeWrongInputException e) {
                        System.out.println(e.getMessage());
                    }
            }

        }
    }
}


