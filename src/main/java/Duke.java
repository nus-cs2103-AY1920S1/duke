import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The duke.Duke program implements an app that enables users to track their tasks.
 *
 * @author Yen-Peng
 */

public class Duke {

    public static void main(String[] args) throws DukeException, IOException {

        String greet = "____________________________________________________________\n"
                + "Hello! My name is Smart Baby~\n"
                + "(๑★ᴗ★๑) What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greet);

        List<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                checkFile();
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String userInput = bufferRead.readLine();
                File taskListText = new File("TaskList.txt");
                BufferedReader tasksFile = new BufferedReader(new FileReader(taskListText));

                switch (userInput) {
                case "list":
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        assert tasks.get(i) != null;
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________\n");
                    break;
                case "bye":
                    System.out.println("____________________________________________________________\n"
                            + "Zzz...sleeping time! ~u~\n"
                            + "____________________________________________________________\n");
                    System.exit(0);
                    break;

                default:
                    String[] segments = userInput.split(" ", 2);
                    checkErrors(segments);
                    String word = segments[0];
                    String rest = segments[1];
                    String second = userInput.split(" ")[1];

                    switch (word) {
                    case "done":
                        int selectedToBeDone = Integer.parseInt(second);
                        tasks.get(selectedToBeDone - 1).markAsDone(tasks.get(selectedToBeDone - 1));
                        System.out.println("____________________________________________________________\n"
                                + "Nice! I've marked this task as done:\n"
                                + tasks.get(selectedToBeDone - 1) + "\n"
                                + "____________________________________________________________\n");
                        break;
                    case "todo": {
                        Todo t = new Todo(rest);
                        tasks.add(t);

                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1) + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                        break;
                    }
                    case "deadline": {
                        String[] parts = rest.split("/by");
                        String part1 = parts[0];
                        String part2 = parts[1];
                        Deadline d = new Deadline(parts[0], parts[1]);
                        tasks.add(d);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1) + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                        break;
                    }
                    case "event": {
                        String[] parts = rest.split("/at");
                        String part1 = parts[0];
                        String part2 = parts[1];
                        Event e = new Event(parts[0], parts[1]);
                        tasks.add(e);
                        System.out.println("____________________________________________________________\n"
                                + "Got it. I've added this task:\n"
                                + tasks.get(tasks.size() - 1) + "\n"
                                + "Now you have " + tasks.size() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                        break;
                    }
                    default:
                        throw new DukeException("____________________________________________________________\n"
                                + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                                + "____________________________________________________________\n");
                    }
                }

                try (PrintStream out = new PrintStream(new FileOutputStream(taskListText))) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        if (t.getType().equals("todo")) {
                            out.print(t.getSymbol() + " | " + t.getBoolean() + " | " + t.getDescription() + "\n");
                        } else {
                            out.print(t.getSymbol() + " | " + t.getBoolean() + " | " + t.getDescription() + "|" + t.getDateString() + "\n");
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }

    }

    private static void checkFile() throws IOException {
        File tmpDir = new File("TaskList.txt");
        if (!tmpDir.exists()) {
            tmpDir.createNewFile();
        }
    }

    public static void checkErrors(String[] segments) throws DukeException {
        if (segments.length == 1 && segments[0].equals("")) {
            throw new DukeException("____________________________________________________________\n"
                    + "( ͡° ͜ʖ ͡°) Ahh I can't read your mind yet, say something.\n"
                    + "____________________________________________________________\n");
        } else if (segments.length == 1 && segments[0].equals("done")) {
            throw new DukeException("____________________________________________________________\n"
                    + "I've done nothing productive all day...you too? (⊃◜⌓◝⊂)\n"
                    + "____________________________________________________________\n");
        } else if (segments.length == 1 && segments[0].equals("delete")) {
            throw new DukeException("____________________________________________________________\n"
                    + "One does not simply delete nothing. (￣ ︶ ￣;)\n"
                    + "____________________________________________________________\n");
        } else if (segments.length == 2 && segments[1].equals("")) {
            throw new DukeException("____________________________________________________________\n"
                    + "☹ OOPS!!! The description of a " + segments[0] + " cannot be empty.\n"
                    + "____________________________________________________________\n");
        } else if (segments.length == 1 && Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> segments[0].equals(s))) {
            throw new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! The description of a " + segments[0] + " cannot be empty.\n" +
                    "____________________________________________________________\n");
        } else if (segments.length == 1) {
            throw new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "____________________________________________________________\n");
        } else {
        }
    }
}