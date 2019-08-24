import tasks.*;
import tests.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private List<Task> myList;

    public Duke() {
        myList = new ArrayList<Task>();
        load();
        greet();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String query = sc.nextLine();
        String[] parts = query.split(" ");
        String des, date;
        int index, number;
        while (!query.equals("bye")) {
            try {
                if (parts.length == 0) throw new DukeException("Your input cannot be empty.");
                switch (parts[0]) {
                case "list":
                    duke.list();
                    break;
                case "done":
                    if (parts.length == 1) throw new DukeException("The description of a done cannot be empty.");
                    number = Integer.parseInt(parts[1]);
                    if (number > duke.getSize() || duke.getSize() == 0) {
                        throw new DukeException("It's an invalid task");
                    }
                    duke.markDone(number);
                    break;
                case "delete":
                    if (parts.length == 1) throw new DukeException("The description of a delete cannot be empty.");
                    number = Integer.parseInt(parts[1]);
                    if (number > duke.getSize() || duke.getSize() == 0) {
                        throw new DukeException("It's an invalid task");
                    }
                    duke.delete(number);
                    break;
                case "todo":
                    if (parts.length == 1) throw new DukeException("The description of a todo cannot be empty.");
                    des = query.substring("todo".length() + 1);
                    duke.add(new Todo(des));
                    break;
                case "deadline":
                    if (parts.length == 1)
                        throw new DukeException("The description of a deadline cannot be empty.");
                    index = query.indexOf("/");
                    if (index == -1 || index + 4 >= query.length())
                        throw new DukeException("Please provide a time for your deadline task");
                    if ("deadline".length() + 1 >= index - 1)
                        throw new DukeException("Please provide a proper name for your deadline task");
                    des = query.substring("deadline".length() + 1, index - 1);
                    date = query.substring(index + 4);
                    duke.add(new Deadline(des, date));
                    break;
                case "event":
                    if (parts.length == 1) throw new DukeException("The description of a event cannot be empty.");
                    index = query.indexOf("/");
                    if (index == -1 || index + 4 >= query.length())
                        throw new DukeException("Please provide a time for your event task");
                    if ("event".length() + 1 >= index - 1)
                        throw new DukeException("Please provide a proper name for your event task");
                    des = query.substring("event".length() + 1, index - 1);
                    date = query.substring(index + 4);
                    duke.add(new Event(des, date));
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException ex) {
                duke.print(ex.toString());
            } finally {
                query = sc.nextLine();
                parts = query.split(" ");
            }
        }
        duke.exit();
    }

    private void print(String str) {
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder("    ____________________________________________________________");
        for (String line : lines) {
            sb.append("\n     " + line);
        }
        sb.append("\n    ____________________________________________________________");
        System.out.println(sb.toString());
    }

    private void save() {
        String filePath = "./data/duke.txt";
        try {
            File myFile = new File(filePath);
            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }
            //FileWriter: the file's parent directory must exist
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = "";
            for (Task task : myList) {
                if (!textToAdd.equals("")) {
                    textToAdd = textToAdd.concat("\n");
                }
                textToAdd = textToAdd.concat(task.writer());
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
        }
    }

    private void load() {
        String filePath = "./data/duke.txt";
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splits = line.split("[|]");
                int completed = Integer.parseInt(splits[1].substring(1, 2));
                Task newTask;
                switch (splits[0]) {
                case "T ":
                    newTask = new Todo(splits[2].substring(1));
                    break;
                case "E ":
                    newTask = new Event(splits[2].substring(1, splits[2].length() - 1), splits[3].substring(1));
                    break;
                case "D ":
                    newTask = new Deadline(splits[2].substring(1, splits[2].length() - 1), splits[3].substring(1));
                    break;
                default:
                    newTask = new Task("");
                }
                if (completed == 1) newTask.changeStatus();
                myList.add(newTask);
            }
        } catch (IOException e) {
        }
    }

    private void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        print("Hello! I'm Duke\nWhat can I do for you?");
    }

    public int getSize() {
        return myList.size();
    }

    public void add(Task newTask) {
        myList.add(newTask);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n  " + newTask);
        sb.append("\nNow you have " + getSize() + " tasks in the list.");
        print(sb.toString());
    }

    public void list() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < getSize(); i++) {
            sb.append("\n" + (i + 1) + "." + myList.get(i));
        }
        print(sb.toString());
    }

    public void markDone(int number) {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:");
        Task doneTask = myList.get(number - 1);
        doneTask.changeStatus();
        sb.append("\n" + doneTask);
        print(sb.toString());
    }

    public void delete(int number) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:");
        Task deletedTask = myList.get(number - 1);
        sb.append("\n" + deletedTask);
        myList.remove(number - 1);
        print(sb.toString());
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");
        save();
    }
}
