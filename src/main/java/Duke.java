import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static File file = new File("/users/junhup/desktop/duke/src/main/java/duke.txt");
    public static void main(String[] args) throws IOException {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        readStored();
        readCurrent();
    }
    
    public static void updateComplete(int index) throws IOException{
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            int count = 0;
            ArrayList<String> input = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (count == index) {
                    count++;
                    line = line.replace("0", "1");
                    input.add(line);
                } else {
                    count++;
                    input.add(line);
                }
            }
            fr.close();
            br.close();
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : input) {
                out.write(s);
                out.newLine();
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    public static void readCurrent() {
        Scanner scanner  = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if (input.equals("list")) {
                    printList(tasks);
                } else if (input.startsWith("done")) {
                    completeTask(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    addTaskToList(input);
                }
            } catch (Exception e) {
                handleException(e);
            }
        }
    }
    
    public static void readStored() throws IOException{
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/users/junhup/desktop/duke/src/main/java/duke.txt"));
            readData(bufferedReader, tasks);
            bufferedReader.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void readData(BufferedReader bufferedReader, ArrayList<Task> tasksList) {
        String inputLine = null;
        try {
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
                String[] input = inputLine.split(Pattern.quote(" | "));
                String typeOfTasks = input[0];
                if (typeOfTasks.equals("T")) {
                    addStoredTodo(input, tasksList);
                } else if (typeOfTasks.equals("D")) {
                    addStoredDeadline(input, tasksList);
                } else {
                    addStoredEvent(input, tasksList);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addTaskToList(String input) throws EmptyDescriptionException, InvalidDescriptionException, InvalidInputException, IOException {
        FileWriter fw = new FileWriter("/users/junhup/desktop/duke/src/main/java/duke.txt", true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        if (input.startsWith("todo")) {
            addTodo(input, bufferedWriter);
        } else if (input.startsWith("deadline")) {
            addDeadline(input, bufferedWriter);
        } else if (input.startsWith("event")) {
            addEvent(input, bufferedWriter);
        } else {
            throw new InvalidInputException();
        }
        bufferedWriter.close();
    }
    
    public static void addTodo(String input, BufferedWriter bw) throws EmptyDescriptionException {
        try {
            if (!input.substring(4).isEmpty()) {
                String description = input.substring(4);
                Todo todo = new Todo(description);
                tasks.add(todo);
                bw.write("T | 0 | " + description);
                bw.newLine();
                bw.flush();
                printOut(todo);
            } else {
                throw new EmptyDescriptionException("todo");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addDeadline(String input, BufferedWriter bw) throws EmptyDescriptionException, InvalidDescriptionException {
        try {
            if (input.contains("/by")) {
                int index = input.lastIndexOf("/by");
                String description = input.substring(8, index);
                String by = input.substring(index + 3);
                if (description.isBlank()) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (by.isBlank()) {
                    throw new InvalidDescriptionException("deadline");
                }
                Deadline deadline = new Deadline(description, by);
                tasks.add(deadline);
                bw.write("D | 0 | " + description + " | " + by);
                bw.newLine();
                bw.flush();
                printOut(deadline);
            } else {
                throw new InvalidDescriptionException("deadline");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addEvent(String input, BufferedWriter bw) throws EmptyDescriptionException, InvalidDescriptionException {
        try {
            if (input.contains("/at")) {
                int index = input.lastIndexOf("/at");
                String description = input.substring(5, index);
                String at = input.substring(index + 3);
                if (description.isBlank()) {
                    throw new EmptyDescriptionException("event");
                }
                if (at.isBlank()) {
                    throw new InvalidDescriptionException("event");
                }
                Event event = new Event(description, at);
                tasks.add(event);
                bw.write("E | 0 | " + description + " | " + at);
                bw.newLine();
                bw.flush();
                printOut(event);
            } else {
                throw new InvalidDescriptionException("event");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addStoredTodo(String[] input, ArrayList<Task> tasksList) {
        Todo todo = new Todo(input[2]);
        if (Integer.parseInt(input[1]) == 1) {
            todo.complete();
        }
        tasksList.add(todo);
    }
    
    public static void addStoredDeadline(String[] input, ArrayList<Task> tasksList) {
        Deadline deadline = new Deadline(input[2], input[3]);
        if (Integer.parseInt(input[1]) == 1) {
            deadline.complete();
        }
        tasksList.add(deadline);
    }
    
    public static void addStoredEvent(String[] input, ArrayList<Task> tasksList) {
        Event event = new Event(input[2], input[3]);
        if (Integer.parseInt(input[1]) == 1) {
            event.complete();
        }
        tasksList.add(event);
    }
    
    public static void completeTask(String input) throws IOException {
        String[] inputs = input.split(" ");
        int index = Integer.parseInt(inputs[1]) - 1;
        tasks.get(index).complete();
        updateComplete(index);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
    }
    
    public static void updateDelete(int index) throws IOException{
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            int count = 0;
            ArrayList<String> input = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (count == index) {
                    count++;
                } else {
                    count++;
                    input.add(line);
                }
            }
            fr.close();
            br.close();
            
            FileWriter fw = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : input) {
                out.write(s);
                out.newLine();
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
    

    public static void deleteTask(String input) throws IOException {
        try {
            String[] inputs = input.split(" ");
            int index = Integer.parseInt(inputs[1]) - 1;
            Task removedTask = tasks.remove(index);
            updateDelete(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printOut(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void printList(ArrayList<Task> tasksList) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksList.size(); i++) {
            System.out.println(i + 1 + "." + tasksList.get(i));
        }
    }
    
    public static void handleException(Exception e) {
        if (e instanceof InvalidInputException) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (e instanceof EmptyDescriptionException) {
            System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", e.getMessage()));
        } else if (e instanceof InvalidDescriptionException) {
            System.out.println(String.format("OOPS!!! Invalid input! Make sure your %s has a description and required data after /at for Event or /by for Deadline.\n", e.getMessage()));
        } else {
            System.out.println(e.getMessage());
        }

    }
}

