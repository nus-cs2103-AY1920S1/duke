import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

import static java.lang.String.format;

/**
 * Duke class.
 */
public class Duke {
    private String line;

    /**
     * duke exception printer.
     * @param e exception input.
     */
    private void printDukeException(DukeException e) {
        System.out.println(this.line + format("\t %s\n", e) + this.line);
    }

    /**
     * printing the given input to the interface.
     * @param args iteration of string inputs that each represent a line to be printed.
     */
    private void printStatement(String... args) {
        String content = format("%s", Stream.of(args).map(s -> "\t " + s + "\n")
                .reduce((x,y) -> x + y).orElse(""));
        System.out.println(this.line + content + this.line);
    }

    private void updateData(ArrayList<Task> list, String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task task : list) {
            String data = String.format("%s\n", task.fileFormat());
            fw.write(data);
        }
        fw.close();
    }

    /**
     * Initially populates the Task List with the hard drive data (Input data file).
     * @param list ArrayList to be populated.
     * @param line A given line in the input file.
     */
    private void initialPopulate(ArrayList<Task> list, String line) {
        try {
            String[] arr = line.split(" \\| ", 4);
            String cmd = arr[0];
            boolean isDone = Integer.parseInt(arr[1]) != 0;
            switch (cmd) {
                case "T":
                    Task todo = new Todo(arr[2]);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;
                case "D":
                    String datetime[] = arr[3].split(" ", 2);
                    Task deadline = new Deadline(arr[2], datetime[0], datetime[1]);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Task event = new Event(arr[2], arr[3]);
                    if (isDone) {
                        event.markAsDone();
                    }
                    list.add(event);
                    break;
                default:
                    throw new DukeException("Invalid Task Type");
            }
        } catch (DukeException e) {
            printStatement(e.toString());
        }
    }

    private void run(String path) {
        this.line = "\t____________________________________________________________\n";
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(path);
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    throw new DukeException("Data file not created");
                }
            }
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {
                /*
                T | 1 | read book
                D | 0 | return book | June 6th
                E | 0 | project meeting | Aug 6th 2-4pm
                T | 1 | join sports club
                 */
                String line = fileReader.nextLine();
                initialPopulate(list, line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            printStatement(e.toString());
            return;
        } catch (IOException io) {
            printStatement(io.toString());
            return;
        } catch (DukeException duke) {
            printStatement(duke.toString());
            return;
        }

        Scanner sc = new Scanner(System.in);
        printStatement("Hello! I'm Duke", "What can I do for you?");
        while (!sc.hasNext("bye")) {
            String s = sc.nextLine();
            String[] arr = s.split(" ", 2);
            String cmd = arr[0];
            try {
                switch (cmd) {
                    case "todo":
                        try {
                            if (arr.length < 2) {
                                throw new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                            }
                            String toDoContent = arr[1];
                            Task todo = new Todo(toDoContent);
                            list.add(todo);
                            printStatement("Got it. I've added this task:",
                                    format("  %s", todo),
                                    format("Now you have %d tasks in the list.", list.size()));
                            updateData(list, path);
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
                            String datetime[] = deadline_content[1].split(" ", 2);
                            if (deadline_content.length < 2 || datetime.length < 2) {
                                throw new DukeException("Invalid format for Deadline Task.");
                            }
                            try {
                                Task deadline = new Deadline(deadline_content[0], datetime[0], datetime[1]);
                                list.add(deadline);
                                printStatement("got it. i've added this task:",
                                        String.format("  %s", deadline),
                                        String.format("Now you have %d tasks in the list.", list.size()));
                            } catch (DateTimeParseException e) {
                                printDukeException(new DukeException("Invalid Date Time format input"));
                            }
                            updateData(list, path);
                        } catch (DukeException e) {
                            printDukeException(e);
                        }
                        break;
                    case "event":
                        try {
                            String[] eventContent = arr[1].split(" /at ", 2);
                            if (eventContent.length < 2) {
                                throw new DukeException("Invalid format for Event Task.");
                            }
                            Task event = new Event(eventContent[0], eventContent[1]);
                            list.add(event);
                            printStatement("got it. i've added this task:",
                                    format("  %s", event),
                                    format("Now you have %d tasks in the list.", list.size()));
                            updateData(list, path);
                        } catch (DukeException e) {
                            printDukeException(e);
                        }
                        break;
                    case "done":
                        try {
                            if (arr.length < 2) {
                                throw new DukeException("An Integer is required to choose the task.");
                            }
                            int index = Integer.parseInt(arr[1]);
                            if (index < 1 || index > list.size()) {
                                throw new DukeException("Index out of range.");
                            }
                            Task task = list.get(index - 1);
                            task.markAsDone();
                            printStatement("Nice! I've marked this task as done:",
                                    format("  %s", task.toString()));
                            updateData(list, path);
                        } catch (DukeException e) {
                            printDukeException(e);
                        }
                        break;
                    case "list":
                        Stream<String> taskStream = IntStream
                                .range(0, list.size())
                                .mapToObj(i -> {
                                    Task t = list.get(i);
                                    return format("%d.%s", i + 1, t.toString());
                                });
                        Stream<String> combined = Stream.concat(Stream.of("Here are the tasks in your list:"), taskStream);
                        String[] combinedString = combined.toArray(String[]::new);
                        printStatement(combinedString);
                        break;
                    case "delete":
                        try {
                            if (arr.length < 2) {
                                throw new DukeException("An Integer is required to delete the task.");
                            }
                            int index = Integer.parseInt(arr[1]);
                            if (index < 1 || index > list.size()) {
                                throw new DukeException("Index out of range.");
                            }
                            Task task = list.remove(index - 1);
                            printStatement("Noted. I've removed this task:",
                                    format("  %s", task.toString()),
                                    format("Now you have %d tasks in the list.", list.size()));
                            updateData(list, path);
                        } catch (DukeException e) {
                            printDukeException(e);
                        }
                        break;
                    default:
                        printStatement(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (IOException e) {
                printStatement(e.toString());
            }
        }
        printStatement("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run("./data/list.txt");
    }
}
