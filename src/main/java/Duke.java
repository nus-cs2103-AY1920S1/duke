import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.StandardCopyOption;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasksList = new ArrayList<>();
    private static String pathName = "C:\\Users\\Khairul\\Desktop\\Computing Resources\\CS2103T\\duke\\data\\duke.txt";

    public static void main(String[] args) {

        try {
            init();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        while(sc.hasNextLine()) {
            String nextItem = sc.next();
            if (nextItem.equals("list")) {
                printList();
            } else if (nextItem.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (nextItem.equals("done")) {
                try {
                    doneTask();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("todo")) {
                try {
                    addToDo();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("deadline")) {
                try {
                    addDeadline();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("event")) {
                try {
                    addEvent();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("delete")) {
                try{
                    delete();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private static void appendToFile(String filePath, String text) throws IOException {
        String textToAppend = text;
        File appendingFile = new File(filePath);
        if(appendingFile.length() != 0){
            textToAppend = System.lineSeparator() + text;
        }
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void init() throws FileNotFoundException {
        File taskFile = new File(pathName);
        Scanner s = new Scanner(taskFile);
        while(s.hasNext()){
            String line = s.nextLine();
            String[] details = line.split(" \\| ");
//            for(String st : details){
//                System.out.println(st);
//            }
            switch (details[0]){
                case "T":
                    String taskName = " " + details[2];
                    Task newTask = new Task(taskName, details[1].equals("1"));
                    tasksList.add(newTask);
                    break;
                case "D":
                    String deadlineName = " " + details[2] + " ";
                    String deadlineTime = " " + details[3];
                    Deadline deadline = new Deadline(deadlineName, details[1].equals("1"),
                            deadlineTime);
                    tasksList.add(deadline);
                    break;
                case "E":
                    String eventName = " " + details[2] + " ";
                    String eventTime = " " + details[3];
                    Event event = new Event(eventName, details[1].equals("1"),
                            eventTime);
                    tasksList.add(event);
            }
        }
        s.close();
    }

    private static void listToFile() throws IOException {
        String tempPath = "C:\\Users\\Khairul\\Desktop\\Computing Resources\\CS2103T\\duke\\data\\temp.txt";
        File tempFile = new File(tempPath);
        for (Task task: tasksList) {
            appendToFile(tempPath, task.toFile());
        }
        Files.copy(Paths.get(tempPath), Paths.get(pathName), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempPath));
    }

    private static void printList(){
        System.out.println("Here are the tasks in your list:");
        int length = tasksList.size();
        for (int i = 0; i < length; i++) {
            int toPrint = i + 1;
            System.out.println(toPrint + "." + tasksList.get(i));
        }
    }

    private static void doneTask() throws DukeException{
        if(!sc.hasNextInt()){
            throw new DukeException("☹ OOPS!!! The index of the task to mark as complete " +
                    "must be specified");
        }
        int taskID = sc.nextInt();
        if(taskID < 1 || tasksList.size() < taskID){
            throw new DukeException("☹ OOPS!!! There is no available task in the given index.");
        }
        Task doneTask = tasksList.get(taskID - 1);
        doneTask.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + doneTask);
        try {
            listToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addToDo() throws DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Task(currLine, false);
        tasksList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
        try{
            appendToFile(pathName, newTask.toFile());
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void addDeadline() throws  DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] stringSplit = currLine.split("/by");
        String deadlineName = stringSplit[0];
        if(stringSplit.length < 2){
            throw new DukeException("☹ OOPS!!! The deadline must be specified.");
        }
        String deadline = stringSplit[1];
        Deadline newDeadline = new Deadline(deadlineName, false, deadline);
        tasksList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newDeadline);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
        try{
            appendToFile(pathName, newDeadline.toFile());
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void addEvent() throws DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] stringSplit = currLine.split("/at");
        String eventName = stringSplit[0];
        if(stringSplit.length < 2){
            throw new DukeException("☹ OOPS!!! The event timing must be specified.");
        }
        String duration = stringSplit[1];
        Event newEvent = new Event(eventName, false, duration);
        tasksList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newEvent);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
        try{
            appendToFile(pathName, newEvent.toFile());
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void delete() throws DukeException{
        if(!sc.hasNextInt()){
            throw new DukeException("☹ OOPS!!! The index of the task to delete must be specified");
        }
        int index = sc.nextInt();
        if(index < 1 || tasksList.size() < index){
            throw new DukeException("☹ OOPS!!! There is no available task in the given index.");
        }
        Task toRemove = tasksList.get(index - 1);
        tasksList.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toRemove);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
        try {
            listToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}