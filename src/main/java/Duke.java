import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> task = new ArrayList<>();
    private int counter = 0;
    private Storage storage;
    private Ui ui;

    public Duke() {
        storage = new Storage("data/duke.txt");
        ui = new Ui();
    }

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
            task = storage.readData();
            counter = task.size();
        } catch (FileNotFoundException e) {
            ui.printException(e);
        }

        Scanner sc = new Scanner(System.in);

        ui.startOfInteractions();

        String textInput = sc.nextLine();
        while (!textInput.equals("bye")) {
            try {
                if (textInput.equals("list")) {
                    ui.printList(task, counter);
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
                    ui.taskDone(markAsDoneTask);
                } else if (textInput.startsWith("todo")) {
                    if (textInput.equals("todo") || textInput.equals("todo ")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = textInput.replaceFirst("todo ", "");
                    task.add(new Todo(description));
                    counter = ui.printAddedTask(task, counter);
                } else if (textInput.startsWith("deadline")) {
                    if (textInput.equals("deadline") || textInput.equals("deadline ")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String removeTaskWord = textInput.replaceFirst("deadline ", "");
                    String[] taskSplit = removeTaskWord.split(" /by ");
                    task.add(new Deadline(taskSplit[0], taskSplit[1]));
                    counter = ui.printAddedTask(task, counter);
                } else if (textInput.startsWith("event")) {
                    if (textInput.equals("event") || textInput.equals("event ")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }

                    String removeTaskWord = textInput.replaceFirst("event ", "");
                    String[] taskSplit = removeTaskWord.split(" /at ");
                    task.add(new Event(taskSplit[0], taskSplit[1]));
                    counter = ui.printAddedTask(task, counter);
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
                    ui.deleteTask(deletedTask, counter);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.printException(e);
            } finally {
                textInput = sc.nextLine();
            }
        }
        ui.endOfInteractions();

        try {
            storage.writeData(task);
        } catch (IOException e) {
            ui.printException(e);
        }

        // Close the scanner
        sc.close();
    }
}
