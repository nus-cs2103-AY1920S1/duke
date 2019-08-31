import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Duke {
    private static Task[] Task;

    public static void main(String[] args) throws DukeException {

        String greet = "____________________________________________________________\n"
                + "こんにちは! My name is Smart Baby~\n"
                + "(๑★ᴗ★๑) What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(greet);

        List<Task> tasks = new ArrayList<>();

        while(true) {
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String userInput = bufferRead.readLine();

                switch (userInput) {
                    case "list":
                        System.out.println("____________________________________________________________\n");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            assert tasks.get(i) != null;
                            System.out.println((i+1) + "." + tasks.get(i).toString());
                        }
                        System.out.println("____________________________________________________________\n");
                        break;
                    case "bye":
                        System.out.println("____________________________________________________________\n" +
                                "Zzz...sleeping time! ~~\n" +
                                "____________________________________________________________\n");
                        System.exit(0);
                        break;

                    default:
                        try {
                            String[] segments = userInput.split(" ", 2);
                            checkErrors(segments);
                            String word = segments[0];
                            String rest = segments[1];
                            String second = userInput.split(" ")[1];

                            switch (word) {
                                case "done":
                                    int selectedToBeDone = Integer.parseInt(second);
                                    tasks.get(selectedToBeDone - 1).markAsDone(tasks.get(selectedToBeDone - 1));
                                    System.out.println("____________________________________________________________\n" +
                                            "Nice! I've marked this task as done: \n" +
                                            tasks.get(selectedToBeDone - 1) + "\n" +
                                            "____________________________________________________________\n");
                                    break;
                                case "todo": {
                                    Todo t = new Todo(rest);
                                    tasks.add(t);

                                    System.out.println("____________________________________________________________\n" +
                                            "Got it. I've added this task:\n" +
                                            tasks.get(tasks.size() - 1) + "\n" +
                                            "Now you have " + tasks.size() + " tasks in the list.\n" +
                                            "____________________________________________________________\n");
                                    break;
                                }
                                case "deadline": {
                                    String[] parts = rest.split("/by");
                                    String part1 = parts[0];
                                    String part2 = parts[1];
                                    Deadline d = new Deadline(parts[0], parts[1]);
                                    tasks.add(d);
                                    System.out.println("____________________________________________________________\n" +
                                            "Got it. I've added this task:\n" +
                                            tasks.get(tasks.size() - 1) + "\n" +
                                            "Now you have " + tasks.size() + " tasks in the list.\n" +
                                            "____________________________________________________________\n");
                                    break;
                                }
                                case "event": {
                                    String[] parts = rest.split("/at");
                                    String part1 = parts[0];
                                    String part2 = parts[1];
                                    Event e = new Event(parts[0], parts[1]);
                                    tasks.add(e);
                                    System.out.println("____________________________________________________________\n" +
                                            "Got it. I've added this task:\n" +
                                            tasks.get(tasks.size() - 1) + "\n" +
                                            "Now you have " + tasks.size() + " tasks in the list.\n" +
                                            "____________________________________________________________\n");
                                    break;
                                }
                                case "delete": {
                                    int selectedToBeDeleted = Integer.parseInt(second);
                                    System.out.println("____________________________________________________________\n" +
                                            "Noted. I've removed this task:\n" +
                                            tasks.get(selectedToBeDeleted - 1));
                                    tasks.remove(selectedToBeDeleted - 1);
                                    System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" +
                                            "____________________________________________________________\n");
                                    break;
                                }
                                default:
                                    throw new DukeException("____________________________________________________________\n" +
                                            "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                            "____________________________________________________________\n");

                            }
                        }

                        catch (DukeException ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void checkErrors(String[] segments) throws DukeException {

        if (segments.length == 1 && segments[0].equals("")) {
            throw new DukeException("____________________________________________________________\n" +
                    "( ͡° ͜ʖ ͡°) Ahhhh my technology can't read your minds yet, say something.\n" +
                    "____________________________________________________________\n");
        }

        else if (segments.length == 1 && segments[0].equals("done")) {
            throw new DukeException("____________________________________________________________\n" +
                    "I've done nothing all day...you too? (⊃◜⌓◝⊂)\n" +
                    "____________________________________________________________\n");
        }

        else if (segments.length == 1 && segments[0].equals("delete")) {
            throw new DukeException("____________________________________________________________\n" +
                    "One does not simply delete nothing (￣ ︶ ￣;)\n" +
                    "____________________________________________________________\n");
        }

        else if (segments.length == 2 && segments[1].equals("")) {
            throw new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! The description of " + segments[0] + " cannot be empty.\n" +
                    "____________________________________________________________\n");
        }

        else if (segments.length == 1 && Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> segments[0].equals(s))) {
            throw new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! The description of " + segments[0] + " cannot be empty.\n" +
                    "____________________________________________________________\n");
        }

        else if (segments.length == 1) {
            throw new DukeException("____________________________________________________________\n" +
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "____________________________________________________________\n");
        }

    }
}