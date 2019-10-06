package duke;

import duke.Storage;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.InvalidTaskException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDateTime;

/**
 * Main class for Duke chatbot.
 * Handles user input.
 */
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storageHandler = new Storage();
        TaskList tasks = new TaskList(storageHandler.load());
        Parser parser = new Parser();
        Executor executor = new Executor(ui, storageHandler, tasks, parser);
        executor.start();
    }

//
//
//
//
//    /**
//     * Adds and subsequently displays a new todo created from the user input.
//     *
//     * @param userInput string representing the todo the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a todo.
//     */
//    private static void addAndDisplayNewTodo(String userInput) throws InvalidTaskException {
//        Todo newTodo = new Todo(userInput.substring("todo ".length()));
//        tasks.add(newTodo);
//        storageHandler.save(tasks);
//        displayAddedTask(newTodo);
//    }
//
//    /**
//     * Adds and subsequently displays a new deadline created from the user input.
//     *
//     * @param userInput string representing the deadline the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a deadline.
//     */
//    private static void addAndDisplayNewDeadline(String userInput) throws InvalidTaskException {
//        String[] descriptionAndDate = userInput.substring("deadline ".length()).split("/by ", 2);
//        String description = descriptionAndDate[0];
//        LocalDateTime dueDate = LocalDateTime
//                .parse(descriptionAndDate[1].trim(), Deadline.dueDateFormat);
//        Deadline newDeadline = new Deadline(description, dueDate);
//        tasks.add(newDeadline);
//        storageHandler.save(tasks);
//        displayAddedTask(newDeadline);
//    }
//
//    /**
//     * Adds and subsequently displays a new event created from the user input.
//     *
//     * @param userInput string representing the event the user intends to create.
//     * @throws InvalidTaskException when there are insufficient parameters to initialize a event.
//     */
//    private static void addAndDisplayNewEvent(String userInput) throws InvalidTaskException {
//        String[] descriptionAndDateTimes = userInput.substring("event ".length()).split("/at ", 2);
//        String[] startAndEndDateTimes = descriptionAndDateTimes[1].split("-", 2);
//        String description = descriptionAndDateTimes[0];
//        LocalDateTime startDateTime = LocalDateTime
//                .parse(startAndEndDateTimes[0].trim(), Event.eventDateTimeFormat);
//        LocalDateTime endDateTime = LocalDateTime
//                .parse(startAndEndDateTimes[1].trim(), Event.eventDateTimeFormat);
//
//        Event newEvent = new Event(description, startDateTime, endDateTime);
//        tasks.add(newEvent);
//        storageHandler.save(tasks);
//        displayAddedTask(newEvent);
//    }
//
//    /**
//     * Deletes and subsquently displays the task at the input task index.
//     *
//     * @param taskIndex task index of the task to be deleted.
//     */
//    private static void deleteAndDisplayTask(int taskIndex) {
//        Task task = tasks.get(taskIndex);
//        tasks.remove(taskIndex);
//        storageHandler.save(tasks);
//        dukeReply("I have removed the following task:\n  " + task + "\nNow you have " +
//                tasks.size() + " tasks in the list.");
//    }


}
