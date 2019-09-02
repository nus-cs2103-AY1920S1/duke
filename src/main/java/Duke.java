import duke.DukeException.DukeException;
import duke.command.Command;
import duke.command.Parser;
import duke.taskHandler.Storage;
import duke.taskHandler.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is the main class that launches the chat bot when it is run.
 */
public class Duke {

    /**
     * This is the path of the file where the tasks are saved to and loaded from.
     */
    private String filePath;

    /**
     * This is the storage class which does the saving and loading of the files.
     */
    private Storage storage;

    /**
     * This is the task list class that contains the list of tasks.
     */
    private TaskList tasks;

    /**
     * The ui class handles the output to the user.
     */
    private Ui ui;

    /**
     * Creates a new chat bot with a specified file path.
     * If the specified file contains pre-saved tasks, it will be loaded.
     * @param filePath The path of the file that is used to save and load the tasks.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Scanner sc = new Scanner(System.in);
                String fullCommand = sc.nextLine();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks.getList(), ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                System.out.println("☹ OOPS!!! The command is invalid");
            }
        }
    }

//    public void run() throws IOException {
//        this.ui.showLogo();
//        Scanner sc = new Scanner(System.in);
//        boolean isChatting = true;
//
//        while (isChatting) {
//            Parser parser = new Parser(sc.nextLine());
//            this.ui.printLine();
//            switch (parser.command) {
//            case "done":
//                try {
//                    if (parser.input.equals("done")) {
//                        throw new DukeException("☹ OOPS!!! Please specify the task number that you want to mark as done.");
//                    } else if (parser.inputArr.length > 2) {
//                        throw new DukeException("☹ OOPS!!! The command format is invalid.");
//                    }
//                    int num = Integer.parseInt(parser.inputArr[1]);
//                    Task task = this.tasks.list.get(num - 1);
//                    task.markAsDone();
//                    this.ui.showDoneResponse();
//                    this.ui.printLine();
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("    ☹ OOPS!!! This task number does not exist.");
//                } catch (NumberFormatException e) {
//                    System.out.println("    ☹ OOPS!!! Please specify the task number that you want to mark as done.");
//                } catch (Exception e) {
//                    System.out.println("    " + e);
//                }
//                break;
//            case "delete":
//                try {
//                    if (parser.input.equals("done")) {
//                        throw new DukeException("☹ OOPS!!! Please specify the task number that you want to delete.");
//                    } else if (parser.inputArr.length > 2) {
//                        throw new DukeException("☹ OOPS!!! The command format is invalid.");
//                    }
//                    int num = Integer.parseInt(parser.inputArr[1]);
//                    Task deletedTask = this.tasks.list.remove(num - 1);
//                    this.ui.showDeleteResponse(deletedTask);
//                    this.ui.showTotalNumberTasks(this.tasks.list);
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("    ☹ OOPS!!! This task number does not exist.");
//                } catch (NumberFormatException e) {
//                    System.out.println("    ☹ OOPS!!! Please specify the task number that you want to mark as done.");
//                } catch (Exception e) {
//                    System.out.println("    " + e);
//                }
//                break;
//            case "event":
//                try {
//                    parser.parseEvent();
//                    if (parser.input.equals("event")) {
//                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
//                    } else if (parser.eventCommand.length == 1) {
//                        throw new DukeException("☹ OOPS!!! Please specify where the event is.");
//                    }
//                    Task eventTask = new Event(parser.eventDescript, parser.at);
//                    this.tasks.list.add(eventTask);
//                    this.ui.printAddedTask(eventTask, this.tasks.list);
//                } catch (DukeException de) {
//                    System.out.println("    " + de);
//                } catch (Exception e) {
//                    System.out.println("     ☹ OOPS!!! That is an invalid command.");
//                }
//                break;
//            case "deadline":
//                try {
//                    parser.parseDeadline();
//                    if (parser.input.equals("deadline")) {
//                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
//                    } else if (parser.deadlineCommand.length == 1) {
//                        throw new DukeException("☹ OOPS!!! Please specify when the deadline is.");
//                    }
//                    Task deadlineTask = new Deadline(parser.deadlineDescript, parser.by);
//                    this.tasks.list.add(deadlineTask);
//                    this.ui.printAddedTask(deadlineTask, this.tasks.list);
//                } catch (DukeException de) {
//                    System.out.println("    " + de);
//                } catch (Exception e) {
//                    System.out.println("    ☹ OOPS!!! That is gan invalid command.");
//                }
//                break;
//            default:
//                try {
//                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
//                } catch (Exception e) {
//                    System.out.println("    " + e);
//                }
//            }
//            this.ui.printLine();
//        }
//    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}

