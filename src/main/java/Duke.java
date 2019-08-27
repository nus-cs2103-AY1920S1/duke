import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    private static void loadData(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath);
        //System.out.println(f.getAbsolutePath());
        Scanner sc = new Scanner(f);
        while (sc.hasNext()){
            String type = sc.next();
            switch(type){
                case "T":
                    boolean done = sc.nextBoolean();
                    String taskName = sc.nextLine().trim();
                    list.add(new Task(taskName, done));
                    break;
                case "D":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    String[] userWords = taskName.split("/");
                    list.add(new Deadline(userWords[0], userWords[1], done));
                    break;
                case "E":
                    done = sc.nextBoolean();
                    taskName = sc.nextLine().trim();
                    userWords = taskName.split("/");
                    list.add(new Event(userWords[0], userWords[1], done));
                    break;
            }
        }
    }

    private static void saveData(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String data = "";
        for (Task t : list){
            data += t.writeFormat() + "\n";
        }
        fw.write(data);
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        final String lineSpace = "_______________________________\n";
        String startMessage = lineSpace + "Hello! I'm Duke\nWhat can I do for you?\n" + lineSpace;
        System.out.println(startMessage);
        ArrayList<Task> list = new ArrayList();
        final String filePath = "src/main/data/duke.txt";
        try{
            loadData(filePath, list);
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        Scanner sc = new Scanner(System.in);
        String taskName;
        Task task;
        while (sc.hasNext()) {
            try {
                String userCmd = sc.next();
                String[] userWords;
                if (userCmd.equals("bye")) {
                    System.out.println(lineSpace + "Bye. Hope to see you again soon!\n" + lineSpace);
                    break;
                }
                switch (userCmd) {
                    case "list":
                        System.out.println(lineSpace + "Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println(i + 1 + "." + list.get(i));
                        }
                        System.out.print(lineSpace);
                        break;
                    case "todo":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Task(taskName);
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        saveData(filePath, list);
                        break;
                    case "deadline":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        userWords = taskName.split("/by");
                        if (userWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The date/time of a deadline cannot be empty or is wrongly typed.");
                        }
                        task = new Deadline(userWords[0].trim(), userWords[1].trim());
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        saveData(filePath, list);
                        break;
                    case "event":
                        taskName = sc.nextLine().trim();
                        if (taskName.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        userWords = taskName.split("/at");
                        if (userWords.length == 1) {
                            throw new DukeException("☹ OOPS!!! The date/time of an event cannot be empty or is wrongly typed.");
                        }
                        task = new Event(userWords[0].trim(), userWords[1].trim());
                        list.add(task);
                        System.out.println(lineSpace + "Got it. I've added this task:\n" + task
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        saveData(filePath, list);
                        break;
                    case "delete":
                        int deletion = sc.nextInt();
                        if(deletion < 0 || deletion > list.size()){
                            throw new DukeException("Task not found");
                        }
                        Task temp = list.get(deletion-1);
                        list.remove(deletion-1);
                        System.out.println(lineSpace + "Noted. I've removed this task:\n" + temp
                                + "\nNow you have " + list.size() + " tasks in the list.\n" + lineSpace);
                        saveData(filePath, list);
                        break;
                    case "done":
                        int taskNo = sc.nextInt();
                        if(taskNo < 0 || taskNo > list.size()){
                            throw new DukeException("Task not found");
                        }
                        list.get(taskNo - 1).markAsDone();
                        System.out.println(lineSpace + "Nice! I've marked this task as done:\n"
                                + list.get(taskNo - 1) + "\n" + lineSpace);
                        saveData(filePath, list);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

