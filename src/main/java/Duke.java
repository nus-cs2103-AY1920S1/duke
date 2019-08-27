import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;





public class Duke {
    boolean exited = false;
    List<Task> toDoList = new ArrayList<>();
    //set a variable to store the fixed file path of duke.txt
    static String filePath = "/Users/joannayap/Downloads/duke/src/main/data/duke.txt";


    public void writeToFile(String textToAdd) throws IOException{
        //Create a file writer object to represent the hard disk
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    //create a method to update a specific line in file ( when the task is marked as done)

    public static void updateText(int lineNumber) throws IOException{
        Path path = Paths.get(filePath);
     //read all the line in the files
        List<String>  lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        String oldText = lines.get(lineNumber);
        lines.set(lineNumber, oldText.substring(0, 3) + " 0 " + oldText.substring(6, oldText.length()));
        Files.write(path, lines, StandardCharsets.UTF_8);
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

    public void determineInputType(String input) throws IllegalArgumentException, EmptyDescException{
        if (input.contains("done")) {
            int taskNum = Integer.parseInt(input.substring(5)) - 1;
            Task updatedTask = toDoList.get(taskNum);
            try{
                updateText(taskNum);
            } catch (IOException e){
                System.out.println("Something went wrong " + e.getMessage());
            }
            updatedTask.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(updatedTask);


        } else if (input.contains("todo")) {

            if (input.length() < 5) {
                throw new EmptyDescException("todo");
            } else {

                String desc = input.substring(5);

              try{
                  writeToFile("T | 1 | " + desc + "\n");
              } catch (IOException e){
                  System.out.println("Something went wrong " + e.getMessage());
              }

                Todo newTodo = new Todo(desc);

                toDoList.add(newTodo);

                int numTask = toDoList.size();


                System.out.println("Got it. I've added this task: \n" + "  "
                        + newTodo + "Now you have " +
                        numTask + " tasks in the list.");
            }


        } else if (input.contains("deadline")) {

            if (input.length() < 9) {
                throw new EmptyDescException("deadline");
            } else {

                int deadlineIndex = input.indexOf('/') + 4;
                String deadline = input.substring(deadlineIndex);
                String desc = input.substring(9, deadlineIndex - 5);


                try{
                    writeToFile("D | 1 | " + desc + " | " + deadline+ "\n");
                } catch (IOException e){
                    System.out.println("Something went wrong " + e.getMessage());
                }




                Deadline newDeadline = new Deadline(desc, deadline);

                toDoList.add(newDeadline);

                int numTask = toDoList.size();


                System.out.println("Got it. I've added this task: \n" + "  "
                        + newDeadline + "Now you have " +
                        numTask + " tasks in the list.");
            }
        } else if (input.contains("event")) {

            if (input.length() < 6) {
                throw new EmptyDescException("event");
            } else {

                int timeIndex = input.indexOf('/') + 4;
                String time = input.substring(timeIndex);

                String desc = input.substring(6, timeIndex - 5);

                try{
                    writeToFile("E | 1 | " + desc + " | " + time+ "\n");
                } catch (IOException e){
                    System.out.println("Something went wrong " + e.getMessage());
                }





                Event newEvent = new Event(desc, time);

                toDoList.add(newEvent);

                int numTask = toDoList.size();


                System.out.println("Got it. I've added this task: \n" + "  "
                        + newEvent + "Now you have " +
                        numTask + " tasks in the list.");
            }
        } else if (input.contains("delete")) {
            int taskNum = Integer.parseInt(input.substring(7)) - 1;
            Task removedTask = toDoList.remove(taskNum);
            System.out.println("Noted. I've removed this task:\n" + removedTask +
                    "Now you have " + toDoList.size() + " tasks in the list.");

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

