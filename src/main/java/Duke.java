import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;


class DukeException extends Exception {
    public DukeException(String s) {
        super(s);
    }
}

public class Duke {
    private ArrayList<Task> todoList;
    File saveList;
    FileWriter editor;

    public Duke() {
        this.todoList = new ArrayList<Task>();
    }

    private void initialize(){
        saveList=new File("src/main/data/list.txt");
        boolean isNew;
        try {
            isNew = saveList.createNewFile();
            if(!isNew){
                Scanner sc = new Scanner(saveList);
                this.transferSavedFile(sc);
                editor = new FileWriter(saveList, true);
                print("Save file loaded");
            }
        }catch(IOException e){
            print("Directory search error. No save file loaded. Initialized with blank list. No commands will be saved to hard disk");

        }
    }

    private void transferSavedFile(Scanner sc){
        int index = 0;
        while(sc.hasNext()){
            String saveEntry = sc.nextLine();
            String instruction[] = saveEntry.split(" \\| ");
            if(instruction[0].equals("T")){
                Todo task = new Todo(instruction[2]);
                todoList.add(task);
            }else if(instruction[0].equals("E")){
                Event task = new Event(instruction[2], instruction[3]);
                todoList.add(task);
            }else if(instruction[0].equals("D")){
                Deadline task = new Deadline(instruction[2], instruction[3]);
                todoList.add(task);
            }
            if(instruction[1].equals("1")){
                todoList.get(index).silentSetDone();
            }
            index++;
        }
        sc.close();
    }

    private static void print(String message) {
        System.out.println(">>" + message);
    }

    private void list() {
        StringBuilder outputMessageMessage = new StringBuilder("List:");
        int i = todoList.size();
        for (int x = 1; x <= i; x++) {
            outputMessageMessage.append("\n  " + x + "." + todoList.get(x - 1));
        }
        String outputMessage = outputMessageMessage.toString();
        print(outputMessage);
    }

    private void run(String userInput) {
        userInput = userInput.trim();
        int spacePosition = userInput.indexOf(" ");
        if (spacePosition == -1) {  //raise exception: only 1 word given
            try {
                this.raiseSingleWordInput(userInput);
            } catch (DukeException e) {
                Duke.print(e.getMessage());
            }
        } else {
            String command = userInput.substring(0, spacePosition);
            String taskContent = userInput.substring(spacePosition + 1);
            if (command.equals("done")) {
                this.done(taskContent);
            } else if (command.equals("delete")) {
                this.delete(taskContent);
            } else if (command.equals("todo")) {
                this.addTodo(taskContent);
            } else if (command.equals("event")) {
                this.addEvent(taskContent);
            } else if (command.equals("deadline")) {
                this.addDeadline(taskContent);
            } else {
                Duke.print("Error: input not recognized");  //
            }
        }
    }

    private void done(String doneIndex) {
        try {
            int doneInt = Integer.parseInt(doneIndex);
            this.todoList.get(doneInt - 1).setDone();
            this.fileSetDone(doneInt - 1);
        } catch (NumberFormatException e) {
            Duke.print("Error: bad task index"); // for wrong index provided
        } catch (IndexOutOfBoundsException e) {
            Duke.print("Error: no such task index");  //for index>array length
        }
    }

