package UI;

import Data.Command;
import Data.CommandType;
import Data.Parser;
import Data.Storage;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidInputException;
import Exceptions.MissingInputException;
import Task.Task;
import Task.TaskList;

import java.util.Scanner;

public class UI {

    private Parser parser = new Parser();
    private TaskList tasks = new TaskList();
    private boolean isExit = false;
    private Storage storage;
    private Scanner sc = new Scanner(System.in);

    /**
     * Processes commands from the user to interact with given file.
     *
     * @param fileInput String that indicates file path.
     */
    public UI(String fileInput) {
        storage = new Storage(fileInput);
    }

    /**
     * Exits program in Duke by changing boolean.
     */
    private void exit() {
        isExit = true;
    }

    /**
     * @return boolean to indicate whether program should be exited.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Processes file in storage and adds tasks to program.
     */
    public void processFile() throws MissingInputException, InvalidInputException {
        for (Task task: storage.loadTasks().getTaskList()) {
            tasks.loadTask(task);
        }
    }

    /**
     * Processes input from the Command Line made by user.
     * This makes changes to the program's task list and file.
     */
    public void processInput() {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            processCommand(parser.process(line));
        }
    }

    /**
     *
     */
    public String processInput(String input) {
        String output = processCommand(parser.process(input));
        storage.updateTaskList(tasks);
        return output;
    }


    /**
     * Intermediate method to process command.
     * This updates and writes the file.
     *
     * @param command command created from parser.
     */
    private String processCommand(Command command) {
           try {
                switch (command.type) {
                case EXIT:
                    exit();
                    return "Bye!";
                case PRINTLIST:
                    return tasks.getList();
                case ADD:
                    return tasks.addTask(parser.createTask(command));
                case DELETE:
                    return tasks.deleteTask(parser.getTaskNo(command));
                case DONE:
                    return tasks.setDone(parser.getTaskNo(command));
                case FIND:
                    return tasks.findMatchingTasks(parser.getKeyword(command));
                case UPDATE:
                    return tasks.updateTask(parser.getUpdateInfo(command));
                default:
                    assert command.type.equals(CommandType.INVALID); //cases should always fall up to Invalid case.
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | InvalidInputException | MissingInputException e) {
                return e.getErrorMessage();
            }
        }
    }
