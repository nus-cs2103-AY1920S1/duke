import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {

        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File("data/duke.txt");
        f.createNewFile();
        Scanner s = new Scanner(f);

        Integer num; //number in list which is done
        Task currTask; //refers to current task in list
        String currEvent;
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            readFileContents("data/duke.txt", list);
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("File not found");
        }

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        while (!line.equals("bye")) {
            try {
                String desc = ""; //current task being added
                String time = ""; //current time of current task being added
                String[] words = line.split(" ", 2);
                currEvent = words[0];
                if (line.equals("list")) {
                    for (int i = 1; i <= list.size(); i++) {
                        System.out.println(i + ". " + list.get(i - 1));
                    }
                } else if (words[0].equals("done")) {
                    num = Integer.valueOf(words[1]);
                    currTask = list.get(num - 1);
                    currTask.setStatusIcon(true);
                    System.out.println("Nice! I've marked this task as done: \n  " + currTask);
                } else if (currEvent.equals("todo")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    desc = words[1];
                    list.add(new ToDos(desc));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else if (currEvent.equals("deadline")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    desc = words[1].split(" /by ", 2)[0];
                    time = words[1].split(" /by ", 2)[1];
                    list.add(new Deadline(desc, time));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else if (currEvent.equals("event")) {
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    desc = words[1].split(" /at ", 2)[0];
                    time = words[1].split(" /at ", 2)[1];
                    list.add(new Event(desc, time));
                    System.out.println("Got it. I've added this task: \n  " + list.get(list.size() - 1)
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else if (words[0].equals("delete")) {
                    num = Integer.valueOf(words[1]);
                    currTask = list.remove(num - 1);
                    System.out.println("Noted. I've removed this task: \n  " + currTask
                            + "\nNow you have " + list.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException e){
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! The task done must be a number.");
            } catch (ParseException e) {
                e.printStackTrace();
            } finally {
                line = sc.nextLine();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            sb.append(task.format()).append("\n");
        }
        writeToFile("data/duke.txt", sb.toString());
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException, ParseException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            boolean checkDone;
            String currLine = s.nextLine();
            String[] currWords= currLine.split("[|]", 4);
            if (currWords[1].equals("1")) {
                checkDone = true;
            } else {
                checkDone = false;
            }

            if (currWords[0].equals("T")) {
                Task addedTask = new ToDos(currWords[2]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            } else if (currWords[0].equals("D")) {
                Task addedTask = new Deadline(currWords[2], currWords[3]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            } else if (currWords[0].equals("E")) {
                Task addedTask = new Event(currWords[2], currWords[3]);
                addedTask.setStatusIcon(checkDone);
                list.add(addedTask);
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
