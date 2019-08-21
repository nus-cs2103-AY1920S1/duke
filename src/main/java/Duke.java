import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String text = sc.nextLine();

        while (!text.equals("bye")) {
            try {
                String firstWord = text.split(" ")[0];
                if (firstWord.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    IntStream.rangeClosed(1, list.size()).forEach(x -> {
                        Task task = list.get(x - 1);
                        System.out.println(x + "." + task.toString());
                    });
                } else if (firstWord.equals("done")) {
                    String[] words = text.split(" ");
                    if (words.length != 2) {
                        throw new DukeException("You need to specify a task that is done.");
                    }
                    if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                        throw new DukeException("Task does not exist.");
                    }
                    Task task = list.get(Integer.parseInt(words[1]) - 1);
                    task.setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task.toString());
                } else if (firstWord.equals("delete")) {
                    String[] words = text.split(" ");
                    if (words.length != 2) {
                        throw new DukeException("You need to specify a task that is done.");
                    }
                    if (Integer.parseInt(words[1]) > list.size() || Integer.parseInt(words[1]) <= 0) {
                        throw new DukeException("Task does not exist.");
                    }
                    Task removed = list.remove(Integer.parseInt(words[1]) -1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removed.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } else {
                    Task task;
                    if (firstWord.equals("todo")) {
                        if (text.split(" ").length < 2 || text.split(" ")[1].equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDo(text.split(" ", 2)[1]);
                    } else if (firstWord.equals("deadline")) {
                        if (text.split(" ").length < 2 || text.split(" ")[1].equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String description = text.split(" ", 2)[1].split(" /", 2)[0];
                        String by = text.split(" ", 2)[1].split(" /by ", 2)[1];
                        task = new Deadline(description, by);
                    } else if (firstWord.equals("event")) {
                        if (text.split(" ").length < 2 || text.split(" ")[1].equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String description = text.split(" ", 2)[1].split(" /", 2)[0];
                        String at = text.split(" ", 2)[1].split(" /at ", 2)[1];
                        task = new Event(description, at);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Input must be an integer.");
            } catch (Exception e) {
                System.err.println("Something is wrong");
            } finally {
                text = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
