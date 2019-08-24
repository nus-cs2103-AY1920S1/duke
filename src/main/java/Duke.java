import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static ArrayList<Task> arr = new ArrayList<>();
    public static String lines = "\t____________________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lines);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(lines);

        try {
            FileReader reader = new FileReader("C:\\Users\\jietung\\Desktop\\duke\\src\\main\\data\\duke.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String s;

            System.out.println(lines);
            System.out.println("\tHere are the tasks in your list:");

            int i = 1;

            while ((s = bufferedReader.readLine()) != null) {
                Task t;
                if (s.charAt(1) == 'T') {
                    t = new Todo(s.substring(7));
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                } else if (s.charAt(1) == 'D') {
                    int index = s.indexOf(40);
                    t = new Deadline(s.substring(7, index - 1), s.substring(index + 5, s.length() - 2));
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                } else {
                    int index = s.indexOf(40);
                    t = new Event(s.substring(7, index - 1), s.substring(index + 5, s.length() - 2));
                    if (s.charAt(4) == '\u2713') {
                        t.markAsDone();
                    }
                    arr.add(t);
                }
                System.out.println("\t" + i + ". " + t);
                i++;
            }

            System.out.println(lines);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);



        String str = sc.nextLine();
        while (!str.equals("bye")) {
            try {
                input(str);
            } catch (DukeException e) {
                System.out.println(lines);
                System.err.println("\t" + e.getMessage());
                System.out.println(lines);
            }
            str = sc.nextLine();
        }

        try {
            FileWriter writer = new FileWriter("C:\\Users\\jietung\\Desktop\\duke\\src\\main\\data\\duke.txt", false);
            BufferedWriter bufferedWriter =  new BufferedWriter(writer);

            for (Task t : arr) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lines);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(lines);
    }

    public static void input(String str) throws DukeException {

        String[] strArr = str.split(" ");

        if (str.equals("list")) { //list

            int i = 1;
            System.out.println(lines);
            System.out.println("\tHere are the tasks in your list:");

            for (Task t : arr) {
                System.out.println("    " + i + "." +  t);
                i++;
            }

            System.out.println(lines);

        } else if (strArr[0].equals("done")) { //mark task as done

            if (strArr.length == 1) {
                throw new DukeException("OOPS!!! Please state the task number you want to mark as done.");
            }

            int index = Integer.parseInt(strArr[1]) - 1;

            if (index >= arr.size()) {
                throw new DukeException("OOPS!!! The number is too large.");
            } else if (index < 0) {
                throw new DukeException("OOPS!!! The number is too small.");
            }

            Task t = arr.get(index);
            t.markAsDone();

            System.out.println(lines);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + t);
            System.out.println(lines);

        } else if (strArr[0].equals("todo")
                || strArr[0].equals("deadline")
                || strArr[0].equals("event")) { //add task to list

            Task t;

            if (strArr[0].equals("todo")) { //todo

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }

                t = new Todo(str.substring(5));
                arr.add(t);

            } else if (strArr[0].equals("deadline")) { //deadline

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!str.contains(" /by ")) {
                    throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
                }

                int indexOfSlash = str.indexOf(47);
                String ss1 = str.substring(0, indexOfSlash);
                String[] ss1Arr = ss1.split(" ");
                String ss2 = str.substring(indexOfSlash);
                String[] ss2Arr = ss2.split(" ");

                if (ss1Arr.length == 1) {
                    throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                }
                if (ss2Arr.length == 1) {
                    throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
                }

                t = new Deadline(str.substring(9, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                arr.add(t);

            } else { //event

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                if (!str.contains(" /at ")) {
                    throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
                }

                int indexOfSlash = str.indexOf(47);
                String ss1 = str.substring(0, indexOfSlash);
                String[] ss1Arr = ss1.split(" ");
                String ss2 = str.substring(indexOfSlash);
                String[] ss2Arr = ss2.split(" ");

                if (ss1Arr.length == 1) {
                    throw new DukeException("OOPS!!! The description of event cannot be empty.");
                }
                if (ss2Arr.length == 1) {
                    throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
                }

                t = new Event(str.substring(6, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                arr.add(t);

            }

            System.out.println(lines);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t  " + t);
            System.out.println("\tNow you have " + arr.size() + " tasks in the list.");
            System.out.println(lines);

        }  else if (strArr[0].equals("delete")) { //delete

            if (strArr.length == 1) {
                throw new DukeException("OOPS!!! Please state the task number you want to delete.");
            }

            int index = Integer.parseInt(strArr[1]) - 1;

            if (index >= arr.size()) {
                throw new DukeException("OOPS!!! The number is too large.");
            } else if (index < 0) {
                throw new DukeException("OOPS!!! The number is too small.");
            }

            Task t = arr.remove(index);

            System.out.println(lines);
            System.out.println("\tNoted! I've removed this task:");
            System.out.println("\t" + t);
            System.out.println("\tNow you have " + arr.size() + " tasks in the list.");
            System.out.println(lines);

        } else {
            throw new DukeException("OOPS!!! I,m sorry, but I don't know what that means :-(");
        }
    }
}
