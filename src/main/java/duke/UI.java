package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    Parser parser = new Parser();
    TaskList tasks = new TaskList();
    boolean isExit = false;
    private Storage storage;
    List<Command> commands = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public UI(String fileInput) {
        storage = new Storage(fileInput);
    }

    public void exit() {
        isExit = true;
    }

    public boolean isExit() {
        return isExit;
    }

    public void processFile() {
        for (Task task: storage.loadTasks().taskList) {
            tasks.loadTask(task);
        }
    }

    public void processInput() {
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            processCommand(parser.process(line));
        }
    }

    public void processCommand(Command command) {
            try {
                switch (command.type) {
                    case EXIT:
                        exit();
                        break;
                    case PRINTLIST:
                        tasks.printList();
                        break;
                    case ADD:
                        tasks.addTask(parser.createTask(command.command));
                        break;
                    case DELETE:
                        tasks.deleteTask(parser.getTaskNo(command.command));
                        break;
                    case DONE:
                        tasks.setDone(parser.getTaskNo(command.command));
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                e.printError();
            }
            try {
                storage.updateTaskList(tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong!");
            }

        }
    }
