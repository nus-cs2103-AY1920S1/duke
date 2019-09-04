import java.io.IOException;
import java.text.SimpleDateFormat;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private int tracker;

    /**
     * Represents the Main code for the Duke chatbot.
     * Will load a list of tasks from the .txt file and modify it
     * as the user keys in new tasks and manipulates existing ones
     * @throws IOException thrown if unable to load file
     */
    public Duke() throws IOException {
        String filePath = "data/duke.txt";
        ui = new UI();
        storage = new Storage(filePath);
        try {
            if (storage.load() != null) {
                tasks = new TaskList(storage.load());
                tracker = tasks.getListSize();
            } else {
                throw new DukeException("Unable to load data from file");
            }
        } catch (DukeException exp) {
            ui.showErrorMsg(exp.getMessage());
        }
    }

    /**
     * Get response from Duke.
     */
    public String getResponse(String echo) {
        Command cmd = (new Parser()).parse(echo);
        try {
            if (echo.equals("bye")) {
                return ui.showGoodbye();
            } else if (echo.equals("list")) {
                return ui.showTasksInList(tasks);
            } else if (cmd.getCommandType().equals("find")) {
                return ui.showFoundItems(cmd.getInstruction(), tasks);
            } else if (cmd.getCommandType().equals("done")) {
                try {
                    if (cmd.getInstruction().equals("")) {
                        throw new DukeException("☹ OOPS!!! Please specify which task is done");
                    } else if (Integer.parseInt(cmd.getInstruction()) > tasks.getListSize()) {
                        throw new DukeException("☹ OOPS!!! Task " + cmd.getInstruction() + " does not exist");
                    } else {
                        tasks.markAsDone(Integer.parseInt(cmd.getInstruction()) - 1);
                        try {
                            storage.update(tasks.getList());
                            return ui.showTaskIsDoneMsg(tasks.getItemAtIndex(Integer.parseInt(cmd.getInstruction()) - 1));
                        } catch (Exception exp) {
                            return ui.showErrorMsg(exp.getMessage());
                        }
                    }
                } catch (DukeException exp) {
                    return ui.showErrorMsg(exp.getMessage());
                }

            } else if (cmd.getCommandType().equals("delete")) {
                try {
                    if (cmd.getInstruction().equals("")) {
                        throw new DukeException("☹ OOPS!!! Please specify which task is to be deleted");
                    } else if (Integer.parseInt(cmd.getInstruction()) > tasks.getListSize()) {
                        throw new DukeException("☹ OOPS!!! Task " + cmd.getInstruction() + " does not exist");
                    } else {
                        Task tsk = tasks.removeFromList(Integer.parseInt(cmd.getInstruction()) - 1);
                        try {
                            storage.update(tasks.getList());
                            return ui.showTaskDeletedMsg(tsk, tasks.getListSize());
                        } catch (Exception exp) {
                            return ui.showErrorMsg(exp.getMessage());
                        }
                    }
                } catch (DukeException exp) {
                    return ui.showErrorMsg(exp.getMessage());
                }

            } else if (cmd.getCommandType().equals("deadline") || cmd.getCommandType().equals("todo")
                    || cmd.getCommandType().equals("event")) {
                if (cmd.getCommandType().equals("deadline")) {
                    try {
                        if (cmd.getInstruction().equals("") || cmd.getInstruction().equals("deadline")) {
                            throw new DukeException("☹ OOPS!!! The description of deadline cannot be empty");
                        } else if (!echo.contains("/by")) {
                            throw new DukeException("☹ OOPS!!! The deadline must be completed "
                                    + "by a certain date");
                        } else {
                            try {
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                tasks.addItemToList(new Deadline(cmd.getInstruction(),
                                        formatter.parse(cmd.getDate())));
                            } catch (java.text.ParseException exp) {
                                return exp.getMessage();
                            }
                            try {
                                storage.append(tasks.getLastItem());
                            } catch (Exception exp) {
                                return ui.showErrorMsg(exp.getMessage());
                            }
                        }
                    } catch (DukeException exp) {
                        return ui.showErrorMsg(exp.getMessage());
                    }
                } else if (cmd.getCommandType().equals("todo")) {
                    try {
                        if (cmd.getInstruction().equals("") || cmd.getInstruction().equals("todo")) {
                            throw new DukeException("☹ OOPS!!! The description of todo cannot be empty");
                        } else {
                            tasks.addItemToList(new Todo(cmd.getInstruction()));
                            try {
                                storage.append(tasks.getLastItem());
                            } catch (Exception exp) {
                                return ui.showErrorMsg(exp.getMessage());
                            }
                        }
                    } catch (DukeException exp) {
                        return ui.showErrorMsg(exp.getMessage());
                    }
                } else if (cmd.getCommandType().equals("event") || cmd.getInstruction().equals("event")) {
                    try {
                        if (cmd.getInstruction().equals("")) {
                            throw new DukeException("☹ OOPS!!! The description of event cannot be empty");
                        } else if (!echo.contains("/at")) {
                            throw new DukeException("☹ OOPS!!! The event must be at by a certain date");
                        } else {
                            try {
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                                tasks.addItemToList(new Event(cmd.getInstruction(),
                                        formatter.parse(cmd.getDate())));
                            } catch (java.text.ParseException exp) {
                                return exp.getMessage();
                            }
                            try {
                                storage.append(tasks.getLastItem());
                            } catch (Exception exp) {
                                return ui.showErrorMsg(exp.getMessage());
                            }

                        }
                    } catch (DukeException exp) {
                        return ui.showErrorMsg(exp.getMessage());
                    }
                }

                if (tasks.getListSize() > tracker) {
                    tracker = tasks.getListSize();
                    return ui.showTaskAddedMsg(tasks.getLastItem(), tasks.getListSize());
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException exp) {
            return ui.showErrorMsg(exp.getMessage());
        }
        return "☹ OOPS!!! That did not go according to plan";
    }

}