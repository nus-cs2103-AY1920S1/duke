
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
public class Duke {

    public static File getFile(String filePath) {
        File f = new File(filePath);
        return f;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String filePath = "C:\\Users\\johnn\\CS2103\\Week2\\tasks.txt";
        File source = getFile(filePath);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = getTasks(source);

        System.out.println("Hello from\n" + logo + "\n What can I do for you? \n" );

        while (sc.hasNext()) {

            String userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            String firstWord = words[0];
            //remove first word
            String line = "";
            for (int i = 1; i < words.length; i++) {
                if (i == words.length - 1) {
                    line += words[i];
                } else {
                    line += words[i] + " ";
                }
            }


            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.size(); i++) {
                    Task currTask = list.get(i);
//                    System.out.println(currTask.getClass());
                    System.out.print((i+1) + ". " + currTask.toString() + "\n");
                }
            } else if (firstWord.equals("done")) {
                try {
                    int position = Integer.parseInt(words[1]) - 1;
                    Task currTask = list.get(position);
                    currTask.doTask();
                    System.out.println("Nice! I've marked this task as done: \n " +
                            currTask.toString() + "\n");
                } catch (Exception ex) {
                    System.out.println("Unable to find task. Please try again." );

                }
            } else if (firstWord.equals("delete")) {
                try {
                    int position = Integer.parseInt(words[1]) - 1;
                    Task currTask = list.get(position);
                    list.remove(position);
                    System.out.println("Noted. I've removed this task:  \n " +
                            currTask.toString() + "\n Now you have " + list.size() + " task(s) in the list." );
                } catch (Exception ex) {
                    System.out.println("Unable to find task. Please try again." );

                }
            } else
             if (firstWord.equals("todo")){
                if (line.equals("")) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                Todo task = new Todo(line);
                list.add(task);
                System.out.println("Got it. I've added this task: \n"
                        + task.toString() + "\n Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("deadline")) {

                if (line.equals("")) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }
                //split the string by /
                String[] halves = line.split("/by");
                String event = halves[0];
                String by = halves[1];
                Deadline deadline = new Deadline(event, by);
                list.add(deadline);
                System.out.println("Got it. I've added this task: \n"
                        + deadline.toString() + "\n Now you have " + list.size() + " tasks in the list");
            } else if (firstWord.equals("event")) {
                if (line.equals("")) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    continue;
                }
                //split the string by /
                String[] halves = line.split("/at");
                String event = halves[0];
                String at = halves[1];
                Event myEvent = new Event(event, at);
                list.add(myEvent);
                System.out.println("Got it. I've added this task: \n"
                        + myEvent.toString() + "\n Now you have " + list.size() + " tasks in the list");
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        updateTasks(filePath, list);


    }

    public static void updateTasks(String filePath, ArrayList<Task> list) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String c = task.getClass().toString();
            if (c.equals("class Todo")) {
                String description = task.getDescription();
                Boolean isDone = task.getStatus();
                result += "todo" + " " + description + "\n";
                if (isDone) {
                    result += "done " + (i + 1) + "\n";
                }
            } else if (c.equals("class Deadline")) {
                Deadline deadline = (Deadline) task;
                String description = deadline.getDescription();
                Boolean isDone = deadline.getStatus();
                String by = dateToStringConverter(deadline.getBy());
                result += "deadline" + " " + description + "/by" + by + "\n";
                if (isDone) {
                    result += "done " + (i + 1) + "\n";
                }
            } else if (c.equals("class Event")) {
                Event event = (Event) task;
                String description = event.getDescription();
                Boolean isDone = event.getStatus();
                String by = dateToStringConverter(event.getAt());
                result += "event" + " " + description + "/at" + by + "\n";
                if (isDone) {
                    result += "done " + (i + 1) + "\n";
                }
            }
        }
        fw.write(result);
        fw.close();

    }

    public static String dateToStringConverter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy HHmm");
        String sDate = sdf.format(date);
        return sDate;
    }

    public static ArrayList<Task> getTasks(File file) throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {

            String userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            String firstWord = words[0];
            //remove first word
            String line = "";
            for (int i = 1; i < words.length; i++) {
                if (i == words.length - 1) {
                    line += words[i];
                } else {
                    line += words[i] + " ";
                }
            }
            switch (firstWord) {
                case "todo":
                    Todo task = new Todo(line);
                    list.add(task);
                    break;
                case "deadline": {
                    //split the string by /
                    String[] halves = line.split("/by");
                    String event = halves[0];
                    String by = halves[1];
                    Deadline deadline = new Deadline(event, by);
                    list.add(deadline);
                    break;
                }
                case "event": {
                    //split the string by /
                    String[] halves = line.split("/at");
                    String event = halves[0];
                    String at = halves[1];
                    Event myEvent = new Event(event, at);
                    list.add(myEvent);
                    break;
                }
                case "done": {
                    int position = Integer.parseInt(words[1]) - 1;
                    Task currTask = list.get(position);
                    currTask.doTask();
                    break;
                }
            }
        }
        return list;
    }
}
