import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Duke {
    private boolean exited = false;
    private List<Task> toDoList = new ArrayList<>();
    //set a variable to store the fixed file path of duke.txt
    private static String filePath = "/Users/joannayap/Downloads/duke/src/main/data/duke.txt";


    public Date parseDate(String date) throws Exception {
        Date twentyFourHourFormat = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        String twelveHourFormat = new SimpleDateFormat("dd/MM/yyy hh:mm").format(twentyFourHourFormat);

        return new SimpleDateFormat("dd/MM/yyy hh:mm").parse(twelveHourFormat);
    }

    public void writeToFile(String textToAdd) throws IOException {
        //Create a file writer object to represent the hard disk
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }


    public void appendToFile(String type, String desc, Date date) {
        try {
            if (date == null)
                writeToFile(type + " | 1 | " + desc + "\n");
            else
                writeToFile(type + " | 1 | " + desc + " | " + date + "\n");

        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

    }
    //create a method to update a specific line in file ( when the task is marked as done)

    public static void updateText(int lineNumber) throws IOException {
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String oldText = lines.get(lineNumber);
        lines.set(lineNumber, oldText.substring(0, 3) + " 0 " + oldText.substring(6, oldText.length()));
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public static void deleteText(int lineNumber) throws IOException {
        Path path = Paths.get(filePath);
        //read all the line in the files
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }


    public void markTaskDone(String input) {
        //parse out the task number
        int taskNum = Integer.parseInt(input.substring(5)) - 1;

        //Ge the specific task object from toDoList
        Task updatedTask = toDoList.get(taskNum);

        //Mark the task as done in duke.txt
        try {
            updateText(taskNum);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
        updatedTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(updatedTask);
    }


    public void addTodoTask(String input) throws EmptyDescException {
        if (input.length() < 5) {
            throw new EmptyDescException("todo");
        } else {

            String desc = input.substring(5);

            appendToFile("T", desc, null);

            Todo newTodo = new Todo(desc);

            toDoList.add(newTodo);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newTodo + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }


    public void addDeadlineTask(String input) throws EmptyDescException, Exception {
        if (input.length() < 9) {
            throw new EmptyDescException("deadline");
        } else {


            int deadlineIndex = input.indexOf('/') + 4;
            String deadline = input.substring(deadlineIndex);
            String desc = input.substring(9, deadlineIndex - 5);
            Date deadlineDate = null;

            deadlineDate = parseDate(deadline);


            appendToFile("D", desc, deadlineDate);

            Deadline newDeadline = new Deadline(desc, deadlineDate);

            toDoList.add(newDeadline);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newDeadline + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }

    public void addEventTask(String input) throws EmptyDescException, Exception {
        if (input.length() < 6) {
            throw new EmptyDescException("event");
        } else {

            int timeIndex = input.indexOf('/') + 4;
            String time = input.substring(timeIndex);
            String desc = input.substring(6, timeIndex - 5);
            Date timeDate = null;

            timeDate = parseDate(time);

            appendToFile("E", desc, timeDate);


            Event newEvent = new Event(desc, timeDate);

            toDoList.add(newEvent);

            int numTask = toDoList.size();


            System.out.println("Got it. I've added this task: \n" + "  "
                    + newEvent + "Now you have " +
                    numTask + " tasks in the list.");
        }
    }

    public void deleteTask(String input) {
        int taskNum = Integer.parseInt(input.substring(7)) - 1;
        //delete text from duke.txt
        try {
            deleteText(taskNum);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }

        Task removedTask = toDoList.remove(taskNum);
        System.out.println("Noted. I've removed this task:\n" + removedTask +
                "Now you have " + toDoList.size() + " tasks in the list.");

    }

    public void respond() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        while (!exited && scanner.hasNextLine()) {
            String input = scanner.nextLine();

            try {
                giveRespond(input);
            } catch (IllegalArgumentException e1) {
                System.out.println(e1);
            } catch (EmptyDescException e2) {
                System.out.println(e2);
            }
        }
    }

    public void determineInputType(String input) throws IllegalArgumentException, EmptyDescException {
        if (input.contains("done")) {


            markTaskDone(input);


        } else if (input.contains("todo")) {


            addTodoTask(input);


        } else if (input.contains("deadline")) {

            try {
                addDeadlineTask(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (input.contains("event")) {

            try {
                addEventTask(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else if (input.contains("delete")) {
            deleteTask(input);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void giveRespond(String input) throws IllegalArgumentException, EmptyDescException {
        switch (input) {

            case "bye":

                exited = true;
                System.out.println("Bye. Hope to see you again soon!");
                break;

            case "list":

                System.out.println("Here are the tasks in your list:");
                int index = 1;
                for (Task s : toDoList) {
                    System.out.print(index + ". " + s);
                    index++;
                }
                break;

            default:
                determineInputType(input);
        }
    }


    public static void main(String[] args) {
        Duke D1 = new Duke();
        D1.respond();
    }

}