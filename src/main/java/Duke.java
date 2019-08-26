import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.FileReader;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        System.out.println(lines);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(lines);
        FileReader fr = new FileReader("../Data/Duke.txt");
        BufferedReader brFile = new BufferedReader(fr);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> inputList = obtainTasks(brFile);
        boolean programRunning = true;
        while (programRunning) {
            String[] inputMessage = br.readLine().split(" ");
            System.out.println(lines);
            String taskCount = "";
            if (inputList.size() > 1) {
                taskCount = "tasks";
            } else {
                taskCount = "task";
            }
            try {
                switch (inputMessage[0]) {
                    case "list":
                        if (inputList.size() == 0) {
                            throw new DukeException("     The list is empty!");
                        }
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 1; i <= inputList.size(); i++) {
                            System.out.println("     " + i + ". " + inputList.get(i - 1));
                        }
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "bye":
                        System.out.println("     Bye. Hope to see you again soon!");
                        System.out.println(lines);
                        System.exit(0);
                        break;
                    case "done":
                        int index = Integer.parseInt(inputMessage[1]);
                        if (index > inputList.size() || index <= 0) {
                            throw new DukeException("     Such task does not exist!");
                        }
                        inputList.get(index - 1).completeTask();
                        writeToFile(inputList);
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + inputList.get(index - 1));
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "todo":
                        if (inputMessage.length == 1) {
                            throw new DukeException("     OOPS!! The description of a todo cannot be empty");
                        }
                        System.out.println("     Got it. I've added this task:");
                        String item = "";
                        for (int i = 1; i < inputMessage.length; i++) {
                            if (i == inputMessage.length - 1) {
                                item += inputMessage[i];
                            } else {
                                item += inputMessage[i];
                                item += " ";
                            }
                        }
                        inputList.add(new Todo(item));
                        writeToFile(inputList);
                        System.out.println("       " + inputList.get(inputList.size() - 1));
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "deadline":
                    case "event":
                        String input = "";
                        int marker = 0;
                        for (int i = 1; i < inputMessage.length; i++)  {
                            if (i + 1 >= inputMessage.length) {
                                throw new DukeException("     Please provide more information");
                            }
                            if (inputMessage[i + 1].equals("/by") || inputMessage[i + 1].equals("/at")) {
                                input += inputMessage[i];
                                marker = i + 1;
                                break;
                            } else {
                                input += inputMessage[i];
                                input += " ";
                            }
                        }
                        String extraInfo = "";
                        String tempInfo = "";
                        for (int i = marker + 1; i < inputMessage.length; i ++) {
                            if (i == inputMessage.length - 1) {
                                tempInfo += inputMessage[i];
                            } else {
                                tempInfo += inputMessage[i];
                                tempInfo += " ";
                            }
                        }
                        String[] dateAndTime = tempInfo.split(" ");
                        String[] date = dateAndTime[0].split("/");
                        if (date.length != 3 || dateAndTime.length < 2) {
                            throw new DukeException("     Invalid date and time format!");
                        }
                        if (date[0].equals("") || date[1].equals("") || date[2].equals("")) {
                            throw new DukeException("     Invalid date and time format!");
                        }
                        int day = Integer.parseInt(date[0]);
                        int month = Integer.parseInt(date[1]);
                        int year = Integer.parseInt(date[2]);
                        Date inputDate = new Date(day, month, year);
                        Time inputTime = new Time(Integer.parseInt(dateAndTime[1]));
                        if (!inputDate.isValid()) {
                            throw new DukeException("     Sorry! Invalid date format");
                        }
                        if (!inputTime.isValid()) {
                            throw new DukeException("     Sorry! Invalid time format");
                        }
                        extraInfo = inputDate + ", " + inputTime;
                        if (extraInfo.equals("")) {
                            throw new DukeException("     Please provide more information");
                        }
                        if (inputMessage[0].equals("deadline")) {
                            if (!inputMessage[marker].equals("/by")) {
                                throw new DukeException("     Wrong syntax, should be using /by for deadline");
                            }
                            inputList.add(new Deadline(input, extraInfo));
                        } else if (inputMessage[0].equals("event")) {
                            if (!inputMessage[marker].equals("/at")) {
                                throw new DukeException("     Wrong syntax, should be using /at for event");
                            }
                            inputList.add(new Event(input, extraInfo));
                        }
                        writeToFile(inputList);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + inputList.get(inputList.size() - 1));
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    case "delete" :
                        int indexToDelete = Integer.parseInt(inputMessage[1]);
                        if(indexToDelete > inputList.size() || indexToDelete <= 0) {
                            throw new DukeException("     Such task does not exist!");
                        }
                        Task toBeDeleted = inputList.get(indexToDelete - 1);
                        inputList.remove(indexToDelete - 1);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + toBeDeleted);
                        if (inputList.size() == 1) {
                            taskCount = "task";
                        } else {
                            taskCount = "tasks";
                        }
                        writeToFile(inputList);
                        System.out.println("     Now you have " + inputList.size() + " " + taskCount + " in the list.");
                        System.out.println(lines);
                        System.out.println();
                        break;
                    default:
                        throw new DukeException("     OOPS!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error);
                System.out.println(lines);
                System.out.println();
            } catch (Exception error) {
                System.out.println("     Something went wrong" + error.getMessage());
                System.out.println(lines);
                System.out.println();
            }
        }
    }
    public static ArrayList<Task> obtainTasks(BufferedReader reader) throws Exception {
        ArrayList<Task> output = new ArrayList<Task>();
        String next = reader.readLine();
        while(next != null) {
            String[] input = next.split(" ");
            String status = input[1];
            String type = input[0];
            String description = "";
            String extraInfo = "";
            for (int i = 2; i < input.length; i ++) {
                if (i == input.length - 1) {
                    description += input[i];
                } else {
                    description += input[i];
                    description += " ";
                }
            }
            if (type.equals("todo")) {
                output.add(new Todo(description));
            } else if (type.equals("event")){
                extraInfo = reader.readLine();
                output.add(new Event(description, extraInfo));
            } else if (type.equals("deadline")) {
                extraInfo = reader.readLine();
                output.add(new Deadline(description, extraInfo));
            }
            if (status.equals("done")) {
                output.get(output.size() - 1).completeTask();

            }
            next = reader.readLine();
        }
        return output;
    }
    public static void writeToFile(ArrayList<Task> input) throws Exception {
        FileWriter fw = new FileWriter("../Data/Duke.txt");
        for (Task task : input) {
            String output = "";
            String status = "";
            if (task.getStatus()) {
                status = "done";
            } else {
                status = "pending";
            }
            if (task.getClass().getName().equals("Todo")) {
                output = "todo " + status + " " + task.getDescription();
                fw.write(output + "\n");
            } else if (task.getClass().getName().equals("Event")){
                output = "event " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            } else {
                output = "deadline " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            }
        }
        fw.close();
    }
    public static ArrayList<Task> obtainTasks(BufferedReader reader) throws Exception {
        ArrayList<Task> output = new ArrayList<Task>();
        String next = reader.readLine();
        while(next != null) {
            String[] input = next.split(" ");
            String status = input[1];
            String type = input[0];
            String description = "";
            String extraInfo = "";
            for (int i = 2; i < input.length; i ++) {
                if (i == input.length - 1) {
                    description += input[i];
                } else {
                    description += input[i];
                    description += " ";
                }
            }
            if (type.equals("todo")) {
                output.add(new Todo(description));
            } else if (type.equals("event")){
                extraInfo = reader.readLine();
                output.add(new Event(description, extraInfo));
            } else if (type.equals("deadline")) {
                extraInfo = reader.readLine();
                output.add(new Deadline(description, extraInfo));
            }
            if (status.equals("done")) {
                output.get(output.size() - 1).completeTask();

            }
            next = reader.readLine();
        }
        return output;
    }
    public static void writeToFile(ArrayList<Task> input) throws Exception {
        FileWriter fw = new FileWriter("../Data/Duke.txt");
        for (Task task : input) {
            String output = "";
            String status = "";
            if (task.getStatus()) {
                status = "done";
            } else {
                status = "pending";
            }
            if (task.getClass().getName().equals("Todo")) {
                output = "todo " + status + " " + task.getDescription();
                fw.write(output + "\n");
            } else if (task.getClass().getName().equals("Event")){
                output = "event " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            } else {
                output = "deadline " + status + " " + task.getDescription();
                fw.write(output + "\n");
                fw.write(task.getExtraInfo() + "\n");
            }
        }
        fw.close();
    }
}
