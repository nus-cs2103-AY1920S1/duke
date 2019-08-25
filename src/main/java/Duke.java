import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> list;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.runDuke();
    }

    private Duke() {
        this.list = new ArrayList<>();
    }

    private void runDuke() {
        try {
            loadTodoList();
        } catch (DukeException e) {
            printMessage(e.getMessage());
        }

        printGreetingMessage();

        boolean isDone = false;
        while (!isDone) {
            String input = sc.nextLine().trim();

            try {
                if (isExitCommand(input)) {
                    isDone = true;
                } else if (isListCommand(input)) {
                    printList();
                } else if (isDoneCommand(input)) {
                    String newInput = input.replaceFirst("done", "").trim();
                    int oneBasedIndex = validateDoneOrDeleteIndex(newInput);
                    markTaskAsDone(oneBasedIndex);
                } else if (isAddTodoCommand(input)) {
                    String todo = input.replaceFirst("todo", "").trim();
                    validateTodo(todo);
                    addTodoToList(todo);
                } else if (isAddDeadlineCommand(input)) {
                    String[] deadline = validateEventOrDeadline(input, "deadline", "/by");
                    if (isDate(deadline[1])) {
                        Date deadlineDate = parseDate(deadline[1]);
                        addDeadlineToList(deadline[0], deadlineDate);
                    } else {
                        addDeadlineToList(deadline[0], deadline[1]);
                    }
                } else if (isAddEventCommand(input)) {
                    String[] event = validateEventOrDeadline(input, "event", "/at");
                    if (isDate(event[1])) {
                        Date eventDate = parseDate(event[1]);
                        addEventToList(event[0], eventDate);
                    } else {
                        addEventToList(event[0], event[1]);
                    }
                } else if (isDeleteCommand(input)) {
                    String newInput = input.replaceFirst("delete", "").trim();
                    int oneBasedIndex = validateDoneOrDeleteIndex(newInput);
                    deleteTask(oneBasedIndex);
                } else {
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                saveTodoList();
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }

        printExitMessage();
    }

    private Date parseDate(String date) throws DukeException {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    private boolean isDate(String input) {
        // Assume date is in the format 2/12/2019 1800
        String[] splitInput = input.split("/");
        if (splitInput.length != 3 || !isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2 || !isNumeric(yearAndTime[0]) || !isNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    private void loadTodoList() throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String input;
            while ((input = br.readLine()) != null) {
                String[] splitInput = input.split(" \\| ");

                switch (splitInput[0]) {
                case "T":
                    Todo todo = new Todo(splitInput[2]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitInput[2], splitInput[3]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    break;
                case "E":
                    Event event = new Event(splitInput[2], splitInput[3]);
                    if (Integer.parseInt(splitInput[1]) == 1) {
                        event.markAsDone();
                    }
                    list.add(event);
                    break;
                default:
                    throw new DukeException("An error occurred during file parsing, unexpected task type was encountered.");
                }
            }
        } catch (IOException e) {
            throw new DukeException("An IOException occurred.");
        } catch (NumberFormatException e) {
            throw new DukeException("An error occurred during file parsing, unexpected done value encountered.");
        }
    }

    private void saveTodoList() throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task l : list) {
                bw.append(l.getOutputFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred.");
        }
    }

    private int validateDoneOrDeleteIndex(String doneInput) throws DukeException {
        // Checks that the string is not empty and is an integer
        if (doneInput.isEmpty() || !isNumeric(doneInput)) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be blank or not an integer.");
        }

        int oneBasedIndex = Integer.parseInt(doneInput);
        if (oneBasedIndex < 1 || oneBasedIndex > list.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        return oneBasedIndex;
    }

    private boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    private void validateTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private String[] validateEventOrDeadline(String input, String textToReplace, String textToSplit) throws DukeException {
        // Removes textToReplace from input,
        // and finally split it by textToSplit
        String[] splitInput = input.replaceFirst(textToReplace, "")
                .trim().split(textToSplit);

        // Trims all whitespace from the resulting split
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        // Event or deadline should be of length 2 after splitting and both should not be blank
        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("\u2639 OOPS!!! I had trouble processing that input.\n" +
                    "\tPlease make sure that the task description and dates are not empty!");
        }
        return splitInput;
    }

    private void markTaskAsDone(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        list.get(zeroBasedIndex).markAsDone();
        printMessage("Nice! I've marked this task as done:\n\t\t" + list.get(zeroBasedIndex));
    }

    private void deleteTask(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        Task deletedTask = list.get(zeroBasedIndex);
        list.remove(zeroBasedIndex);
        printDeleteSuccessMessage(deletedTask);
    }

    private void addTodoToList(String input) {
        Todo newTodo = new Todo(input);
        list.add(newTodo);
        printAddSuccessMessage(newTodo);
    }

    private void addDeadlineToList(String description, String deadlineBy) {
        Deadline newDeadline = new Deadline(description, deadlineBy);
        list.add(newDeadline);
        printAddSuccessMessage(newDeadline);
    }

    private void addDeadlineToList(String description, Date deadlineDate) {
        Deadline newDeadline = new Deadline(description, deadlineDate);
        list.add(newDeadline);
        printAddSuccessMessage(newDeadline);
    }

    private void addEventToList(String description, String eventTime) {
        Event newEvent = new Event(description, eventTime);
        list.add(newEvent);
        printAddSuccessMessage(newEvent);
    }

    private void addEventToList(String description, Date eventDate) {
        Event newEvent = new Event(description, eventDate);
        list.add(newEvent);
        printAddSuccessMessage(newEvent);
    }

    private void printAddSuccessMessage(Task task) {
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        printLine();
    }

    private void printDeleteSuccessMessage(Task task) {
        printLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
        printLine();
    }

    private void printList() {
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int oneBasedIndex = i + 1;
            System.out.printf("\t%d. %s\n", oneBasedIndex, list.get(i));
        }
        printLine();
    }

    private boolean isExitCommand(String input) {
        return input.equals("bye");
    }

    private boolean isListCommand(String input) {
        return input.equals("list");
    }

    private boolean isDoneCommand(String input) {
        return input.startsWith("done");
    }

    private boolean isAddTodoCommand(String input) {
        return input.startsWith("todo");
    }

    private boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    private boolean isAddEventCommand(String input) {
        return input.startsWith("event");
    }

    private boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }

    private void printGreetingMessage() {
        printMessage("Hello, I'm Duke\n\tWhat can I do for you?");
    }

    private void printExitMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private void printMessage(String message) {
        printLine();
        System.out.println("\t" + message);
        printLine();
    }

    private void printLine() {
        System.out.println("\t____________________________________________________________");
    }
}