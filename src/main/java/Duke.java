import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.String;
import java.lang.Integer;

public class Duke {

    private static final String DUKE_FILE = "duke.txt";

    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        // [Level-7] Loads the data from the hard disk when Duke starts up
        String line = null;
        System.out.println(System.getProperty("user.dir"));
        try {
            FileReader fileReader = new FileReader(DUKE_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(Task.convertStringToTask(line));
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        String input;
        boolean isBye = false;
        boolean isError;
        boolean isChanged;
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Hello from\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(indentation + horizontalLine + "\n");
        while (!isBye) {
            isError = false;
            isChanged = false;
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                if (input.equals("list")) {
                    System.out.println(indentation + horizontalLine);
                    System.out.println("Here are the tasks in your list:");
                    int index = 1;
                    for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
                        System.out.println(indentation + index + "." + iterator.next());
                    }
                    System.out.println(indentation + horizontalLine + "\n");
                } else if (input.startsWith("done")) {
                    isChanged = true;
                    System.out.println(indentation + horizontalLine);
                    System.out.println("Nice! I've marked this task as done:");
                    int doneIndex = Integer.parseInt(input.split(" ")[1]) - 1; // possible error here
                    Task doneTask = taskList.get(doneIndex);
                    doneTask.markAsDone();
                    System.out.println(indentation + doneTask);
                    System.out.println(indentation + horizontalLine + "\n");
                } else if (input.startsWith("delete")) {
                    isChanged = true;
                    System.out.println(indentation + horizontalLine);
                    System.out.println("Noted. I've removed this task: ");
                    int deleteIndex = Integer.parseInt(input.split(" ")[1]) - 1; // possible error here
                    System.out.println(indentation + taskList.remove(deleteIndex));
                    if (taskList.size() == 1)
                        System.out.println(indentation + "Now you have " + taskList.size() + " task in the list.");
                    else
                        System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(indentation + horizontalLine + "\n");
                } else { // task
                    System.out.println(indentation + horizontalLine);
                    Task task = null;
                    if (input.startsWith("todo")) {
                        input = input.replaceFirst("^todo", "");
                        if (input.substring(input.indexOf(" ") + 1).isEmpty()) {
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                            isError = true;
                        } else
                            task = new ToDo(input.substring(input.indexOf(" ") + 1));
                    }
                    else if (input.startsWith("deadline"))
                        task = new Deadline(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 4));
                    else if (input.startsWith("event"))
                        task = new Event(input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1), input.substring(input.indexOf("/") + 4));
                    else {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        isError = true;
                    }
                    if (!isError) {
                        isChanged = true;
                        taskList.add(task);
                        System.out.println(indentation + "Got it. I've added this task:");
                        System.out.println(indentation + "  " + task);
                        if (taskList.size() == 1)
                            System.out.println(indentation + "Now you have " + taskList.size() + " task in the list.");
                        else
                            System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                    }
                    System.out.println(indentation + horizontalLine + "\n");
                }
                if (isChanged) {
                    try {
                        FileWriter fileWriter = new FileWriter(DUKE_FILE);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        Task task;
                        int index = 1;
                        for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
                            if (index > 1)
                                bufferedWriter.write("\n");
                            task = (Task) iterator.next();
                            System.out.println(task.convertTaskToString());
                            bufferedWriter.write(task.convertTaskToString());
                        }
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                isBye = true;
            }
        }
        System.out.println(indentation + horizontalLine);
        System.out.println(indentation + "Bye. Hope to see you again soon!");
        System.out.println(indentation + horizontalLine);
    }
}
