import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String border = "-------------------------------------";
    private static ArrayList<Task> items = new ArrayList<Task>();

    /**
     * Main method for executing Duke.
     *
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greetings before program
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Hello! I'm Duke\n");
        sb.append("What can I do for you?\n");
        sb.append(border + "\n");
        String greetings = sb.toString();
        System.out.println(greetings);
        sb.setLength(0);

        //error message for indexOutOfBound
        sb.append(border + "\n");
        sb.append("Invalid number. Number not listed. \n");
        sb.append(border + "\n");
        String indexError = sb.toString();
        sb.setLength(0);

        //error message for empty input
        sb.append(border + "\n");
        sb.append("No input detected. Please enter a number. \n");
        sb.append(border + "\n");
        String emptyError = sb.toString();
        sb.setLength(0);

        Scanner pastScan = new Scanner(new FileReader("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt"));
        loadExisting(pastScan);
        //Scanner obj for input
        Scanner sc = new Scanner(System.in);

        //Loop till user input 'bye'
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {

            //adding items to arraylist for listing purpose
            if (input.toLowerCase().contains("done")) {
                //getting the number for item
                try {
                    int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                    Task curr = items.get(itemNum - 1);
                    curr.markAsDone();
                    //forming the message
                    sb.append(border + "\n");
                    sb.append("Nice! I've marked this task as done:\n");
                    sb.append(curr + "\n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                    input = sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indexError);
                    sb.setLength(0);
                    input = sc.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println(emptyError);
                    sb.setLength(0);
                    input = sc.nextLine();
                }
            } else if (input.toLowerCase().contains("delete")) {
                //getting the number for item
                try {
                    int itemNum = Integer.parseInt(input.substring(input.length() - 1));
                    Task curr = items.get(itemNum - 1);
                    //forming the message
                    sb.append(border + "\n");
                    sb.append("Noted! I've removed this task:\n");
                    sb.append(curr + "\n");
                    sb.append("Now you have " + (items.size() - 1) + " tasks in the list.\n");
                    sb.append(border + "\n");

                    items.remove(itemNum - 1);

                    System.out.println(sb.toString());
                    sb.setLength(0);
                    input = sc.nextLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indexError);
                    sb.setLength(0);
                    input = sc.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println(emptyError);
                    sb.setLength(0);
                    input = sc.nextLine();
                }
            } else if (input.toLowerCase().equals("list")) {
                sb.append(border + "\n");
                //list out all items in arraylist items
                for (int i = 1; i <= items.size(); i++) {
                    Task curr = items.get(i - 1);
                    sb.append(i + "." + curr + "\n");
                }
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            } else if (input.toLowerCase().contains("todo")) {
                //adding an item
                try {
                    Todo newTask = new Todo(input.substring(5));
                    items.add(newTask);
                    String message = generateMessage(newTask);
                    System.out.println(message);
                    input = sc.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Todo must have valid description\n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                    input = sc.nextLine();
                }
            } else if (input.toLowerCase().contains("deadline")) {
                try {
                    int date = input.indexOf("/by");
                    //split input into [deadline] [description] [date]
                    Deadline newTask = new Deadline(input.substring(9, date), input.substring(date + 3));
                    items.add(newTask);
                    String message = generateMessage(newTask);
                    System.out.println(message);
                    input = sc.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Deadline's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                    input = sc.nextLine();
                }

            } else if (input.toLowerCase().contains("event")) {
                try {
                    int time = input.indexOf("/at");
                    //split input into [event] [description] [timing]
                    Event newTask = new Event(input.substring(6, time), input.substring(time + 3));
                    items.add(newTask);
                    String message = generateMessage(newTask);
                    System.out.println(message);
                    input = sc.nextLine();
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Event's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                    input = sc.nextLine();
                }
            } else {
                sb.append(border + "\n");
                sb.append("Unable to understand. Invalid Input. \n");
                sb.append(border + "\n");
                System.out.println(sb.toString());
                sb.setLength(0);
                input = sc.nextLine();
            }

        }


        storeCurrent(items);

        //Concluding Message
        sb.append(border + "\n");
        sb.append("Bye. Hope to see you again soon!\n");
        sb.append(border + "\n");
        String conclude = sb.toString();
        System.out.println(conclude);

    }

    /**
     * Message generator for task with indentation and border.
     *
     *
     */
    private static String generateMessage(Task current) {
        StringBuilder sb = new StringBuilder();
        sb.append(border + "\n");
        sb.append("Got it. I've added this task: \n");
        sb.append(current + "\n");
        sb.append("Now you have " + items.size() + " tasks in the list.\n");
        sb.append(border + "\n");
        return sb.toString();
    }

    private static void loadExisting(Scanner stored) {
        StringBuilder sb = new StringBuilder();
        while (stored.hasNextLine()) {
            String input = stored.nextLine();
            if (input.toLowerCase().contains("[t]")) {
                //adding an item
                try {
                    Todo newTask = new Todo(input.substring(7, input.length() - 1));
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Todo must have valid description\n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }
            } else if (input.toLowerCase().contains("[d]")) {
                try {
                    int date = input.indexOf("(by");
                    int close = input.indexOf(")");
                    //split input into [deadline] [description] [date]
                    Deadline newTask = new Deadline(input.substring(7, date), input.substring(date + 5, close));
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Deadline's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }

            } else if (input.toLowerCase().contains("[e]")) {
                try {
                    int time = input.indexOf("(at");
                    int close = input.indexOf(")");
                    //split input into [event] [description] [timing]
                    Event newTask = new Event(input.substring(7, time), input.substring(time + 5, close));
                    if (Integer.parseInt(input.substring(input.length() - 1)) == 1) {
                        newTask.markAsDone();
                    }
                    items.add(newTask);
                } catch (StringIndexOutOfBoundsException e) {
                    sb.append(border + "\n");
                    sb.append("Invalid Event's arguments \n");
                    sb.append(border + "\n");
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    private static void storeCurrent(ArrayList<Task> inputs) {
        try {
            File file = new File("/Users/teojunhong/JavaProject/2103T/duke/savedList.txt");
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fileWriter);
            for (Task input : inputs) {
                int status = 0;
                if (input.isDone) {
                    status = 1;
                }
                pw.println(input + " " + status);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

