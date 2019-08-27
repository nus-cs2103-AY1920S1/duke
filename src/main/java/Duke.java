import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> commandList = new ArrayList<Task>();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                if (input.length() <= 3) {
                    throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    if (input.equals("list")) {
                        System.out.println("Here are the tasks in your list:");
                        int size = commandList.size();
                        for (int i = 1; i < size + 1; i++) {
                            System.out.print(i + ".");
                            System.out.println(commandList.get(i - 1));
                        }
                    } else if (input.substring(0, 4).equals("done")) {
                        String[] arr = input.split(" ");
                        int number = Integer.parseInt(arr[1]);
                        if (number > commandList.size() + 1) {
                            throw new TaskNotFoundException("OOPS!!! Task number is incorrect");
                        }
                        commandList.get(number - 1).complete();
                    } else {
                        if (input.substring(0, 4).equals("todo")) {
                            if (input.length() == 4) {
                                throw new TodoEmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                            }
                            ToDo todo = new ToDo(input.substring(5));
                            commandList.add(todo);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(todo);
                        } else if (input.length() <= 5) {
                            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        } else if (input.substring(0, 5).equals("event")) {
                            String eventDetails = input.substring(6);
                            String[] arr = eventDetails.split("/");
                            try {
                                Event event = new Event(arr[0], arr[1]);

                                if (arr[1].length() == 0) {
                                    throw new EventDetailsEmptyException("OOPS!!! Event details cannot be empty.");
                                }
                                commandList.add(event);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(event);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Input format is wrong.");
                            }
                        } else if (input.substring(0, 6).equals("delete")) {
                            String[] arr = input.split(" ");
                            int number = Integer.parseInt(arr[1]);
                            if (number > commandList.size() + 1) {
                                throw new TaskNotFoundException("OOPS!!! Task number is incorrect");
                            }
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(commandList.get(number - 1));
                            commandList.remove(number - 1);
                        } else if (input.length() <= 7) {
                            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        } else if (input.substring(0, 8).equals("deadline")) {
                            String deadlineDetails = input.substring(9);
                            String[] arr = deadlineDetails.split("/");
                            try {
                                Deadline deadline = new Deadline(arr[0], arr[1]);
                                if (arr[1].length() == 0) {
                                    throw new DeadlineDetailsEmptyException("OOPS!!! Deadline details cannot be empty");
                                }
                                commandList.add(deadline);
                                System.out.println("Got it. I've added this task:");
                                System.out.println(deadline);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Input format is wrong.");
                            }
                        } else {
                            throw new IncorrectInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        int numberOfTasks = commandList.size();
                        if (numberOfTasks == 1) {
                            System.out.println("Now you have " + numberOfTasks + " task in the list");
                        } else
                            System.out.println("Now you have " + numberOfTasks + " tasks in the list");
                    }
                    input = sc.nextLine();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
                //reads the next input
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
