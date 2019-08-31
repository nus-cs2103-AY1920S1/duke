package duke;

import duke.command.Command;

import duke.date.DukeDate;

import duke.exception.*;

import duke.module.Parser;
import duke.module.Storage;
import duke.module.TaskList;
import duke.module.Ui;

import duke.task.Task;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.IOException;

import java.util.List;

public class Duke {

    private static final String DUKE_HELLO = "Hello! I'm Duke!\n";
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!\n";
    private static final String DUKE_NO_SAVED_TASKS = "You have no saved tasks!";
    private static final String DUKE_EXISTS_SAVED_TASKS = "You have %d saved tasks!";
    private static final String DUKE_START_COMMAND = "What can I do for you?\n";
    private static final String DUKE_LIST_TASKS = "Here are the tasks in your list:";
    private static final String DUKE_NO_TASKS = "You currently have no tasks in your list.";
    private static final String DUKE_FOUND_TASKS = "Here are the matching tasks in your list:";
    private static final String DUKE_NO_FOUND_TASKS = "There were no matching tasks.";
    private static final String DUKE_MARK_AS_DONE = "Nice! I've marked this task as done:\n";
    private static final String DUKE_ADD_TASK = "Got it. I've added this task:\n";
    private static final String DUKE_DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String DUKE_DELETE_ALL_TASKS = "Noted. I've removed all tasks.";
    private static final String DUKE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    private static final String DUKE_LINE = "    ____________________________________________________________\n";
    private static final String DUKE_TAB4 = "    ";
    private static final String DUKE_TAB2 = "  ";

    private static final String ERROR_MISSING_INDEX = "☹ OOPS!!! Please include the index of the task.";
    private static final String ERROR_ILLEGAL_INDEX = "☹ OOPS!!! The index must be a number "
            + "separated by one whitespace.";
    private static final String ERROR_MISSING_KEYWORD = "☹ OOPS!!! Please include a phrase to search for.";
    private static final String ERROR_MISSING_TASK_DESCRIPTION = "☹ OOPS!!! The description of a task "
            + "cannot be empty.";
    private static final String ERROR_MISSING_DESCRIPTION_AND_DATE = "☹ OOPS!!! Description and dates of a task "
            + "cannot be empty.";
    private static final String ERROR_MISSING_DEADLINE_DATE = "☹ OOPS!!! Deadline dates must be "
            + "specified after \"/by.\"";
    private static final String ERROR_MISSING_EVENT_DATE = "☹ OOPS!!! Deadline dates must be specified after \"/at.\"";
    private static final String ERROR_ILLEGAL_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_IOEXCEPTION_MESSAGE = "☹ OOPS!!! An IOException was caught: ";

    private static final String DELIMITER_DEADLINE_DATE = "/by";
    private static final String DELIMITER_EVENT_DATE = "/at";

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() throws DukeIOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.load());
    }

//    private void recallTasks() throws DukeIOException {
//        this.taskList.addTasks(this.storage.parseFile());
//    }

//    private void listTasks() throws DukeIllegalIndexException {
//        if (this.taskList.getSize() == 0) {
//            System.out.println(DUKE_TAB4 + DUKE_NO_TASKS);
//            return;
//        }
//        System.out.println(DUKE_TAB4 + DUKE_LIST_TASKS);
//        for (int i = 1; i <= this.taskList.getSize(); i++) {
//            System.out.println(String.format("  %s%d.%s",
//                                             DUKE_TAB4,
//                                             i,
//                                             taskList.getTaskAt(i).getStatus()));
//        }
//    }

//    private void listTasks(List<Task> taskList) {
//        if (taskList.size() == 0) {
//            System.out.println(DUKE_TAB4 + DUKE_NO_FOUND_TASKS);
//            return;
//        }
//        System.out.println(DUKE_TAB4 + DUKE_FOUND_TASKS);
//        for (int i = 0; i < taskList.size(); i++) {
//            System.out.println(String.format("  %s%d.%s",
//                    DUKE_TAB4,
//                    i + 1,
//                    taskList.get(i).getStatus()));
//        }
//    }

//    private void finishTask(String[] command) throws DukeIllegalIndexException, DukeIllegalArgumentException {
//        try {
//            int index = Integer.parseInt(command[1]);
//            taskList.markAsDoneTaskAt(index);
//            System.out.println(DUKE_TAB4 + DUKE_MARK_AS_DONE
//                    + DUKE_TAB4 + DUKE_TAB2
//                    + taskList.getTaskAt(index).getStatus());
//        } catch (ArrayIndexOutOfBoundsException e) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_INDEX);
//        } catch (NumberFormatException e) {
//            throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
//        }
//    }

//    private void deleteTask(String[] command) throws DukeIllegalIndexException, DukeIllegalArgumentException {
//        try {
//            int index = Integer.parseInt(command[1]);
//            System.out.println(DUKE_TAB4 + DUKE_DELETE_TASK
//                    + DUKE_TAB4 + DUKE_TAB2 + this.taskList.deleteTaskAt(index).getStatus()
//                    + String.format("\n" + DUKE_TAB4 + DUKE_NUMBER_OF_TASKS, taskList.getSize()));
//        } catch (ArrayIndexOutOfBoundsException e) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_INDEX);
//        } catch (NumberFormatException e) {
//            if (command[1].equals("all")) {
//                this.taskList.deleteAllTasks();
//                System.out.println(DUKE_TAB4 + DUKE_DELETE_ALL_TASKS);
//            } else {
//                throw new DukeIllegalIndexException(ERROR_ILLEGAL_INDEX);
//            }
//        }
//    }

