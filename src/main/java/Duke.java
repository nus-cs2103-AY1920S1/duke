import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;

public class Duke {
    private String line;

    private void printDukeException(DukeException e) {
        System.out.println(this.line + String.format("\t %s\n", e) + this.line);
    }

    private void printStatement(String... args) {
        String content = String.format("%s", Stream.of(args).map(s -> "\t " + s + "\n")
        .reduce((x,y) -> x + y).orElse(""));
        System.out.println(this.line + content + this.line);
    }

    private void run() {
        this.line = "\t____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        printStatement("Hello! I'm Duke", "What can I do for you?");
        while (!sc.hasNext("bye")) {
            String s = sc.nextLine();
            String arr[] = s.split(" ", 2);
            String cmd = arr[0];
            switch (cmd) {
                case "todo":
                    try {
                        if (arr.length < 2) {
                            throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                        }
                        String todo_content = arr[1];
                        Task todo = new Todo(todo_content);
                        list.add(todo);
                        printStatement("Got it. I've added this task:",
                                String.format("  %s", todo),
                                String.format("Now you have %d tasks in the list.", list.size()));
                    } catch (DukeException e) {
                        printDukeException(e);
                    }
                    break;
                case "deadline":
                    try {
                        if (arr.length < 2) {
                            throw new DukeException("Invalid format for Deadline Task.");
                        }
                        String deadline_content[] = arr[1].split(" /by ", 2);
                        if (deadline_content.length < 2) {
                            throw new DukeException("Invalid format for Deadline Task.");
                        }
                        Task deadline = new Deadline(deadline_content[0], deadline_content[1]);
                        list.add(deadline);
                        printStatement("got it. i've added this task:",
                                String.format("  %s", deadline),
                                String.format("Now you have %d tasks in the list.", list.size()));
                    } catch (DukeException e) {
                        printDukeException(e);
                    }
                    break;
                case "event":
                    try {
                        String event_content[] = arr[1].split(" /at ", 2);
                        if (event_content.length < 2) {
                            throw new DukeException("Invalid format for Event Task.");
                        }
                        Task event = new Event(event_content[0], event_content[1]);
                        list.add(event);
                        printStatement("got it. i've added this task:",
                                String.format("  %s", event),
                                String.format("Now you have %d tasks in the list.", list.size()));
                    } catch (DukeException e) {
                        printDukeException(e);
                    }
                    break;
                case "done":
                    try {
                        if (arr.length < 2) {
                            throw new DukeException("An Integer is required to choose the task.");
                        }
                        int index = Integer.valueOf(arr[1]);
                        if (index < 1 || index > list.size()) {
                            throw new DukeException("Index out of range.");
                        }
                        Task task = list.get(index - 1);
                        task.markAsDone();
                        printStatement("Nice! I've marked this task as done:",
                                String.format("  %s", task.toString()));
                    } catch (DukeException e) {
                        printDukeException(e);
                    }
                    break;
                case "list":
                    Stream<String> taskstream = IntStream
                            .range(0, list.size())
                            .mapToObj(i -> {
                                Task t = list.get(i);
                                return String.format("%d.%s", i + 1, t.toString());
                            });
                    Stream<String> combined = Stream.concat(Stream.of("Here are the tasks in your list:"), taskstream);
                    String combinedString[] = combined.toArray(String[]::new);
                    printStatement(combinedString);
                    break;
                case "delete":
                    try {
                        if (arr.length < 2) {
                            throw new DukeException("An Integer is required to delete the task.");
                        }
                        int index = Integer.valueOf(arr[1]);
                        if (index < 1 || index > list.size()) {
                            throw new DukeException("Index out of range.");
                        }
                        Task task = list.remove(index - 1);
                        printStatement("Noted. I've removed this task:",
                                String.format("  %s", task.toString()),
                                String.format("Now you have %d tasks in the list.", list.size()));
                    } catch (DukeException e) {
                        printDukeException(e);
                    }
                    break;
                default:
                    printStatement(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        printStatement("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}
