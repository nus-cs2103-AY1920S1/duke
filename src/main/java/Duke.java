import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    //constants for UI messages
    final static String greet = "Hello! I'm Duke\nWhat can I do for you?";
    final static String goodbye = "Bye. Hope to see you again!";
    final static String niceAdded = "Nice! I've marked this task as done:";
    final static String gotIt = "Got it. I've added this task:";
    final static String deleted = "Noted. I've removed this task:";
    //List of all tasks
    public static ArrayList<Task> listOfTasks = new ArrayList<>();

    //helper function to print total number of tasks
    private static void printNumTasks(){
        System.out.println("Now you have " + Task.totalTasks + " tasks in the list.");
    }
    private static void printLogo(){
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    private static void writeToDisk(String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        String lineToWrite = "";
        for(int k = 0; k < listOfTasks.size(); k++){
            Task curr = listOfTasks.get(k);
            int doneFlag = curr.isDone ? 1 : 0;
            if(curr instanceof Todo){
                lineToWrite = "T" + "|" + doneFlag + "|" + curr.getDescription();
            } else if(curr instanceof Deadline){
                Deadline currDeadline = (Deadline) curr;
                lineToWrite = "D" + "|" + doneFlag + "|" + currDeadline.getDescription() + "|"
                        + currDeadline.getDate().getDateString() + " " + currDeadline.getTiming().getTimeString();
            } else {
                Event currEvent = (Event) curr;
                lineToWrite = "E" + "|" + doneFlag + "|" + currEvent.getDescription() + "|"
                        + currEvent.getDate().getDateString() + " " + currEvent.getTiming().getTimeString();
            }
            fw.write(lineToWrite + System.lineSeparator());
        }
        System.out.println("Writing new changes to disk...");
        fw.close();
        System.out.println("Writing done!");
    }

    public static void main(String[] args) throws Exception {
        BufferedReader fileinput = new BufferedReader(new FileReader("data/duke.txt"));
        File f = new File("data/duke.txt");
        Boolean fileIsChanged = false;

        //Read data from duke.txt to store into ArrayList
        String fileData = fileinput.readLine();
        while(fileData != null){
            String[] fileTokens = fileData.split("\\|");
            String taskType = fileTokens[0];
            int doneFlag = Integer.parseInt(fileTokens[1]);
            String taskDesc = fileTokens[2];
            String taskDate;
            String taskTime;
            String[] dateTimeTokens;

            switch (taskType){
            case "T":
                listOfTasks.add(new Todo(taskDesc, doneFlag));
                break;
            case "D":
                dateTimeTokens = fileTokens[3].split(" ");
                taskDate = dateTimeTokens[0];
                taskTime = dateTimeTokens[1];
                listOfTasks.add(new Deadline(taskDesc, taskDate, taskTime, doneFlag));
                break;
            case "E":
                dateTimeTokens = fileTokens[3].split(" ");
                taskDate = dateTimeTokens[0];
                taskTime = dateTimeTokens[1];
                listOfTasks.add(new Event(taskDesc, taskDate, taskTime, doneFlag));
                break;
            default:
                break;
            }
            fileData = fileinput.readLine();
        }
        fileinput.close();

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        printLogo();
        System.out.println(greet);

        String command = input.readLine();

        //main program logic
        while(!command.equals("bye")){
            String[] tokens = command.split(" ");
            String toAdd = "";
            Task newTask;
            try {
                switch (tokens[0]) {
                case "list":
                    //"list" logic
                    int size = listOfTasks.size();
                    System.out.println("Here are your tasks in your list:");
                    for (int i = 0; i < size; i++) {
                        Task curr = listOfTasks.get(i);
                        System.out.println(i + 1 + "." + curr.toString());
                    }
                    break;
                case "done":
                    //"done" logic
                    try {
                        if(tokens.length < 2){
                            throw new DukeException("☹ OOPS!!! Please specify task to complete");
                        }
                        int toComplete = Integer.parseInt(command.split(" ")[1]) - 1;
                        if(toComplete >= listOfTasks.size() || toComplete < 0){
                            throw new DukeException("OOPS! Task " + (toComplete + 1) + " doesn't exist!");
                        }
                        Task curr = listOfTasks.get(toComplete);
                        listOfTasks.get(toComplete).completeTask();
                        System.out.println(niceAdded);
                        System.out.println(curr.toString());
                        fileIsChanged = true;
                    } catch (DukeException de){
                        System.err.println(de.getMessage());
                    }
                    break;
                case "delete":
                    //"delete" logic
                    try{
                        if(tokens.length < 2){
                            throw new DukeException("☹ OOPS!!! Please specify task to delete");
                        }
                        int toDelete = Integer.parseInt(command.split(" ")[1]) - 1;
                        if(toDelete >= listOfTasks.size() || toDelete < 0){
                            throw new DukeException("☹ OOPS! Task " + (toDelete + 1) + " doesn't exist!");
                        } else {
                            Task curr = listOfTasks.get(toDelete);
                            System.out.println(deleted);
                            System.out.println(curr.toString());
                            listOfTasks.remove(toDelete);
                            Task.totalTasks--;
                            printNumTasks();
                            fileIsChanged = true;
                        }
                    } catch (DukeException de){
                        System.err.println(de.getMessage());
                    }
                    break;
                case "todo":
                    //toodo logic
                    try {
                        if (tokens.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        for (int j = 1; j < tokens.length; j++) {
                            toAdd = toAdd + tokens[j] + " ";
                        }
                        newTask = new Todo(toAdd.trim());
                        System.out.println(gotIt);
                        System.out.println(" " + newTask.toString());
                        listOfTasks.add(newTask);
                        fileIsChanged = true;
                        printNumTasks();
                    } catch (DukeException de) {
                        System.err.println(de.getMessage());
                    }
                    break;
                case "deadline":
                    //"deadline" logic
                    try {
                        if (tokens.length == 1) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        boolean dateFlag = false;
                        String dateString = "";
                        String timeString = "";

                        //Check if both date and time are specified
                        if(command.split("/by")[1].trim().split(" ").length != 2){
                            throw new DukeException("☹ OOPS!!! Date and Timing not specified correctly!");
                        }

                        for (int m = 1; m < tokens.length; m++) {
                            if (tokens[m].equals("/by")) {
                                dateFlag = true;
                            } else {
                                if (dateFlag == false) toAdd = toAdd + tokens[m] + " ";
                                else {
                                    if (m == tokens.length - 1) {
                                        timeString = tokens[m];
                                    } else {
                                        dateString = tokens[m];
                                    }
                                }
                            }
                        }
                        newTask = new Deadline(toAdd.trim(), dateString.trim(), timeString.trim());
                        System.out.println(gotIt);
                        System.out.println(" " + newTask.toString());
                        listOfTasks.add(newTask);
                        fileIsChanged = true;
                        printNumTasks();
                    } catch (DukeException de){
                        System.err.println(de.getMessage());
                    }
                    break;
                case "event":
                    //"event" logic
                    try {
                        if(tokens.length == 1){
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String dateString = "";
                        String timeString = "";
                        boolean timeFlag = false;

                        //Check if both date and time are specified
                        if(command.split("/by")[1].trim().split(" ").length != 2){
                            throw new DukeException("☹ OOPS!!! Date and Timing not specified correctly!");
                        }

                        for (int z = 1; z < tokens.length; z++) {
                            if (tokens[z].equals("/at")) timeFlag = true;
                            else {
                                if (timeFlag == false) toAdd = toAdd + tokens[z] + " ";
                                else{
                                    if(z == tokens.length - 1){
                                        timeString = tokens[z];
                                    } else {
                                        dateString = tokens[z];
                                    }
                                }
                            }
                        }
                        newTask = new Event(toAdd.trim(), dateString.trim(), timeString.trim());
                        System.out.println(gotIt);
                        System.out.println(" " + newTask.toString());
                        listOfTasks.add(newTask);
                        fileIsChanged = true;
                        printNumTasks();
                    } catch (DukeException de){
                        System.err.println(de.getMessage());
                    }
                    break;
                default:
                    //unrecognized command
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de){
                System.err.println(de.getMessage());
            }
            //read next command
            command = input.readLine();
        }
        //exit program
        input.close();
        if(fileIsChanged) {
            writeToDisk(f.getAbsolutePath());
        }
        System.out.println(goodbye);
    }
 }
