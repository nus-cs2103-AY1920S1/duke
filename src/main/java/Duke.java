import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");


        Scanner scan = new Scanner(System.in);
        Task[] list = new Task[100];
        int index = 1;
        String input = scan.nextLine();

        while (!input.equals("bye")) {
            String arr[] = input.split(" ",2 );
            String command = arr[0];
            try {
                switch (command) {
                    case "list":
                        if (index == 1) {
                            throw new DukeException("OOPS!!! You have no tasks to be displayed.");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 1; i < index; i++) {
                                System.out.println(i + "." + list[i]);
                            }
                        }
                        break;
                    case "done":
                        if (arr.length == 1) {
                            throw new DukeException("OOPS!!! Please enter a task number to check as done e.g done 1");
                        } else {
                            int num = Integer.parseInt(arr[1]);
                            if (num >= index || num < 1) {
                                throw new DukeException("This task does not exist.");
                            } else {
                                System.out.println(list[num].done());
                            }
                        }
                        break;
                    case "todo":
                        if (arr.length == 1) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            list[index] = new Task(arr[1]);
                            System.out.println("Got it. I've added this task:\n" + list[index]);
                            System.out.println("Now you have " + index + " tasks in the list");
                            index++;
                        }
                        break;
                    case "deadline":
                        if (arr.length == 1) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String[] deadline = arr[1].split("/by ", 2);
                            if (deadline.length == 1) {
                                throw new DukeException("OOPS!!! Please enter a due date. e.g complete homework /by 1 Jan");
                            } else {
                                list[index] = new Deadline(deadline[0], deadline[1]);
                                System.out.println("Got it. I've added this task:\n" + list[index]);
                                System.out.println("Now you have " + index + " tasks in the list");
                                index++;
                            }
                        }
                        break;
                    case "event":
                        if (arr.length == 1) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String[] event = arr[1].split("/at ", 2);
                            if (event.length == 1) {
                                throw new DukeException("OOPS!!! Please enter an event date. e.g group meeting /at 1 Jan");
                            } else {
                                list[index] = new Event(event[0], event[1]);
                                System.out.println("Got it. I've added this task:\n" + list[index]);
                                System.out.println("Now you have " + index + " tasks in the list");
                                index++;
                            }
                        }
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            }
            //Taking in the next line
            input = scan.nextLine();
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
