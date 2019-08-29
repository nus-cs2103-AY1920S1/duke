import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Duke {

    private static void updateTasksToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    private static void arrayDataToFile(ArrayList<Task> list, String filePath) {
        //Store all the latest data from the ArrayList
        try {
            updateTasksToFile(filePath, "");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        for(Task task : list) {
            StringBuilder temp = new StringBuilder();
            temp.append(task.getTaskType());
            temp.append(" | ");
            if(task.isDone()) {
                temp.append(1);
            } else {
                temp.append(0);
            }
            temp.append(" |");
            temp.append(task.getDescription());
            if((task instanceof Event) || (task instanceof Deadline)) {
                temp.append("| ");
                if(task instanceof Event) {
                    temp.append(((Event) task).getDuration());
                } else {
                    temp.append(((Deadline) task).getBy());
                }
            }
            try {
                appendToFile(filePath, temp.toString() + System.lineSeparator());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private static void loadTasksIntoArray(ArrayList<Task> list, String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while(s.hasNext()) {
            String[] temp = s.nextLine().split(" ");
            String task = (String)Array.get(temp, 0);
            Task newTask;
            if(task.equals("T")) {
                newTask = new Todo((String)Array.get(temp,4));
            } else if(task.equals("D")) {
                newTask = new Deadline((String)Array.get(temp,4),
                        (String)Array.get(temp, 6));
            } else {
                newTask = new Event((String)Array.get(temp,4),
                        (String)Array.get(temp, 6));
            }
            if(((String)Array.get(temp, 2)).equals("1")) {
                newTask.setDone();
            }
            list.add(newTask);
        }
    }

    public static void main(String[] args) {
        //first prepare the file, defined as file1
        String file1 = "data/tasks.txt";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?" );

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        //here, we should load previous data into the ArrayList, before we move on to allow user to
        //interact with the programme
        try {
            loadTasksIntoArray(taskList, file1);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        StringBuilder lineBuilder = new StringBuilder("     ");
        for(int i = 0; i < 59; i++) {
            lineBuilder.append("_");
        }
        String horLine = lineBuilder.toString();
        while(reader.hasNextLine()) {
            String input = reader.next();
            if(input.equals("bye")) {
                System.out.println(horLine);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(horLine);
                break;
            } else if(input.equals("list")){
                System.out.println(horLine);
                if(taskList.isEmpty()) {
                    System.out.println("Sorry, there are no tasks in the list");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    int counter = 1;
                    for (Task task : taskList) {
                        System.out.println("     " + counter + "." + task);
                        counter++;
                    }
                }
                System.out.println(horLine + "\n");

            } else if(input.equals("done")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number - 1);
                temp.setDone();
                System.out.println(horLine);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + temp);
                System.out.println(horLine + "\n");
                arrayDataToFile(taskList, file1);
            } else if(input.equals("delete")) {
                int number = reader.nextInt();
                Task temp = taskList.get(number - 1);
                taskList.remove(number - 1);
                System.out.println(horLine);
                System.out.println("     Nice! I've removed this task:");
                System.out.println("       " + temp);
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine + "\n");
                arrayDataToFile(taskList, file1);
            } else{  //all other commands
                try {
                    if (input.equals("todo")) {
                            String tempString = reader.nextLine();
                            if (tempString.equals("")) {
                                throw new DukeException("      ☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            taskList.add(new Todo(tempString));
                    } else if (input.equals("deadline") || input.equals("event")) {
                        String tempString = reader.nextLine();
                        if(tempString.equals("")) {
                            throw new DukeException("      ☹ OOPS!!! The description of a " +
                                    input + " cannot be empty.");
                        }
                        //replace the first / so that the dates will not be split up
                        tempString = tempString.replaceFirst("/", ":");  //need to assign this to tempString so it is re-recorded
                        String[] tempStringArr = tempString.split(":");
                        String description = (String) Array.get(tempStringArr, 0);
                        String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                        if (input.equals("deadline")) {
                            taskList.add(new Deadline(description, secondString));
                        } else {
                            taskList.add(new Event(description, secondString));
                        }
                    } else {//all other keywords not part of Duke's task handling schedule
                        throw new DukeException("      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    System.out.println(horLine);
                    System.err.println(de.getMessage());
                    System.out.println(horLine + "\n");
                    continue;  //to prevent printing of below mentioned lines
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println(horLine);
                    System.err.println("      ☹ OOPS!!! You need to specify the " + input +
                            " time through a /by (deadline) and /at (event)");
                    System.out.println(horLine + "\n");
                    continue;
                }
                System.out.println(horLine);
                System.out.println("      Got it. I've added this task:");
                System.out.println("       " + taskList.get(taskList.size() - 1));
                System.out.println("      Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(horLine);
                System.out.println();
                arrayDataToFile(taskList, file1);
            }
        }
    }
}