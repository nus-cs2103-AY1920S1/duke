import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;

public class Duke {
    public static ArrayList<Task> items = new ArrayList<>();
    public static int counter = 0;
    public static String filePath = "../data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String hello = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(hello);

        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                try {
                    handleInput(next);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    public static void save() {
        try {
            File data = new File(filePath);
            if (!data.getParentFile().exists()) {
                data.getParentFile().mkdirs();
            }

            if (!data.exists()) {
                data.createNewFile();
            }

            FileWriter fw = new FileWriter(data, false);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < items.size(); i++) {
                Task task = items.get(i);
                sb.append(task.getFileLine());
                sb.append('\n');
            }

            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Date handleDateTime(String input) {
        Date date = new Date();

        try {
            date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return date;
    }

    public static void handleInput(String next) throws DukeException {
        if (next.equals("list")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < counter; i++) {
                sb.append(i + 1);
                sb.append(".");
                sb.append(items.get(i));
                sb.append('\n');
            }
            System.out.print(sb);
        } else if (next.startsWith("done")) {
            String[] parts = next.split(" ");
            int num = Integer.parseInt(parts[1]);
            Task task = items.get(num - 1);
            task.setDone(true);
            String output = "Nice! I've marked this task as done:\n  " + task;
            System.out.println(output);
            save();
        } else if (next.startsWith("delete")) {
            String[] parts = next.split(" ");
            int num = Integer.parseInt(parts[1]);
            Task removed = items.remove(num - 1);
            counter--;
            String output = "Noted. I've removed this task:\n  " + removed;
            output += "\nNow you have " + counter + " tasks in the list.";
            System.out.println(output);
            save();
        } else {
            if (next.startsWith("todo")) {
                if (next.length() < 6) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    items.add(new Todo(next.substring(5)));
                }
            } else if (next.startsWith("deadline")) {
                String[] parts = next.split(" /by ");
                items.add(new Deadline(parts[0].substring(9), handleDateTime(parts[1])));
            } else if (next.startsWith("event")) {
                String[] parts = next.split(" /at ");
                String[] fromTo = parts[1].split(" - ");
                items.add(new Event(parts[0].substring(6), handleDateTime(fromTo[0]), handleDateTime(fromTo[1])));
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            String output = "Got it. I've added this task:\n  " + items.get(counter);
            output += "\nNow you have " + (counter + 1) + " tasks in the list.";
            System.out.println(output);
            counter++;
            save();
        }
    }
}
