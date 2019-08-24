import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private static int totalTasks = 0;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParseException {
        File f = new File("data/duke.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();

        readFile(new FileReader(f));
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                listTasks(taskList);
            } else if (input.contains("done")) {
                String[] splitInputs = input.split(" ");
                int index = Integer.parseInt(splitInputs[1]) - 1;
                taskList.get(index).markAsDone();
            } else if (!input.equals("bye")) {
                try {
                    handleTask(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                exit();
                break;
            }
        }
        FileWriter fw = new FileWriter("data/duke.txt");
        for(int i = 0; i < taskList.size(); i++) {
            if(i == 0) {
                fw.write(taskList.get(i).writeToFile());
            } else {
                fw.write(System.lineSeparator() + taskList.get(i).writeToFile());
            }
        }
        fw.close();
    }

    public static void greet() {
        String message = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(message);
    }

    public static void echo(String s) {
        System.out.println("added: " + s);
    }

    public static void exit() {
        String byeMessage = "Bye. Hope to see you again soon!";
        System.out.println(byeMessage);
    }

    public static void listTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; (j < list.size()) && list.get(j) != null; j++) {
            System.out.println(j + 1 + "." + list.get(j));
        }
    }

    public static void handleTask(String task) throws EmptyDescriptionException, InvalidInputException, ParseException {
        Task t = null;
        String[] detailsArray = task.split(" ");
        if (detailsArray.length == 1 && (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline") ||
                detailsArray[0].equals("event"))) {
            throw new EmptyDescriptionException("☹ OOPS!!! The description of a " + detailsArray[0] + " cannot be empty.");
        } else if (detailsArray[0].equals("todo") || detailsArray[0].equals("deadline") ||
                detailsArray[0].equals("event")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            System.out.println("Got it. I've added this task:");
            if (detailsArray[0].equals("todo")) {
                String description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, detailsArray.length));
                t = new Todo(description);
                taskList.add(t);
                totalTasks++;
            } else if (detailsArray[0].equals("deadline")) {
                Date dateTime = null;
                String description = "";
                for (int i = 0; i < detailsArray.length; i++) {
                    if (detailsArray[i].equals("/by")) {
                        dateTime = dateFormat.parse(String.join(" ",
                                Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                        description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                        break;
                    }
                }
                t = new Deadline(description, dateTime);
                taskList.add(t);
                totalTasks++;
            } else if (detailsArray[0].equals("event")) {
                Date dateTime = null;
                String description = "";
                for (int i = 0; i < detailsArray.length; i++) {
                    if (detailsArray[i].equals("/at")) {
                        dateTime = dateFormat.parse(String.join(" ",
                                Arrays.copyOfRange(detailsArray, i + 1, detailsArray.length)));
                        description = String.join(" ", Arrays.copyOfRange(detailsArray, 1, i));
                        break;
                    }
                }
                t = new Event(description, dateTime);
                taskList.add(t);
                totalTasks++;
            }
            System.out.println("  " + t);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else if(detailsArray[0].equals("delete")) {
            Task toDelete = taskList.get(Integer.parseInt(detailsArray[1]) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + toDelete);
            taskList.remove(toDelete);
            totalTasks--;
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } else {
            throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void readFile(FileReader fr) throws IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        //SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");

        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while((line = br.readLine()) != null) {
            Task t = null;
            String[] splitArr = line.split(" [|] ");
            if(splitArr[0].equals("T")) {
                t = new Todo(splitArr[2]);
            } else if(splitArr[0].equals("E")) {
                Date dateTime = dateFormat.parse(splitArr[3]);
                t = new Event(splitArr[2], dateTime);
            } else if(splitArr[0].equals("D")){
                Date dateTime = dateFormat.parse(splitArr[3]);
                t = new Deadline(splitArr[2], dateTime);
            }
            if(splitArr[1].equals("1")) {
                t.updateDone();
            }
            taskList.add(t);
        }
    }
}