//    private void findTask(String[] command) throws DukeIllegalArgumentException {
//        if (command.length == 1) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_CHAR_SEQUENCE);
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i < command.length; i++) {
//            sb.append(command[i].trim()).append(" ");
//        }
//        List<Task> foundTasks = this.taskList.findAllTasksContaining(sb.toString().trim());
//        listTasks(foundTasks);
//    }

//    private void addTodoTask(String[] command) throws DukeIllegalArgumentException{
//        if (command.length == 1) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
//        }
//
//        StringBuilder task = new StringBuilder();
//        for (int i = 1; i < command.length; i++) {
//            task.append(command[i]).append(" ");
//        }
//        this.addTask(new TodoTask(task.toString().trim()));
//    }

//    private void addDeadlineTask(String[] command) throws DukeIllegalArgumentException, DukeDateFormatException {
//        StringBuilder[] task = new StringBuilder[2];
//        task[0] = new StringBuilder();
//        task[1] = new StringBuilder();
//
//        int index = 0;
//        boolean hasDescription = false;
//        boolean hasDeadlineDate = false;
//        for (int i = 1; i < command.length; i++) {
//            if (!hasDeadlineDate && command[i].equals(DELIMITER_DEADLINE_DATE)) {
//                if (i != command.length - 1) {
//                    hasDeadlineDate = true;
//                    index++;
//                    continue;
//                }
//            } else if (!hasDescription && !hasDeadlineDate && !command[i].equals("")) {
//                hasDescription = true;
//            }
//            task[index].append(command[i]).append(" ");
//        }
//
//        if (!hasDescription && !hasDeadlineDate) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_DESCRIPTION_AND_DATE);
//        } else if (!hasDescription) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
//        } else if (!hasDeadlineDate) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_DEADLINE_DATE);
//        }
//
//        // Have Duke parse the String into date and time
//        DukeDate dueDate = Parser.parseToDate(task[1].toString().trim());
//        this.addTask(new DeadlineTask(task[0].toString().trim(),
//                                      dueDate));
//    }

//    private void addEventTask(String[] command) throws DukeIllegalArgumentException, DukeDateFormatException {
//        StringBuilder[] task = new StringBuilder[2];
//        task[0] = new StringBuilder();
//        task[1] = new StringBuilder();
//
//        int index = 0;
//        boolean hasDescription = false;
//        boolean hasEventDate = false;
//        for (int i = 1; i < command.length; i++) {
//            if (!hasEventDate && command[i].equals(DELIMITER_EVENT_DATE)) {
//                if (i != command.length - 1) {
//                    hasEventDate = true;
//                    index++;
//                    continue;
//                }
//            } else if (!hasDescription && !hasEventDate && !command[i].equals("")) {
//                hasDescription = true;
//            }
//            task[index].append(command[i]).append(" ");
//        }
//
//        if (!hasDescription && !hasEventDate) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_DESCRIPTION_AND_DATE);
//        } else if (!hasDescription) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_TASK_DESCRIPTION);
//        } else if (!hasEventDate) {
//            throw new DukeIllegalArgumentException(ERROR_MISSING_EVENT_DATE);
//        }
//
//        // Have Duke parse the string into date and time
//        DukeDate eventDate = Parser.parseToDate(task[1].toString().trim());
//        this.addTask(new EventTask(task[0].toString().trim(),
//                                   eventDate));
//    }

//    private void addTask(Task task) {
//        taskList.addTask(task);
//        System.out.println(DUKE_TAB4 + DUKE_ADD_TASK
//                + DUKE_TAB4 + DUKE_TAB2 + task.getStatus()
//                + String.format("\n" + DUKE_TAB4 + DUKE_NUMBER_OF_TASKS,
//                taskList.getSize()));
//    }

//    private void saveTasks() throws DukeIOException {
//        this.storage.saveTasks(this.taskList);
//    }

    public void run() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        // Greet the user
        this.ui.greet();

        // Handle user input
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = this.ui.readCommand();
                String description = this.ui.readDescription();
                Command c = Parser.parseToCommand(command, description);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();

//                switch (Command.valueOf(command[0].toUpperCase())) {
//                case LIST:
//                    this.listTasks();
//                    break;
//                case DONE:
//                    this.finishTask(command);
//                    this.saveTasks();
//                    break;
//                case DELETE:
//                    this.deleteTask(command);
//                    this.saveTasks();
//                    break;
//                case FIND:
//                    this.findTask(command);
//                    break;
//                case TODO:
//                    this.addTodoTask(command);
//                    this.saveTasks();
//                    break;
//                case DEADLINE:
//                    this.addDeadlineTask(command);
//                    this.saveTasks();
//                    break;
//                case EVENT:
//                    this.addEventTask(command);
//                    this.saveTasks();
//                    break;
//                }
            } catch (DukeException e) {
                this.ui.printToUser(e.getMessage());
            }
        }
    }
    
}