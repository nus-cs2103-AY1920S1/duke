package duke.main;

import duke.exception.EmptyTaskListException;
import duke.note.Note;
import duke.task.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Scans input from the user and prints feedback to the user.
 */
public class Ui {
    private String helloPhrase = "Hello! I'm Duke\nWhat can I do for you?";
    
    private String byePhrase = "Bye. Hope to see you again soon!";
    
    /**
     * Scanner object used for reading user input.
     */
    private Scanner scanner;
    
    /**
     * Creates a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    
    public void print(String string) {
        System.out.println(string);
    }
    
    /**
     * Scans the next input line by the user.
     *
     * @return Returns the entire input line as a String.
     */
    public String getNextLine() {
        return scanner.nextLine();
    }
    
    /**
     * Shows the error message of an Exception.
     *
     * @param exception The Exception to display the error message of.
     * @return Returns the error message as a String.
     */
    public String showError(Exception exception) {
        return exception.getMessage();
    }
    
    /**
     * Shows a welcome message for the user.
     *
     * @return Returns the message String.
     */
    public String showHello() {
        return helloPhrase;
    }
    
    /**
     * Prints a goodbye message for the user.
     *
     * @return Returns the message String.
     */
    public String showBye() {
        return byePhrase;
    }
    
    public String showList(TaskList taskList) throws EmptyTaskListException {
        if (taskList.getSize() == 0) {
            throw new EmptyTaskListException("OOPS!!! You have no tasks currently stored in your list!");
        } else {
            String response = "Here are the tasks in your list:";
            for (int i = 0; i < taskList.getSize(); i++) {
                response = response.concat("\n" + (i + 1) + ". " + taskList.getTask(i));
            }
            return response;
        }
    }

    public String showAfterAddingTask(Task currentTask, int currentSize) {
        return "Got it. I've added this task:\n  " + currentTask.toString() + "\nNow you have " + currentSize
                + " tasks in the list.";
    }
    
    public String showAfterDeletingTask(Task currentTask, int currentSize) {
        return "Got it. I've removed this task:\n  " + currentTask.toString() + "\nNow you have " + currentSize
                + " tasks in the list.";
    }
    
    public String showNoteList() {
        try {
            Stream<Path> walk = Files.walk(Paths.get(Note.FILE_BASE_PATH));
            List<String> noteList = walk
                    .map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt"))
                    .map(x -> x.replace(Note.FILE_BASE_PATH, ""))
                    .map(x -> x.replace(".txt", ""))
                    .collect(Collectors.toList());
            String response = "";
            for (int i = 0; i < noteList.size(); i++) {
                response = response.concat((i + 1) + ": " + noteList.get(i) + "\n");
            }
            if (response.isBlank()) {
                return "You have no notes stored currently!";
            } else {
                return "Here are the note titles that you have:\n" + response;
            }
        } catch (IOException e) {
            return this.showError(e);
        }
    }
    
    public String showAfterWritingNote(String noteName) {
        return "New note added with name: '" + noteName + "'";
    }
    
    public String showAfterReadingNote(String noteName, String noteContents) {
        return "Note name: '" + noteName + "'\nNote contents: \n" + noteContents;
    }
    
    public String showAfterDeletingNote(String noteName) {
        return "Note deleted with name: '" + noteName + "'";
    }
}
