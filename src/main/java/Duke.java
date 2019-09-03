import textfiles.ReadFile;
import textfiles.WriteFile;
import textfiles.Storage;

import duke.dukeinterface.DukeException;
import duke.dukeinterface.Parser;
import duke.dukeinterface.Ui;
import duke.dukeinterface.Tasklist;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Keeps track of the list of tasks the user have.
 */
public class Duke {
    /**
     *  Main method which decides the course of action base on the
     *  commands users give.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Tasklist taskList = new Tasklist();
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        ArrayList<String> textArr;
        String filename = "../../../data/duke.txt";
        Storage file = new ReadFile(filename);
        WriteFile data = new WriteFile(filename);

        ui.greet();
        try {
            //noinspection CastCanBeRemovedNarrowingVariableType
            textArr = ((ReadFile) file).openFile();
            taskList.loadEvents(textArr);
        } catch (IOException e) {
            file.ioErrorMessage();
            return;
        }

        while (run && sc.hasNext()) {
            String command = sc.nextLine();
            String[] commandArr = command.split(" ");

            try {
                parser.checkCommand(commandArr);
                switch (commandArr[0]) {
                case "list":
                    taskList.printArray();
                    break;

                case "done":
                    int indexDone = Integer.parseInt(commandArr[1]) - 1;
                    Task currTask = taskList.get(indexDone);
                    currTask.markAsDone();
                    taskList.taskComplete(currTask);
                    data.replaceNthLine(filename, indexDone, currTask);
                    break;

                case "delete":
                    int index = Integer.parseInt(commandArr[1]) - 1;
                    taskList.deleteComplete(taskList.size(), taskList.get(index));
                    taskList.remove(index);
                    data.removeNthLine(filename, index);
                    break;

                case "todo":
                    String todoDescription = taskList.getDescription(commandArr);
                    Task todo = new ToDo(todoDescription);
                    taskList.add(todo);
                    data.writeToFile("T | ✘ | " + todoDescription);
                    taskList.printTask(todo, taskList.size());
                    break;

                case "event":
                    String eventDescription = taskList.getDescription(commandArr);
                    String eventTime = taskList.getTime(commandArr);
                    eventTime = parser.checkTime(eventTime);
                    Task event = new Event(eventDescription, eventTime);
                    taskList.add(event);
                    data.writeToFile("E | ✘ | " + eventDescription + " | " + eventTime);
                    taskList.printTask(event, taskList.size());
                    break;

                case "find":
                    taskList.searchKeyword(commandArr);
                    break;

                case "deadline":
                    String deadlineDescription = taskList.getDescription(commandArr);
                    String deadlineTime = taskList.getTime(commandArr);
                    deadlineTime = parser.checkTime(deadlineTime);
                    Task deadline = new Deadline(deadlineDescription, deadlineTime);
                    taskList.add(deadline);
                    data.writeToFile("D | ✘ | " + deadlineDescription + " | "
                            + deadlineTime);
                    taskList.printTask(deadline, taskList.size());
                    break;

                case "bye":
                    ui.exit();
                    run = false;
                    break;

                default:
                    Task task = new Task(command);
                    taskList.add(task);
                    ui.echo("added: " + command);
                    break;
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (IndexOutOfBoundsException ex) {
                ui.indexErrorMessage(taskList.size());
            } catch (NumberFormatException ex) {
                ui.numberErrorMessage();
            } catch (IOException ex) {
                data.ioErrorMessage();
            }
        }

        sc.close();
    }
}
