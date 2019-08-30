import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private String filePath;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() throws IOException {
        this.ui.showLogo();
        Scanner sc = new Scanner(System.in);
        boolean chat = true;

        while (chat) {
            Parser parser = new Parser(sc.nextLine());
            this.ui.printLine();
            switch (parser.command) {
            case "bye":
                this.ui.showByeResponse();
                this.tasks.save(this.filePath);
                chat = false;
                break;
            case "list":
                if (this.tasks.list.size() == 0) {
                    this.ui.showNoTaskResponse();
                } else {
                    this.ui.showListResponse();
                    this.ui.printTasks(this.tasks);
                }
                break;
            case "done":
                try {
                    if (parser.input.equals("done")) {
                        throw new DukeException("☹ OOPS!!! Please specify the task number that you want to mark as done.");
                    } else if (parser.inputArr.length > 2) {
                        throw new DukeException("☹ OOPS!!! The command format is invalid.");
                    }
                    int num = Integer.parseInt(parser.inputArr[1]);
                    Task task = this.tasks.getTask(num - 1);
                    task.markAsDone();
                    this.ui.showDoneResponse();
                    this.ui.printLine();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ☹ OOPS!!! This task number does not exist.");
                } catch (NumberFormatException e) {
                    System.out.println("    ☹ OOPS!!! Please specify the task number that you want to mark as done.");
                } catch (Exception e) {
                    System.out.println("    " + e);
                }
                break;
            case "delete":
                try {
                    if (parser.input.equals("done")) {
                        throw new DukeException("☹ OOPS!!! Please specify the task number that you want to delete.");
                    } else if (parser.inputArr.length > 2) {
                        throw new DukeException("☹ OOPS!!! The command format is invalid.");
                    }
                    int num = Integer.parseInt(parser.inputArr[1]);
                    Task deletedTask = this.tasks.list.remove(num - 1);
                    this.ui.showDeleteResponse(deletedTask);
                    this.ui.showTotalNumberTasks(this.tasks.list);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    ☹ OOPS!!! This task number does not exist.");
                } catch (NumberFormatException e) {
                    System.out.println("    ☹ OOPS!!! Please specify the task number that you want to mark as done.");
                } catch (Exception e) {
                    System.out.println("    " + e);
                }
                break;
            case "todo":
                try {
                    parser.parseToDo();
                    if (parser.input.equals("todo")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task toDoTask = new ToDo(parser.todoDescript);
                        this.tasks.list.add(toDoTask);
                        this.ui.printAddedTask(toDoTask, this.tasks.list);
                    }
                } catch (DukeException de) {
                    System.out.println("    " + de);
                } catch (Exception e) {
                    System.out.println("    " + e);
                }
                break;
            case "event":
                try {
                    parser.parseEvent();
                    if (parser.input.equals("event")) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (parser.eventCommand.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please specify where the event is.");
                    }
                    Task eventTask = new Event(parser.eventDescript, parser.at);
                    this.tasks.list.add(eventTask);
                    this.ui.printAddedTask(eventTask, this.tasks.list);
                } catch (DukeException de) {
                    System.out.println("    " + de);
                } catch (Exception e) {
                    System.out.println("     ☹ OOPS!!! That is an invalid command.");
                }
                break;
            case "deadline":
                try {
                    parser.parseDeadline();
                    if (parser.input.equals("deadline")) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (parser.deadlineCommand.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please specify when the deadline is.");
                    }
                    Task deadlineTask = new Deadline(parser.deadlineDescript, parser.by);
                    this.tasks.list.add(deadlineTask);
                    this.ui.printAddedTask(deadlineTask, this.tasks.list);
                } catch (DukeException de) {
                    System.out.println("    " + de);
                } catch (Exception e) {
                    System.out.println("    ☹ OOPS!!! That is gan invalid command.");
                }
                break;
            default:
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means.");
                } catch (Exception e) {
                    System.out.println("    " + e);
                }
            }
            this.ui.printLine();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}

