import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Duke {
    List<Task> list = new ArrayList<>();
    DateTimeFormatter f;
    LocalDateTime dateTime;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String indent = "     ";
    String input;
    String[] inputs;
    String[] inputFormatted;
    Task task;
    int output;
    int i;
    private String[] startEndDate;

    public static void main(String[] args) {
        Duke newDuke = new Duke();
        newDuke.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        f = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
        Output.printIntro();
        do {
            input = sc.nextLine();
            Output.line();
            try {
                output = processInput(input);
            } catch (DukeException e) {
                System.out.println(indent + e.getMessage());
            }
            Output.line();
            System.out.println();
        } while (output != 1);
    }

    private int processInput(String input) throws DukeException {
        inputs = input.split(" ", 2);
        // Exit program
        switch (inputs[0]) {
        case "bye":
            Output.byeMsg();
            return 1;
        case "list":
            Output.printList(list);
            break;
        case "done":
        case "delete":
            if (inputs.length == 2) {
                if (inputs[1].matches("(0|[1-9]\\d*)")) {
                    i = Integer.parseInt(inputs[1]);
                    if (i <= list.size()) {
                        switch (inputs[0]) {
                        case "done":
                            task = list.get(i - 1);
                            task.setDone();
                            Output.doneMsg();
                            Output.printTask(task);
                            break;
                        case "delete":
                            list.remove(i - 1);
                            Output.delMsg();
                            Output.printTask(task);
                            Output.printListSize(list);
                            break;
                        }
                    } else throw new DukeException("☹ OOPS!!! There is no task at index " + i + ".");
                } else throw new DukeException("☹ OOPS!!! The index of " + inputs[0] + " operation must be a positive integer.");
            } else throw new DukeException("☹ OOPS!!! The index of " + inputs[0] + " operation cannot be empty.");
            break;
        case "todo":
        case "deadline":
        case "event":
            if (inputs.length > 1) {
                switch (inputs[0]) {
                case "todo":
                    task = new Todo(inputs[1]);
                    break;
                case "deadline":
                    if (inputs[1].contains(" /by ")) {
                        inputFormatted = inputs[1].split(" /by ", 2);
                        try {
                            dateTime = LocalDateTime.parse(inputFormatted[1], f);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("/u2754 OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
                        }
                        task = new Deadline(inputFormatted[0], dateTime);
                    } else {
                        throw new DukeException("☹ OOPS!!! The due date of a deadline cannot be empty.");
                    }
                    break;
                case "event":
                    if (inputs[1].contains(" /at ")) {
                        inputFormatted = inputs[1].split(" /at ", 2);
                        startEndDate = inputFormatted[1].split(" ");
                        if (startEndDate.length < 4) throw new DukeException("/u2754 OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
                        try {
                            startDate = LocalDateTime.parse(startEndDate[0] + " " + startEndDate[1], f);
                            endDate = LocalDateTime.parse(startEndDate[2] + " " + startEndDate[3], f);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("/u2754 OOPS!!! The date inputted is not in 'DD/MM/YYYY HHmm' format");
                        }
                        task = new Event(inputFormatted[0], startDate, endDate);
                    } else {
                        throw new DukeException("☹ OOPS!!! The duration of a event cannot be empty.");
                    }
                    break;
                }
            } else throw new DukeException("☹ OOPS!!! The description of a " + inputs[0] + " cannot be empty.");
            list.add(task);
            Output.addMsg();
            Output.printTask(task);
            Output.printListSize(list);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return 0;
    }
}
