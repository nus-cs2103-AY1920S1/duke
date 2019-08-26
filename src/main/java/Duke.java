import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);

        FileInputStream fis = new FileInputStream("data/tasks.tmp");
        ObjectInputStream ois = new ObjectInputStream(fis);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String s;
        List<Task> ls = new ArrayList<>();

        try {
            ls = (List<Task>) ois.readObject();
        } catch (EOFException e) {
            e.printStackTrace();
        }
        ois.close();

        Task.updateSize(ls.size());

        while (!(s = sc.nextLine()).equals("bye")) {
            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= ls.size(); i++) {
                    System.out.println(String.format("%d. %s", i, ls.get(i - 1)));
                }
            } else if (s.startsWith("done ")) {
                int index = Integer.parseInt(s.substring(5)) - 1;
                ls.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(ls.get(index));
            } else if (s.startsWith("delete ")) {
                int index = Integer.parseInt(s.substring(7)) - 1;
                Task deletedTask = ls.remove(index);
                Task.decrementNumber();
                System.out.println("Noted. I've removed this task:");
                System.out.println(deletedTask);
                System.out.println(String.format("Now you have %d tasks in the list.", Task.getNumberOfTasks()));
            } else {
                try {
                    Task newTask;
                    if (s.equals("deadline") || s.equals("event") || s.equals("todo")) {
                        throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + s + " cannot be empty.");
                    } else if (s.startsWith("deadline")) {
                        String[] phrases = s.substring(9).split(" /by ");
                        newTask = new Deadline(phrases[0], phrases[1]);
                    } else if (s.startsWith("event")) {
                        String[] phrases = s.substring(6).split(" /at ");
                        newTask = new Event(phrases[0], phrases[1]);
                    } else if (s.startsWith("todo")) {
                        newTask = new Todo(s.substring(5));
                    } else {
                        throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    ls.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println(String.format("Now you have %d tasks in the list.", Task.getNumberOfTasks()));
                } catch (DukeException ex) {
                    System.err.println(ex);
                }
            }

            FileOutputStream fos = new FileOutputStream("data/tasks.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ls);
            oos.close();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}