import java.io.IOException;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Represents the Main code for the Duke chatbot.
     * Will load a list of tasks from the .txt file and modify it
     * as the user keys in new tasks and manipulates existing ones
     * @param filePath refers to the path of the .txt file
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            if(storage.load() != null) {
                tasks = new TaskList(storage.load());
            } else {
                throw new DukeException("Unable to load data from file");
            }
        } catch (DukeException exp) {
            ui.showErrorMsg(exp.getMessage());
        } finally {

        }
    }

    public void run() {

        ui.showGreeting();

        Scanner sc = new Scanner(System.in);
        String echo = "";
        int tracker = tasks.getListSize();

        if (sc.hasNextLine()) {
            echo = sc.nextLine();
            while (!echo.equals("bye")) {
                Command c = (new Parser()).parse(echo);
                ui.showLine();
                try {
                    if (echo.equals("list")) {
                        ui.showTasksInList(tasks);
                    } else if (c.getCommandType().equals("find")) {
                      ui.showFoundItems(c.getInstruction(), tasks);
                    } else if (c.getCommandType().equals("done")) {
                        try {
                            if(c.getInstruction().equals("")) {
                                throw new DukeException("☹ OOPS!!! Please specify which task is done");
                            } else if (Integer.parseInt(c.getInstruction()) > tasks.getListSize()) {
                                throw new DukeException("☹ OOPS!!! Task " + c.getInstruction() + " does not exist");
                            } else {
                                tasks.markAsDone(Integer.parseInt(c.getInstruction()) - 1);
                                ui.showTaskIsDoneMsg(tasks.getItemAtIndex(Integer.parseInt(c.getInstruction()) - 1));
                                try {
                                    storage.update(tasks.getList());
                                } catch (Exception exp) {
                                    ui.showErrorMsg(exp.getMessage());
                                }
                            }
                        } catch (DukeException exp) {
                            ui.showErrorMsg(exp.getMessage());
                        }

                    } else if (c.getCommandType().equals("delete")) {
                        try {
                            if(c.getInstruction().equals("")) {
                                throw new DukeException("☹ OOPS!!! Please specify which task is to be deleted");
                            } else if (Integer.parseInt(c.getInstruction()) > tasks.getListSize()) {
                                throw new DukeException("☹ OOPS!!! Task " + c.getInstruction() + " does not exist");
                            } else {
                                Task tsk = tasks.removeFromList(Integer.parseInt(c.getInstruction()) - 1);
                                ui.showTaskDeletedMsg(tsk, tasks.getListSize());
                                try {
                                    storage.update(tasks.getList());
                                } catch (Exception exp) {
                                    ui.showErrorMsg(exp.getMessage());
                                }
                            }
                        } catch (DukeException exp) {
                            ui.showErrorMsg(exp.getMessage());
                        }

                    } else if (c.getCommandType().equals("deadline") || c.getCommandType().equals("todo") || c.getCommandType().equals("event")) {
                        if (c.getCommandType().equals("deadline")) {
                            try {
                                if (c.getInstruction().equals("") || c.getInstruction().equals("deadline")) {
                                    throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                                } else if (!echo.contains("/by")) {
                                    throw new DukeException("☹ OOPS!!! The deadline must be completed by a certain date");
                                } else {
                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                        tasks.addItemToList(new Deadline(c.getInstruction(),
                                                formatter.parse(c.getDate())));
                                    } catch (java.text.ParseException exp) {
                                        exp.printStackTrace();
                                        break;
                                    }
                                    try {
                                        storage.append(tasks.getLastItem());
                                    } catch (Exception exp) {
                                        ui.showErrorMsg(exp.getMessage());
                                    }
                                }
                            } catch (DukeException exp) {
                                ui.showErrorMsg(exp.getMessage());
                            }
                        } else if (c.getCommandType().equals("todo")) {
                            try {
                                if (c.getInstruction().equals("") || c.getInstruction().equals("todo")) {
                                    throw new DukeException("☹ OOPS!!! The description of todo cannot be empty");
                                } else {
                                    tasks.addItemToList(new Todo(c.getInstruction()));
                                    try {
                                        storage.append(tasks.getLastItem());
                                    } catch (Exception exp) {
                                        ui.showErrorMsg(exp.getMessage());
                                    }
                                }
                            } catch (DukeException exp) {
                                ui.showErrorMsg(exp.getMessage());
                            }
                        } else if (c.getCommandType().equals("event") || c.getInstruction().equals("event")) {
                            try {
                                if (c.getInstruction().equals("")) {
                                    throw new DukeException("☹ OOPS!!! The description of event cannot be empty");
                                } else if (!echo.contains("/at")) {
                                    throw new DukeException("☹ OOPS!!! The event must be at by a certain date");
                                } else {
                                    try {
                                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                        tasks.addItemToList(new Event(c.getInstruction(),
                                                formatter.parse(c.getDate())));
                                    } catch (java.text.ParseException exp) {
                                        exp.printStackTrace();
                                        break;
                                    }
                                    try {
                                        storage.append(tasks.getLastItem());
                                    } catch (Exception exp) {
                                        ui.showErrorMsg(exp.getMessage());
                                    }

                                }
                            } catch (DukeException exp) {
                                ui.showErrorMsg(exp.getMessage());
                            }
                        }

                        if (tasks.getListSize() > tracker) {
                            ui.showTaskAddedMsg(tasks.getLastItem(), tasks.getListSize());
                            tracker = tasks.getListSize();
                        }
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException exp) {
                    ui.showErrorMsg(exp.getMessage());
                }

                ui.showLine();
                if (sc.hasNextLine()) {
                    echo = sc.nextLine();
                } else {
                    break;
                }
            }
        }

        if (echo.equals("bye")) {
            ui.showGoodbye();
        }
        sc.close();
    }

    /**
     * The Main function for the Duke chatbot
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        new Duke("../../../data/duke.txt").run();
    }
}