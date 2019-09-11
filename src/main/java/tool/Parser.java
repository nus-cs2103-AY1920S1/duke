package tool;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


public class Parser {
    protected TaskList commands;
    protected Storage storage;
    protected Ui ui;


    public Parser(TaskList commands, Storage storage, Ui ui) {
        this.commands = commands;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses the user's input to make sense of all the commands, and hands it over
     * to tool.TaskList
     * @param command
     */
    public String parse(String command) {
            String[] inputArr = command.split(" ");
            String userCommand = inputArr[0];
            String dukeText;
            try {
                Boolean b = false;
                String assertOutput = "Did not use storage";
                if (userCommand.equals("bye")) {
                    dukeText = this.ui.bye();
                    b = this.storage.close(this.commands);
                    assertOutput = "Did not close";
                } else if (userCommand.equals("list")) {
                    dukeText = this.ui.list() + "\n" + this.commands.list();
                } else if (userCommand.equals("done")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task doneTask = this.commands.done(index);
                            dukeText = this.ui.done(doneTask);
                            b = storage.done(doneTask, index + 1);
                            assertOutput = "Not done";
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for done does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for done cannot be empty.");
                    }
                } else if (userCommand.equals("deadline")) {
                    try {
                        String dL = command.split(" ", 2)[1];
                        try {
                            String[] taskDeadLine = dL.split(" /by ");
                            String taskD = taskDeadLine[0];
                            String by = taskDeadLine[1];
                            Task tt = new Deadline(taskD, new DateTime(by));
                            this.commands.add(tt);
                            dukeText = this.ui.addTask(tt, this.commands.size());
                            b = storage.save(tt);
                            assertOutput = "Did not save";
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for deadline is wrong. Please follow: <description> /by <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                    }
                } else if (userCommand.equals("event")) {
                    try {
                        String eEvent = command.split(" ", 2)[1];
                        try {
                            String[] taskEvent = eEvent.split(" /at ");
                            String taskE = taskEvent[0];
                            String at = taskEvent[1];
                            Task ee = new Event(taskE, new DateTime(at));
                            this.commands.add(ee);
                            dukeText = this.ui.addTask(ee, this.commands.size());
                            b = storage.save(ee);
                            assertOutput = "Did not save";
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! The format for event is wrong. Please follow: <description> /at <time>");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of event cannot be empty.");
                    }
                } else if (userCommand.equals("todo")) {
                    try {
                        String todoT = command.split(" ", 2)[1];
                        Task t = new Todo(todoT);
                        this.commands.add(t);
                        dukeText = this.ui.addTask(t, this.commands.size());
                        b = storage.save(t);
                        assertOutput = "Did not save";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (userCommand.equals("delete")) {
                    try {
                        int i = Integer.parseInt(inputArr[1]) - 1;
                        try {
                            Task tt = this.commands.delete(i);
                            dukeText = this.ui.delete(tt, this.commands.size());
                            b = storage.delete(i + 1);
                            assertOutput = "Did not delete";
                        } catch (IndexOutOfBoundsException e) {
                            throw new DukeException("OOPS!!! Index for delete does not exist in the list.");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("OOPS!!! Index for delete cannot be empty.");
                    }
                } else if (userCommand.equals("find")) {
                    String word = inputArr[1];
                    dukeText = this.ui.find() + "\n" + this.commands.find(word);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                assert b : assertOutput;
            } catch (DukeException e) {
                dukeText = e.getMessage();
            }

            return dukeText;

    }
}
