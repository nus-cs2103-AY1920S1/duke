import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static ArrayList<Task> storage = new ArrayList<>();

    public static void main(String[] args) {

        printOutput("Hello! I'm Duke\nWhat can i do for you?");

        load(); //Load saved file

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")) { //Read input until 'bye' command is entered
            //ADD TODO, DEADLINE, EVENT TASKS
            if(input.contains("todo") || input.contains("deadline") || input.contains("event")){
                try {
                    String command;
                    String remaining;
                    String item;
                    String date;

                    //Check input is valid
                    if(input.indexOf(" ") == -1 || (input.indexOf(" ") + 1) == -1){
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    else{
                        command = input.substring(0, input.indexOf(" "));
                        remaining = input.substring(input.indexOf(" ") + 1);
                    }

                    switch (command) {
                        case "todo":
                            Todo todo = new Todo(remaining);
                            storage.add(todo);
                            printOutput("  " + todo, "Got it. I've added this task: ");
                            break;
                        case "deadline":
                            //Check input is valid
                            if(remaining.indexOf("/by") == -1 || (input.indexOf("/by") + 4) == -1){
                                throw new DukeException("Please ensure that /by and the date is included");
                            }
                            else{
                                item = remaining.substring(0, remaining.indexOf("/by") - 1);
                                date = remaining.substring(remaining.indexOf("/by") + 4, remaining.length());
                            }

                            Deadline deadline = new Deadline(item, date);
                            storage.add(deadline);
                            printOutput("  " + deadline, "Got it. I've added this task: ");
                            break;
                        case "event":
                            //Check input is valid
                            if(remaining.indexOf("/at") == -1 || (input.indexOf("/at") + 4) == -1){
                                throw new DukeException("Please ensure that /at and the date is included");
                            }
                            else{
                                item = remaining.substring(0, remaining.indexOf("/at") - 1);
                                date = remaining.substring(remaining.indexOf("/at") + 4, remaining.length());
                            }

                            Event event = new Event(item, date);
                            storage.add(event);
                            printOutput("  " + event, "Got it. I've added this task: ");
                            break;
                        default:
                            ;
                            break;
                    }

                    save(); //Save the changes to file

                } catch(DukeException de){

                } catch(Exception e){
                    new DukeException("Something went wrong. Please try again.");
                }
            }
            else if(input.contains("done")){ //DONE
                try {
                    int taskNo = Integer.parseInt(
                            input.replace("done", "")
                                    .replace(" ", "")); //Removing 'done' and empty spaces
                    Task task = storage.get(taskNo - 1); //Minus 1 because the displayed list starts at 1
                    if(task.isDone){
                        throw new DukeException("This item has already been checked.");
                    }
                    else {
                        task.markAsDone();
                    }

                    printOutput("Nice! I've marked this task as done: \n  " + task);

                    save(); //Save the changes to file
                } catch(DukeException de){

                } catch(NumberFormatException nfe) {
                    new DukeException("Only numbers are allowed.");
                } catch(IndexOutOfBoundsException ioobe){
                    new DukeException("There is no such item in the list.");
                }
            }
            else if(input.contains("delete")){ //DONE
                try {
                    int taskNo = Integer.parseInt(
                            input.replace("delete", "")
                                    .replace(" ", "")); //Removing 'delete' and empty spaces

                    if(storage.size() > 0){
                        Task task = storage.remove(taskNo - 1);  //Minus 1 because the displayed list starts at 1
                        printOutput("  " + task, "Noted. I've removed this task: ");
                    }
                    else{
                        throw new DukeException("There are no items in the list.");
                    }

                    save(); //Save the changes to file
                } catch(DukeException de){

                } catch(NumberFormatException nfe) {
                    new DukeException("Only numbers are allowed.");
                } catch(IndexOutOfBoundsException ioobe){
                    new DukeException("There is no such item in the list.");
                }
            }
            else if (input.equals("list")) { //LIST ITEMS
                String listOutput = "Here are the tasks in your list:\n";
                for (int i = 0; i < storage.size(); i++) {
                    //Get tasks
                    Task task = storage.get(i);

                    listOutput += (i + 1) + "." + task;

                    if (i + 1 != storage.size()) {
                        listOutput += "\n";
                    }
                }
                printOutput(listOutput);
            } else { //Invalid Command
                new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            input = sc.nextLine();
        }
        printOutput("Bye. Hope to see you again soon!");
    }

    private static void save() {
        try {
            String path = "data/duke.txt";
            FileWriter fw = new FileWriter(path);

            String text = "";
            for (int i = 0; i < storage.size(); i++) {
                //Get tasks
                Task task = storage.get(i);

                if(task instanceof Todo){
                    text += "T|" + task.isDone + "|" + task.description;
                } else if(task instanceof Deadline){
                    text += "D|" + task.isDone + "|" + task.description + "|" + ((Deadline)task).getDate();
                } else if(task instanceof Event){
                    text += "D|" + task.isDone + "|" + task.description + "|" + ((Event)task).getDate();
                }

                if (i + 1 != storage.size()) {
                    text += "\n";
                }
            }

            fw.write(text);
            fw.close();
        } catch(IOException ie) {
            new DukeException("Something went wrong when saving. Please ensure the data directory is created.");
        }
    }

    private static void load() {
        try {
            String path = "data/duke.txt";
            File f = new File(path); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] input = s.nextLine().split("[|]");

                switch(input[0]){
                case "T":
                    Todo todo = new Todo(input[2]);
                    if(input[1].equals("true")) {
                        todo.markAsDone();
                    }
                    storage.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(input[2], input[3]);
                    if(input[1].equals("true")) {
                        deadline.markAsDone();
                    }
                    storage.add(deadline);
                    break;
                case "E":
                    Event event = new Event(input[2], input[3]);
                    if(input[1].equals("true")) {
                        event.markAsDone();
                    }
                    storage.add(event);
                    break;
                default:;
                    break;
                }

            }
        } catch(FileNotFoundException fnfe) {
            new DukeException("Unable to load file. Your saved data will not be loaded.");
        } catch(ArrayIndexOutOfBoundsException aiobe) {
            storage = new ArrayList<>();
            new DukeException("File corrupted. Your saved data will not be loaded.");
        }
    }

    private static void printOutput(String s){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    ____________________________________________________________");
    }

    private static void printOutput(String s, String taskMessage){
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + taskMessage);
        System.out.println("    " + s.replace("\n","\n    "));
        System.out.println("    " + "Now you have " + storage.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
