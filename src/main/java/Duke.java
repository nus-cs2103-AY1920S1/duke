import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        ArrayList<Task> taskList = new ArrayList<Task>();

        String currentDirectory = System.getProperty("user.dir");
        File parent = new File(currentDirectory + "/data/");
        File newFile = new File(parent, "/duke.txt");

        //Create directory and file if does not exist
        if (parent.mkdirs()){
            newFile.createNewFile();
            //Else scan inputs from file
        } else {
            Scanner fileReader = new Scanner(newFile); // create a Scanner using the File as the source
            while (fileReader.hasNext()) {
                String readNewLine = fileReader.nextLine();
                String[] arrayInput = readNewLine.split(" \\| ");
                if (arrayInput[0].equals("T")){
                    Todo newTodo = new Todo(arrayInput[2]);
                    taskList.add(newTodo);
                } else if (arrayInput[0].equals("D")){
                    Deadline newDeadline = new Deadline(arrayInput[2], arrayInput[3]);
                    taskList.add(newDeadline);
                } else {
                    Event newEvent = new Event(arrayInput[2], arrayInput[3]);
                    taskList.add(newEvent);
                }
                if (arrayInput[1].equals("\u2713")){
                    taskList.get(taskList.size()-1).markAsDone();
                }
            }
            fileReader.close();
        }

        //Read user inputs
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            //List tasks
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int numCommands = 0;
                for (Task tasks : taskList) {
                    numCommands += 1;
                    System.out.println(numCommands + "." + tasks);
                }
                //Mark tasks as done
            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                try {
                    try {
                        //Error if user inputs spaces
                        if (command.substring(5).split(" ")[0].equals("")) {
                            throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        }
                        int taskNumber = Integer.parseInt(command.substring(5).split(" ")[0]);
                        //Check if task number is valid
                        if (taskNumber > 0 && taskNumber <= taskList.size()) {
                            editsFile(currentDirectory, taskList.get(taskNumber-1).stringForAppend());
                            taskList.get(taskNumber - 1).markAsDone();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(taskList.get(taskNumber - 1));
                        } else {
                            throw new DukeException("☹ OOPS!!! Task number is invalid.");
                        }
                        //If user input for task number is empty
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        //If non-numeric input given for task number
                    } catch (NumberFormatException err) {
                        throw new DukeException("☹ OOPS!!! Task number is invalid.");
                    }
                    //Catch error so that program does not terminate
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For Todo tasks
            } else if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
                try {
                    try {
                        //Check if description is empty (does not check when user input
                        //multiple spaces as the description.
                        if (!command.substring(5).equals((""))) {
                            Todo newTodo = new Todo(command.substring(5));
                            taskList.add(newTodo);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(newTodo);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                            appendToFile(currentDirectory + "/data/duke.txt", newTodo.stringForAppend());
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For deadline tasks
            } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
                try {
                    try {
                        //Check if description is empty (does not check when user input
                        //multiple spaces as the description.
                        String[] commandLine = command.substring(9).split(" /by ");
                        Deadline newDeadline = new Deadline(commandLine[0], commandLine[1]);
                        taskList.add(newDeadline);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        appendToFile(currentDirectory + "/data/duke.txt", newDeadline.stringForAppend());
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
            } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
                try {
                    try {
                        String[] commandLine = command.substring(6).split(" /at ");
                        Event newEvent = new Event(commandLine[0], commandLine[1]);
                        taskList.add(newEvent);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newEvent);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        appendToFile(currentDirectory + "/data/duke.txt", newEvent.stringForAppend());
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For delete
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                //Similar method and check to "done"
                try {
                    try {
                        //Error if user inputs spaces
                        if (command.substring(7).split(" ")[0].equals("")) {
                            throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        }
                        int taskNumber = Integer.parseInt(command.substring(7).split(" ")[0]);
                        //Check if task number is valid
                        if (taskNumber > 0 && taskNumber <= taskList.size()) {
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(taskList.get(taskNumber - 1));
                            deleteFromFile(currentDirectory, taskList.get(taskNumber - 1).stringForAppend());
                            taskList.remove(taskNumber - 1);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        } else {
                            throw new DukeException("☹ OOPS!!! Task number is invalid.");
                        }
                        //If user input for task number is empty
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        //If non-numeric input given for task number
                    } catch (NumberFormatException err) {
                        throw new DukeException("☹ OOPS!!! Task number is invalid.");
                    }
                    //Catch error so that program does not terminate
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For other commands
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
            }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    //Function to append to file
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    //Deletes specific line from file
    public static void deleteFromFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/duke.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = line;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(!trimmedLine.equals(lineToRemove)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            } else {
                continue;
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }

    //Edits status of a record
    public static void editsFile(String currentDirectory, String line) throws IOException {
        File inputFile = new File(currentDirectory + "/data/duke.txt");
        File tempFile = new File(currentDirectory + "/data/temp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToEdit = line;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(!trimmedLine.equals(lineToEdit)) {
                writer.write(currentLine + System.getProperty("line.separator"));
            } else {
                String newLine = "";
                String[] splittedParts = lineToEdit.split("\\|");
                for (int j = 0; j < splittedParts.length; j++){
                    if (j != 0){
                        newLine += "|";
                    }
                    if (j == 1){
                        newLine += " \u2713 ";
                    }
                    else{
                        newLine += splittedParts[j];
                    }
                }
                writer.write(newLine + System.getProperty("line.separator"));
            }
        }
        writer.close();
        reader.close();
        boolean isDeleted = inputFile.delete();
        boolean isRenamed = tempFile.renameTo(inputFile);
    }
}
