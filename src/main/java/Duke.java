import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> lst = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        while (!word.equals("bye")) {
            try {
                if (word.equals("list")) {
                    int counter = 1;
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : lst) {
                        System.out.println(counter + ". " + task);
                        counter++;
                    }
//                } else if (word.startsWith("delete ")) {
//                    String[] arr = word.split(" ");
//                    int i = Integer.parseInt(arr[1]);
//                    if (i > lst.size()) {
//                        throw new DukeException("Number can't be bigger than list size.");
//                    } else if (i < 1) {
//                        throw new DukeException("Number must be greater than 0");
//                    }
//                    Task task = lst.remove(i - 1);
//                    System.out.println("Noted. I've removed this task: : ");
//                    System.out.println("\t" + task);
//                    System.out.println("Now you have " +  lst.size()  +  " tasks in the list.");
                } else if (word.startsWith("done ")) {
                    String[] arr = word.split(" ");
                    int i = Integer.parseInt(arr[1]);
                    if (i > lst.size()) {
                        throw new DukeException("Number can't be bigger than list size.");
                    } else if (i < 1) {
                        throw new DukeException("Number must be greater than 0");
                    }
                    Task task = lst.get(i - 1);
                    lst.get(i - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("\t" + task);
                } else {
                    String[] arr = word.split("/");
                    String[] arr2 = arr[0].split(" ", 2);

                    Task task;
                    if (arr2[0].equals("todo")) {
                        if (arr2.length < 2  || arr2[1].trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Todo(arr2[1], "");
                        lst.add(task);
                    } else  if (arr2[0].equals("deadline")) {
                        if (arr2.length < 2 || arr2[1].trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        if (arr.length < 2 || arr[1].trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty, add a /by description!");
                        }
                        if (!arr[1].startsWith("by ")) {
                            throw new DukeException("Use /by  ");
                        }
                        task = new Deadline(arr2[1], arr[1]);
                        lst.add(task);
                    } else if (arr2[0].equals("event")){
                        if (arr2.length < 2 || arr2[1].trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        if (arr.length < 2 || arr[1].trim().equals("")) {
                            throw new DukeException("☹ OOPS!!! The time of a deadline cannot be empty, add a /at description!");
                        }
                        if (!arr[1].startsWith("at ")) {
                            throw new DukeException("Use /at  ");
                        }
                        task = new Event(arr2[1], arr[1]);
                        lst.add(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println("\t" + task.toString());
                    System.out.println("Now you have " +  lst.size()  +  " tasks in the list.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Done must be followed by an integer");
            } finally {
                word = sc.nextLine();
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
