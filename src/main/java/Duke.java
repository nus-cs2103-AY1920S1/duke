import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> task = new ArrayList<>();
    private int counter = 0;

    /**
     * Main method.
     * 
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        try {
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            try {
                if (textInput.equals("list")) {
                    String isPlural = counter == 1 ? "is" : "are";
                    String taskIfPlural = counter == 1 ? "task" : "tasks";
                    System.out.println("Here " + isPlural + " the " + taskIfPlural + " in your list:");
                    for (int i = 1; i <= counter; i++) {
                        System.out.println(i + "." + task.get(i - 1));
                    }
                } else if (textInput.startsWith("done")) {
                    if (textInput.equals("done") || textInput.equals("done ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }

                    int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
                    if (completedIndex < 0 || completedIndex >= counter) {
                        throw new DukeException("OOPS!!! Index not found.");
                    }

                    Task markAsDoneTask = task.get(completedIndex);
                    markAsDoneTask.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markAsDoneTask);
                } else if (textInput.startsWith("todo")) {
                    if (textInput.equals("todo") || textInput.equals("todo ")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = textInput.replaceFirst("todo ", "");
                    task.add(new Todo(description));
                    counter = printAddedTask(task, counter);
                } else if (textInput.startsWith("deadline")) {
                    if (textInput.equals("deadline") || textInput.equals("deadline ")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String removeTaskWord = textInput.replaceFirst("deadline ", "");
                    String[] taskSplit = removeTaskWord.split(" /by ");
                    task.add(new Deadline(taskSplit[0], taskSplit[1]));
                    counter = printAddedTask(task, counter);
                } else if (textInput.startsWith("event")) {
                    if (textInput.equals("event") || textInput.equals("event ")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }

                    String removeTaskWord = textInput.replaceFirst("event ", "");
                    String[] taskSplit = removeTaskWord.split(" /at ");
                    task.add(new Event(taskSplit[0], taskSplit[1]));
                    counter = printAddedTask(task, counter);
                } else if (textInput.startsWith("delete")) {
                    if (textInput.equals("delete") || textInput.equals("delete ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }

                    int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
                    if (deletedIndex < 0 || deletedIndex >= counter) {
                        throw new DukeException("OOPS!!! Index not found.");
                    }
                    Task deletedTask = task.remove(deletedIndex);
                    counter--;

                    String taskIfPlural = counter <= 1 ? "task" : "tasks";
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deletedTask);
                    System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println(e);
            } finally {
                textInput = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

        try {
            writeData();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // Close the scanner
        sc.close();
    }

    private void readData() throws FileNotFoundException {
        File f = new File("data/duke.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String taskLine = sc.nextLine();
            String[] taskLineSplit = taskLine.split(":");
            switch (taskLineSplit[0]) {
            case "T":
                task.add(new Todo(taskLineSplit[2], Integer.parseInt(taskLineSplit[1])));
                counter++;
                break;
            case "E":
                task.add(new Event(taskLineSplit[2], taskLineSplit[3], Integer.parseInt(taskLineSplit[1])));
                counter++;
                break;
            case "D":
                task.add(new Deadline(taskLineSplit[2], taskLineSplit[3], Integer.parseInt(taskLineSplit[1])));
                counter++;
                break;
            }
        }

        // Close the scanner
        sc.close();
    }

    private void writeData() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String stringToWrite = "";
        for (int i = 0; i < counter; i++) {
            Task taskToWrite = task.get(i);
            if (taskToWrite instanceof Todo) {
                stringToWrite += "T:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription();
            } else if (taskToWrite instanceof Event) {
                stringToWrite += "E:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Event e = (Event) taskToWrite;
                stringToWrite += e.getTime();
            } else if (taskToWrite instanceof Deadline) {
                stringToWrite += "D:";
                stringToWrite += writeStatus(taskToWrite);
                stringToWrite += taskToWrite.getDescription() + ":";
                Deadline d = (Deadline) taskToWrite;
                stringToWrite += d.getTime();
            }

            if (i != counter - 1) {
                stringToWrite += System.lineSeparator();
            }
        }
        fw.write(stringToWrite);
        fw.close();
    }

    private String writeStatus(Task t) {
        String statusIcon = t.getStatusIcon();
        if (statusIcon.equals("O")) {
            return "1:";
        } else {
            return "0:";
        }
    }

    private int printAddedTask(ArrayList<Task> task, int counter) {
        System.out.println("Got it. I've added this task:\n" + task.get(counter));
        counter++;
        String taskIfPlural = counter == 1 ? "task" : "tasks";
        System.out.println("Now you have " + counter + " " + taskIfPlural + " in the list.");
        return counter;
    }
}
