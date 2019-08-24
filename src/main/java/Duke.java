import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import main.task.*;

public class Duke {
    public static void main(String[] args) {
        //Level 7
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        String input = sc.nextLine();
        File data = new File("../src/main/task/Dukedata.txt");
        FileWriter fileWriter = null;
        try {
            if (data.createNewFile()) {
                System.out.println("Previous file not found. Creating a new file");
            } else {
                //read file
                System.out.println("Previous file is found in the database. Retrieving information from there");
                FileReader fileReader = new FileReader(data);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    String dataRead = bufferedReader.readLine();
                    String[] dataReads = dataRead.split(" \\| ");
                    if (dataReads[0].equals("T")) {
                        Task todo = new ToDo(dataReads[2]);
                        tasks.add(todo);
                    } else if (dataReads[0].equals("E")) {
                        Task event = new Event(dataReads[2], dataReads[3]);
                        tasks.add(event);
                    } else if (dataReads[0].equals("D")) {
                        Task deadline = new Deadline(dataReads[2], dataReads[3]);
                        tasks.add(deadline);
                    }
                }
                System.out.print("    Here are your previous tasks:\n");
                for (int i = 0; i < tasks.size(); i = i + 1) {
                    System.out.print("    " + tasks.get(i).toString() + "\n");
                }
            }
            fileWriter = new FileWriter(data, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter writer = new PrintWriter(fileWriter);

        System.out.print("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.length() == 4 && input.substring(0, 4).equals("list")) {
                String result = "";
                for (int i = 0; i < tasks.size(); i = i + 1) {
                    result = result + "    " + (i + 1) + ". " + tasks.get(i).toString() + "\n";
                }
                result = result.equals("") ? "\n" : result;
                System.out.print("    ____________________________________________________________\n" +
                        "    Here are the tasks in your list:\n" +
                        result +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
                continue;
            } else if (input.length() > 4 && input.substring(0, 4).equals("done")) {
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumber = sc2.nextInt();
                Task targetedTask = tasks.get(taskNumber - 1);
                Task.markAsDone(targetedTask);
                sc2.close();
                System.out.print("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + targetedTask.toString() + "\n" +
                        "    ____________________________________________________________\n");
                input = sc.nextLine();
            } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")){
                Scanner sc2 = new Scanner(input);
                sc2.next();
                int taskNumToRemove = sc2.nextInt();
                Task removed = tasks.remove(taskNumToRemove - 1);
                try {
                    data.delete();
                    data.createNewFile();
                    fileWriter = new FileWriter(data);
                    writer = new PrintWriter(fileWriter);
                    for (int i = 0; i < tasks.size(); i++) {
                        writer.println(tasks.get(i).toDataFormat());
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print("    ____________________________________________________________\n" +
                        "     Noted. I've removed this task: \n" +
                        "       " + removed.toString() + "\n" +
                        "     Now you have " + tasks.size() + " tasks in the list.\n" +
                        "    ____________________________________________________________\n");
                sc2.close();
                input = sc.nextLine();
            } else {
                Task t = null;
                String taskType = "";
                try {
                    if (input.length() > 4 && input.substring(0, 4).equals("todo")) {
                        taskType = "todo";
                        if (input.length() < 6) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for To Do");
                        }
                        t = new ToDo(input.substring(5));
                    } else if (input.length() > 8 && input.substring(0, 8).equals("deadline")) {
                        taskType = "deadline";
                        if (input.length() < 10 || !input.contains("/")) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for Deadline");
                        }
                        String res = input.substring(9);
                        String[] pair = res.split("/");
                        t = new Deadline(pair[0], pair[1]);
                    } else if (input.length() > 5 && input.substring(0, 5).equals("event")) {
                        taskType = "event";
                        if (input.length() < 7 || !input.contains("/")) {
                            throw new InsufficientTaskArgumentException("Not enough arguments for Deadline");
                        }
                        String res = input.substring(6);
                        String[] pair = res.split("/");
                        t = new Event(pair[0], pair[1]);
                    } else {
                        throw new InvalidTaskException(input + " is not a valid task");
                    }

                } catch (InsufficientTaskArgumentException e) {
                    System.out.print("    ____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                            "    ____________________________________________________________\n");
                    input = sc.nextLine();
                    continue;
                } catch (InvalidTaskException e) {
                    System.out.print(e.toString() + "\n");
                    input = sc.nextLine();
                    continue;
                }
                tasks.add(t);

                try {
                    data.delete();
                    data.createNewFile();
                    fileWriter = new FileWriter(data);
                    writer = new PrintWriter(fileWriter);
                    for (int i = 0; i < tasks.size(); i++) {
                        writer.println(tasks.get(i).toDataFormat());
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.print("    ____________________________________________________________\n" +
                            "     Got it. I've added this task: \n" +
                            "       "+ t.toString() + "\n" +
                            "     Now you have " + tasks.size() + " tasks in the list.\n" +
                            "    ____________________________________________________________\n");
                input = sc.nextLine();
            }
        }
        sc.close();
        System.out.print("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
    }
}
