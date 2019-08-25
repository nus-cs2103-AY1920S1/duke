import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String filePath = System.getProperty("user.dir") + "\\data\\Duke.txt";

        File inputFile = new File(filePath);

        List<Task> taskList = new ArrayList<>();

        String logo = "____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        printMessage(" " + logo + "\n\t Hello! I'm Duke" + "\n" + "\t What can I do for you?");

        try{

            taskList = readFromHardDisk(inputFile);

            while(sc.hasNext()) {
                try{
                    String[] inputs = sc.nextLine().split(" ");
                    String name = "";
                    String time = "";

                    if(inputs[0].equals("bye")) {

                        writeToHardDisk(taskList, filePath);

                        sc.close();
                        printMessage("Bye. Hope to see you again soon!");
                        return;
                    } else if(inputs[0].equals("list")) {
                        printTaskList(taskList);
                    } else if(inputs[0].equals("done")) {

                        if(inputs.length < 2) {
                            throw new DukeException("The task Number cannot be empty.");
                        }

                        int index = Integer.parseInt(inputs[1]) - 1;

                        if(index >= taskList.size() || index < 0) {
                            throw new DukeException("Invalid task number!");
                        }

                        taskList.set(index, taskList.get(index).isDone());
                        printMessage("Nice! I've marked this task as done:"
                                + "\n\t   " + taskList.get(index).toString());

                        writeToHardDisk(taskList, filePath);

                    } else if(inputs[0].equals("todo")){

                        if(inputs.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }

                        for(int i = 1; i < inputs.length; i++) {
                            name = name + inputs[i] + " ";
                        }
                        name = name.substring(0, name.length() - 1);
                        Todo newTask = new Todo(name);
                        taskList.add(newTask);
                        printMessage("Got it. I've added this task: " +
                                "\n\t   " + newTask.toString()
                                + "\n\t Now you have " + taskList.size() + " tasks in the list.");

                        writeToHardDisk(taskList, filePath);
                    } else if(inputs[0].equals("deadline") || inputs[0].equals("event")) {
                        String inputType = inputs[0];

                        if(inputs.length < 2) {
                            if(inputType.equals("deadline")) {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            } else {
                                throw new DukeException("The description of an event cannot be empty.");
                            }
                        }

                        inputs = fetchString(inputs);

                        if (inputs[1].equals("")) {
                            if(inputType.equals("deadline")) {
                                throw new DukeException("The end time of a deadline cannot be empty.");
                            } else {
                                throw new DukeException("The time of an event cannot be empty.");
                            }
                        }

                        name = inputs[0].substring(0, inputs[0].length() - 1);
                        time = inputs[1].substring(0, inputs[1].length() - 1);

                        Task newTask;

                        if (inputType.equals("deadline")) {
                            newTask = new Deadline(name, time);
                        } else {
                            newTask = new Event(name, time);
                        }
                        taskList.add(newTask);
                        printMessage("Got it. I've added this task: " +
                                "\n\t   " + newTask.toString()
                                + "\n\t Now you have " + taskList.size() + " tasks in the list.");

                        writeToHardDisk(taskList, filePath);
                    } else if(inputs[0].equals("delete")) {
                        if(inputs.length < 2) {
                            throw new DukeException("The task Number cannot be empty.");
                        }

                        int index = Integer.parseInt(inputs[1]) - 1;

                        if(index >= taskList.size() || index < 0) {
                            throw new DukeException("Invalid task number!");
                        }

                        Task deletedTask = taskList.remove(index);

                        printMessage("Noted. I've removed this task:"
                                + "\n\t   " + deletedTask.toString()
                                + "\n\t Now you have "
                                + taskList.size()
                                + " tasks in the list.");

                        writeToHardDisk(taskList, filePath);

                    } else if(inputs[0].equals("dir")) {
                        System.out.println(System.getProperty("user.dir"));
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch(DukeException de) {
                    System.err.println(de.toString() + "\n");
                }
            }
        } catch(FileNotFoundException ioe) {
            System.err.println("\t____________________________________________________________");
            System.err.println("\t Error: Input Text File not Found! Program Exiting...");
            System.err.println("\t____________________________________________________________");
        }  catch (UnsupportedEncodingException uee) {
            System.err.println("\t____________________________________________________________");
            System.err.println("\t Error: Unable to write to file! Program Exiting...");
            System.err.println("\t____________________________________________________________");
        }
    }

    public static void printMessage(String output) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t " + output);
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static void printTaskList(List<Task> taskList) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + " " + (i + 1) + ". " + taskList.get(i));
        }
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }

    public static String[] fetchString(String[] arr) {
        String[] result = {"", ""};

        int index = 1;

        while(index < arr.length && !(arr[index].charAt(0) == '/')) {
            result[0] = result[0] + arr[index] + " ";
            index++;
        }

        //Skip the /at or /by
        index++;

        while(index < arr.length) {
            result[1] = result[1] + arr[index] + " ";
            index++;
        }

        return result;

    }

    public static void writeToHardDisk(List<Task> taskList, String filePath) throws FileNotFoundException, UnsupportedEncodingException{

        PrintWriter fileWriter = new PrintWriter(filePath, "UTF-8");

        for(Task T : taskList) {
            fileWriter.println(T.toIndicationInsideFile());
        }

        fileWriter.close();
    }

    public static List<Task> readFromHardDisk(File inputFile) throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();

        Scanner txtSC = new Scanner(inputFile);

        while(txtSC.hasNext()) {
            String[] historicalInputs = txtSC.nextLine().split("\\|");
            boolean oldDone;
            Task oldTask = null;

            if(historicalInputs[1].charAt(1) == '1') {
                oldDone = true;
            } else {
                oldDone = false;
            }

            switch (historicalInputs[0].charAt(0)) {
                case 'T':
                    oldTask = new Todo(historicalInputs[2].substring(1), oldDone);
                    break;
                case 'D':
                    oldTask = new Deadline(historicalInputs[2].substring(1), historicalInputs[3].substring(1), oldDone);
                    break;
                case 'E':
                    oldTask = new Event(historicalInputs[2].substring(1), historicalInputs[3].substring(1), oldDone);
                    break;
            }

            taskList.add(oldTask);
        }

        txtSC.close();

        return taskList;
    }
}