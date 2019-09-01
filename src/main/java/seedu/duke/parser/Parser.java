package seedu.duke.parser;

import seedu.duke.ui.UI;
import seedu.duke.exception.DukeException;
import seedu.duke.task.*;
import java.io.FileWriter;
import java.io.IOException;

/** Represents a command checker to verify if a command is valid. If valid, the command will be
 * executed accordingly.
 */
public class Parser {

    /** Checks if a command is valid and execute command. If command is incomplete, throws
     * ArrayOutOfBoundException. If command is invalid, throws DukeException.
     * @param command Represents the command to be executedd.
     * @param ui Represents the user input Object.
     * @param tasks Represents the TaskList object to add or remove Task objects.
     * @param filePath Represents the file for the tasks to be recorded in.
     */
    public void parse(String command, UI ui, TaskList tasks, String filePath){
        try {
            FileWriter fw = new FileWriter(filePath, true);
            while (!command.equals("bye")) {
                try {
                    String[] parts = command.split(" ", 2);
                    if (command.equals("list")) {
                        printList(tasks, ui);
                    } else if (parts[0].equals("done")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        ui.printReply(tasks.get(taskNum - 1).markAsDone());
                    } else if (parts[0].equals("delete")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        removeFromList(tasks, taskNum, ui);
                    } else if (parts[0].equals("todo")) {
                        addToList(new Todo(parts[1]), tasks, fw, ui);
                    } else if (parts[0].equals("deadline")) {
                        String[] subparts = parts[1].split(" /by ");
                        addToList(new Deadline(subparts[0], (subparts[1])), tasks, fw, ui);
                    } else if (parts[0].equals("event")) {
                        String[] subparts = parts[1].split(" /at ");
                        addToList(new Event(subparts[0], (subparts[1])), tasks, fw, ui);
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    ui.printReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.printReply("OOPS!!! The description of a " + command.split(" ", 2)[0] + " cannot be empty");
                }
                command = ui.readCommand();
            }
            fw.close();
        }catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    /** Prints out all the tasks in the Task list.
     * @param taskList The list that contains/may not contain tasks.
     * @param ui User input that handles the printing.
     */
    public static void printList(TaskList taskList, UI ui){
        String reply = "Here are the tasks in your list:\n\t ";
        for(int i=0; i<taskList.size(); i++) {
            reply += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1) {
                reply += "\n\t ";
            }
        }
        ui.printReply(reply);
    }

    /** Adds a task to the Task list.
     * @param task Task to be added to the list. Prints out a reply when task is added.
     * @param taskList Task list for the task to be added to.
     * @param fw File to record the tasks in Task list.
     * @param ui Handles the printing of reply.
     */
    public static void addToList(Task task, TaskList taskList, FileWriter fw, UI ui) {
        taskList.add(task);
        String reply = "Got it. I've added this task:\n\t  " + task + "\n\tNow you have " + taskList.size()
                + ((taskList.size() == 1) ? " task" : " tasks") + " in the list.";
        ui.printReply(reply);
        String replyToFile = task.toFile();
        try {
            fw.write(replyToFile);
        } catch (IOException e) { }
    }

    /** Removes a task from the Task list. If task index is out of the list, throws DukeException.
     * @param taskList Task list for the task to be removed from.
     * @param taskIndex Index of the task to be removed.
     * @param ui Handles the printing of reply.
     * @throws DukeException If task index is more than the size of Task list.
     */
    public static void removeFromList(TaskList taskList, int taskIndex, UI ui) throws DukeException{
        if(taskIndex > taskList.size()){
            throw new DukeException("");
        }
        String reply = "Noted. I've removed this task:\n\t  " + taskList.remove(taskIndex-1) + "\n\tNow you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        ui.printReply(reply);
    }
}