    private void fileSetDone(int doneIndex){
        StringBuilder finalInput = new StringBuilder();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(saveList.toPath(), StandardCharsets.UTF_8));
            StringBuilder editString = new StringBuilder(fileContent.get(doneIndex));
            editString.setCharAt(4, '1');
            fileContent.set(doneIndex, editString.toString());
            for(String s: fileContent){
                finalInput.append(s + "\n");
            }
            FileWriter overwriteEditor = new FileWriter(saveList, false);
            overwriteEditor.write(finalInput.toString());
            overwriteEditor.close();
        } catch (IOException e){
            print("File write error");
        }
    }

    private void delete(String deleteIndex) {  //exception same as done method
        try {
            int deleteInt = Integer.parseInt(deleteIndex);
            StringBuilder outputMessageMessage = new StringBuilder("Following task removed:\n    " + this.todoList.get(deleteInt - 1));
            outputMessageMessage.append("\n  " + (this.todoList.size() - 1) + " tasks left in the list");
            this.todoList.remove(deleteInt - 1);
            Duke.print(outputMessageMessage.toString());
            this.fileDelete(deleteInt - 1);
        } catch (NumberFormatException e) {
            Duke.print("Error: bad task index");
        } catch (IndexOutOfBoundsException e) {
            Duke.print("Error: no such task index");
        }
    }

    private void fileDelete(int deleteIndex){
        StringBuilder finalInput = new StringBuilder();
        try {
            ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(saveList.toPath(), StandardCharsets.UTF_8));
            fileContent.remove(deleteIndex);
            for(String s: fileContent){
                finalInput.append(s + "\n");
            }
            FileWriter overwriteEditor = new FileWriter(saveList, false);
            overwriteEditor.write(finalInput.toString());
            overwriteEditor.close();
        } catch (IOException e){
            print("File write error");
        }
    }

    private void addTodo(String task) {
        task = task.trim();
        Todo td = new Todo(task);
        this.todoList.add(td);
        StringBuilder outputMessage = new StringBuilder("Task added:\n");
        outputMessage.append("    " + td);
        outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list");
        try {
            editor.write("T | 0 | " + task + "\n");
            editor.flush();
        }catch(IOException e){
            Duke.print("File write error");
        }
        Duke.print(outputMessage.toString());
    }

    private void addEvent(String task) {
        int split = task.indexOf(" /at");
        if (split == -1) {
            Duke.print("Error: event time not given. Specify event time using \"/at\""); //throw exception?
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);  //exception may occur
                description = description.trim();
                deadline = deadline.trim();
                Event e = new Event(description, deadline);
                this.todoList.add(e);
                StringBuilder outputMessage = new StringBuilder("Task added:\n");
                outputMessage.append("    " + e);
                outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list.");
                editor.write("E | 0 | " + description + " | " + deadline + "\n");
                editor.flush();
                Duke.print(outputMessage.toString());
            } catch (IndexOutOfBoundsException e) { // happens when input is "event xx /at" with no time given
                Duke.print("Error: no event time provided");
            } catch (IOException e){
                Duke.print("File write error");
            }
        }
    }

    private void addDeadline(String task) {
        int split = task.indexOf(" /by");
        if (split == -1) {
            Duke.print("Error: deadline not given. Specify deadline using \"/by\""); // i dont know abt this
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);
                description = description.trim();
                deadline = deadline.trim();
                Deadline d = new Deadline(description, deadline);
                this.todoList.add(d);
                StringBuilder outputMessage = new StringBuilder("Task added:\n");
                outputMessage.append("    " + d);
                outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list.");
                editor.write("D | 0 | " + description + " | " + deadline + "\n");
                editor.flush();
                Duke.print(outputMessage.toString());
            } catch (IndexOutOfBoundsException e) { //same as event time
                Duke.print("Error: no deadline provided");
            } catch (IOException e){
                Duke.print("File write error");
            }
        }
    }

    private void raiseSingleWordInput(String badInput) throws DukeException {
        if (badInput.equals("todo") || badInput.equals("event") || badInput.equals("deadline")) {
            throw new DukeException("Error: no description for task.");
        } else if (badInput.equals("done")) {
            throw new DukeException("Error: done task index missing");
        } else {
            throw new DukeException("Error: no such command");
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void setDone() {
            this.isDone = true;
            String message = "The following task has been marked as done:\n    " + this;
            Duke.print(message);
        }

        public void silentSetDone(){
            this.isDone = true;
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[" + "\u2713" + "]" + this.description;
            } else {
                return "[" + "\u2718" + "]" + this.description;
            }
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[T]" + "[" + "\u2713" + "]" + this.description;
            } else {
                return "[T]" + "[" + "\u2718" + "]" + this.description;
            }
        }
    }

    public class Event extends Task {
        String deadline;

        public Event(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[E]" + "[" + "\u2713" + "]" + this.description + " (at: " + this.deadline + ")";
            } else {
                return "[E]" + "[" + "\u2718" + "]" + this.description + " (at: " + this.deadline + ")";
            }
        }
    }

    public class Deadline extends Task {
        String deadline;

        public Deadline(String description, String deadline) {
            super(description);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[D]" + "[" + "\u2713" + "]" + this.description + " (by: " + this.deadline + ")";
            } else {
                return "[D]" + "[" + "\u2718" + "]" + this.description + " (by: " + this.deadline + ")";
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| OwO\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke process = new Duke();
        process.initialize();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                process.list();
            } else {
                process.run(input);
            }
        }
        String exitMessage = "Goodbye. Hope to see you again. UwU";
        print(exitMessage);
    }
}
