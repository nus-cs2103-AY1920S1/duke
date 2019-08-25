import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {

    public static List<String> loadFile() throws FileNotFoundException {
        ArrayList<String> fileContent = new ArrayList<>();
        File file = new File("duke.txt");
        Scanner fileScan = new Scanner(file);
        while (fileScan.hasNext()) {
            fileContent.add(fileScan.nextLine());
        }
        return fileContent;
    }

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        //Instantiate task list
        ArrayList<Task> list = new ArrayList<>();
        list.add(new Task(null, "Task 0"));

        //Load files
        Scanner scan = new Scanner(System.in);
        try {
            List<String> fileContents = loadFile();
            for (int i = 0; i < fileContents.size(); i++) {
                String arr[] = fileContents.get(i).split(" \\| ");
                String type = arr[0];
                switch (type) {
                    case "T":
                        list.add(new Task(arr[0], arr[1], arr[2]));
                        break;
                    case "D":
                        list.add(new Deadline(arr[0], arr[1], arr[2], arr[3]));
                        break;
                    case "E":
                        list.add(new Event(arr[0], arr[1], arr[2], arr[3]));
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }

        //Read input
        try {
            String input = scan.nextLine();
            while (!input.equals("bye")) {
                String arr[] = input.split(" ",2 );
                String command = arr[0];
                    switch (command) {
                        case "list":
                            if (list.size() == 1) {
                                throw new DukeException("OOPS!!! You have no tasks to be displayed.");
                            } else {
                                System.out.println("Here are the tasks in your list:");
                                for (int i = 1; i < list.size(); i++) {
                                    System.out.println(i + "." + list.get(i));
                                }
                            }
                            break;
                        case "done":
                            if (arr.length == 1) {
                                throw new DukeException("OOPS!!! Please enter a task number to check as done e.g done 1");
                            } else {
                                int num = Integer.parseInt(arr[1]);
                                if (num >= list.size() || num < 1) {
                                    throw new DukeException("This task does not exist.");
                                } else {
                                    System.out.println(list.get(num).done());
                                }
                            }
                            break;
                        case "todo":
                            if (arr.length == 1) {
                                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            } else {
                                list.add(new Task("T", arr[1]));
                               // fw.write(list.get(list.size()-1).fileFormat());
                                System.out.println("Got it. I've added this task:\n" + list.get(list.size()-1));
                                System.out.println("Now you have " + (list.size()-1) + " tasks in the list");
                            }
                            break;
                        case "deadline":
                            if (arr.length == 1) {
                                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            } else {
                                String[] deadline = arr[1].split(" /by ", 2);
                                if (deadline.length == 1) {
                                    throw new DukeException("OOPS!!! Please enter a due date. e.g complete homework /by 1 Jan");
                                } else {
                                    list.add(new Deadline("D", deadline[0], deadline[1]));
                                    //fw.write(list.get(list.size()-1).fileFormat());
                                    System.out.println("Got it. I've added this task:\n" + list.get(list.size()-1));
                                    System.out.println("Now you have " + (list.size()-1) + " tasks in the list");
                                }
                            }
                            break;
                        case "event":
                            if (arr.length == 1) {
                                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                            } else {
                                String[] event = arr[1].split(" /at ", 2);
                                if (event.length == 1) {
                                    throw new DukeException("OOPS!!! Please enter an event date. e.g group meeting /at 1 Jan");
                                } else {
                                    list.add(new Event("E", event[0], event[1]));
                                   // fw.write(list.get(list.size()-1).fileFormat());
                                    System.out.println("Got it. I've added this task:\n" + list.get(list.size()-1));
                                    System.out.println("Now you have " + (list.size()-1) + " tasks in the list");
                                }
                            }
                            break;
                        case "delete":
                            if (arr.length == 1) {
                                throw new DukeException("OOPS!!! Please enter a task number to delete e.g delete 1");
                            } else {
                                int num = Integer.parseInt(arr[1]);
                                if (num >= list.size() || num < 1) {
                                    throw new DukeException("This task does not exist.");
                                } else {
                                    System.out.println("Noted. I've removed this task:\n" + list.remove(num));
                                    System.out.println("Now you have " + (list.size() - 1) + " tasks in the list.");
                                }
                            }
                            break;
                        default:
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");

                    }


                //Taking in the next line
                input = scan.nextLine();
            }
            BufferedWriter fw = new BufferedWriter(new FileWriter("duke.txt"));
            for (int i = 1; i < list.size(); i++) {
                fw.write(list.get(i).fileFormat());
            }
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
