import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    public static void updateList(ArrayList<Task> list) throws FileNotFoundException {
        File file = new File("C:\\duke\\src\\main\\java\\data\\duke.txt");
        PrintWriter out = new PrintWriter(file);
        for (int i = 1; i <= list.size(); i++) {
            Task t = list.get(i-1);
            if (t.symbol.equals("T")) {
                out.println( t.symbol + " / " + (t.isDone? 1 : 0) + " / " + t.getDescription());
            } else {
                out.println( t.symbol + " / " + (t.isDone? 1 : 0) + " / " + t.getDescription() + " / " + t.getExtraInfo());
            }
        }
        out.close();

    }
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File("C:\\duke\\src\\main\\java\\data\\duke.txt");
        Scanner sc1 = new Scanner(file);

        while (sc1.hasNextLine()) {
            String[] oldList = sc1.nextLine().split(" / ");
            //System.out.println(Arrays.toString(oldList));
            Task t;
            if (oldList[0].trim().equals("T")) {
                t = new Todo(oldList[2].trim());
            } else {
                if (oldList[0].trim().equals("D")) {
                    t = new Deadline(oldList[2].trim(), oldList[3].trim());
                } else {
                    t = new Event(oldList[2].trim(), oldList[3].trim());
                }
            }
            if (oldList[1].trim().equals("1")) {
                t.markAsDone();;
            }
            list.add(t);
        }
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task t = list.get(i-1);
                    System.out.println( i + "." + t);
                }
            } else if (input.equals("done")) {
                int num = Integer.parseInt(sc.next());
                Task t = list.get(num - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: "+ "\n" + t);
            } else if (input.equals("delete")) {
            	int num = Integer.parseInt(sc.next());
            	Task t = list.remove(num);
            	System.out.println("Noted. I've removed this task: \n" + t + "\nNow you have "
                            + list.size() + " tasks in the list.");
            } else {
                try {
                    Task t;
                    String desc = sc.nextLine();
                    if (desc.equals("")) {
                        throw new DukeException("The description of a " + input+ " cannot be empty.");
                    }
                    if (input.equals("todo")) {
                        t = new Todo(desc);
                    } else if (input.equals("deadline")) {
                        String[] str = desc.split("/");
                        t = new Deadline(str[0], str[1].substring(3));
                    } else if (input.equals("event")) {
                        String[] str = desc.split("/");
                        t = new Event(str[0], str[1].substring(3));
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    list.add(t);
                    System.out.println("Got it. I've added this task: \n" + t + "\nNow you have "
                            + list.size() + " tasks in the list.");

                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
            }
            updateList(list);
        }

    }
}
