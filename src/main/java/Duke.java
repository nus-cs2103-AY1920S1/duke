import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<Task> tasks;
    static final String HORIZONTAL_LINE = "    ____________________________________________________________\n";

    public Duke() {
        IOHandler ioHandler = new IOHandler();

        try {
            tasks = ioHandler.readSaveFile();
        } catch (IOException e) {
            System.out.println("Something went wrong while reading save file... Continuing...");
        }
    }
    
    private String makeSpace(int n) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(' ');
        }
        return str.toString();
    }
    
    public void greet() {
        System.out.print(HORIZONTAL_LINE);
        
        String logo = makeSpace(5) + " ____        _        \n"
                + makeSpace(5) + "|  _ \\ _   _| | _____ \n"
                + makeSpace(5) + "| | | | | | | |/ / _ \\\n"
                + makeSpace(5) + "| |_| | |_| |   <  __/\n"
                + makeSpace(5) + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMessage;

        if (tasks.size() == 0) {
            welcomeMessage = String.format("%s\n%sHello! I'm Duke!\n%sWhat can I do for you?", logo, makeSpace(5), 
                    makeSpace(5));
        } else {
            welcomeMessage = String.format("%s\n%sHello! Welcome back!\n", logo, makeSpace(5))
            + String.format("%sCarrying off from where you left behind the last time...", makeSpace(5));
        }
        
        System.out.printf("%s\n%s\n", welcomeMessage, HORIZONTAL_LINE);

        if (tasks.size() > 0) {
            listAllTasks();
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.printf("%sBye! Hope to see you again soon! \u263A\n", makeSpace(5));
        System.out.println(HORIZONTAL_LINE);
    }

    public void evaluate(String instruction) throws VoidDukeCommand, IncorrectDukeCommand, IOException {
        System.out.println(HORIZONTAL_LINE);

        Scanner userInput = new Scanner(instruction);
        String command;

        if (userInput.hasNext()) {
            command = userInput.next();
        } else {
            userInput.close();
            throw new VoidDukeCommand();
        }

        String errorMessage = null;

        if (command.equals("list")) {

            if (userInput.hasNext()) {
                errorMessage = makeSpace(5) + "The command \"list\" should not have anything after!\n"
                        + makeSpace(5) + "Please remove any additional words!";
            } else {
                listAllTasks();
            }

        } else if (command.equals("bye")) {

            if (userInput.hasNext()) {
                errorMessage = makeSpace(5) + "The command \"bye\" should not have anything after!\n"
                        + makeSpace(5) + "Do you really intend to quit?";
            } else {
                userInput.close();
                return;
            }

        } else if (command.equals("done") || command.equals("delete")) {
            int taskNumber;

            if (userInput.hasNextInt()) {
                taskNumber = userInput.nextInt();

                if (taskNumber <= 0) {
                    errorMessage = String.format("%sNumber cannot be negative!", makeSpace(5));
                } else if (tasks.size() == 0) {
                    errorMessage = String.format("%sYou don't have any tasks yet!", makeSpace(5));
                } else if (taskNumber > tasks.size()) {
                    errorMessage = String.format("%sYou don't have that many tasks!", makeSpace(5));
                } else {
                    switch (command) {
                    case "done":
                        markTaskAsDone(taskNumber - 1);
                        break;
                    case "delete":
                        delete(taskNumber - 1);
                        break;
                    default:
                        break;
                    }
                }

            } else {
                errorMessage = makeSpace(5) + "Please input a non-negative Integer after the \"done\" command!\n"
                            + makeSpace(5) + "Also do ensure that the Integer is not out of range!";
            }

        } else if (command.equals("todo")) {

            if (userInput.hasNext()) {
                String details = userInput.nextLine().strip();
                makeNewTodo(details);
            } else {
                errorMessage = makeSpace(5) + "The description of a todo cannot be empty!";
            }

        } else if (command.equals("deadline")) {
            if (userInput.hasNext()) {
                
                if (instruction.contains(" by ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" by ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = makeSpace(5) + "You are missing the details/date&time of your Deadline!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = makeSpace(5) + "The details of your deadline cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = makeSpace(5) + "The date/time of your deadline cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String[] dateTime = contentDateTime[1].strip().split(" ");

                        if (dateTime.length == 1) {
                            makeNewDeadline(taskDescription, dateTime[0], "");
                        } else if (dateTime.length == 2) {
                            makeNewDeadline(taskDescription, dateTime[0], dateTime[1]);
                        } else if (dateTime.length == 3) {
                            makeNewEvent(taskDescription, dateTime[0] + " " + dateTime[1], dateTime[2]);
                        } else {
                            errorMessage = makeSpace(5) + "Sorry but there seems to be too much information in the\n"
                                    + makeSpace(5) + "Date & Time field of your deadline...\uD83D\uDE25";
                        }
                    }
                } else {
                    errorMessage = makeSpace(5) + "Sorry but I can't seem to detect the due date of the deadline!";
                }

            } else {
                errorMessage = makeSpace(5) + "The description of a Deadline cannot be empty!";
            }

        }  else if (command.equals("event")) {
            if (userInput.hasNext()) {
                
                if (instruction.contains(" at ")) {
                    String[] contentDateTime = userInput.nextLine().strip().split(" at ");

                    if (contentDateTime.length == 0 || contentDateTime.length == 1) {
                        errorMessage = makeSpace(5) + "You are missing the details/date&time of your Event!";
                    } else if (contentDateTime[0].isBlank()) {
                        errorMessage = makeSpace(5) + "The details of your Event cannot be empty!";
                    } else if (contentDateTime[1].isBlank()) {
                        errorMessage = makeSpace(5) + "The date/time of your Event cannot be empty!";
                    } else {
                        String taskDescription = contentDateTime[0].strip();
                        String[] dateTime = contentDateTime[1].strip().split(" ");

                        if (dateTime.length == 1) {
                            makeNewEvent(taskDescription, dateTime[0], "");
                        } else if (dateTime.length == 2) {
                            makeNewEvent(taskDescription, dateTime[0], dateTime[1]);
                        } else if (dateTime.length == 3) {
                            makeNewEvent(taskDescription, dateTime[0] + " " + dateTime[1], dateTime[2]);
                        } else {
                            errorMessage = makeSpace(5) + "Sorry but there seems to be too much information in the\n"
                                    + makeSpace(5) + "Date & Time field of your event...\uD83D\uDE25";
                        }
                    }
                } else {
                    errorMessage = makeSpace(5) + "Sorry but I can't seem to detect the Date & Time of the event!";
                }

            } else {
                errorMessage = makeSpace(5) + "The description of a Event cannot be empty!";
            }
        } else {
            userInput.close();
            throw new InvalidDukeCommand();
        }

        if (errorMessage != null) {
            userInput.close();
            throw new IncorrectDukeCommand(errorMessage);
        }

        userInput.close();
        System.out.println(HORIZONTAL_LINE);
    }

    private void markTaskAsDone(int taskNumber) throws IOException {
        if (tasks.get(taskNumber).isDone) {
            System.out.println(makeSpace(5) + "Task has already been marked done!");
            return;
        }
        
        tasks.get(taskNumber).isDone = true;
        IOHandler ioHandler = new IOHandler();
        ioHandler.overwriteLocalSave(tasks);
        
        System.out.printf("%sNice! I've marked this task as done:\n", makeSpace(5));
        System.out.printf("%s%s\n", makeSpace(7), tasks.get(taskNumber));
    }

    private void listAllTasks() {
        if (tasks.size() == 0) {
            System.out.printf("%sThere are no task(s) to list!\n", makeSpace(5));
        } else {
            System.out.printf("%sHere are the tasks in your list:\n", makeSpace(5));
    
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%s%d. %s\n", makeSpace(7), i + 1, tasks.get(i));
            }
        }
    }

    private void makeNewTodo(String description) throws IOException {
        IOHandler ioHandler = new IOHandler();
        ToDo currentTodo = new ToDo(description);

        tasks.add(currentTodo);
        ioHandler.addToLocalSave(currentTodo);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentTodo);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }

    private void makeNewDeadline(String desc, String date, String time) throws IOException {
        IOHandler ioHandler = new IOHandler();
        Deadline currentDeadline = new Deadline(desc, date, time);

        tasks.add(currentDeadline);
        ioHandler.addToLocalSave(currentDeadline);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentDeadline);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }

    private void makeNewEvent(String desc, String date, String time) throws IOException {
        IOHandler ioHandler = new IOHandler();
        Event currentEvent = new Event(desc, date, time);

        tasks.add(currentEvent);
        ioHandler.addToLocalSave(currentEvent);

        System.out.printf("%sGot it! I've added this task for you \uD83D\uDE09\n", makeSpace(5));
        System.out.printf("%s%s\n\n", makeSpace(7), currentEvent);
        System.out.printf("%sNow you have %d task(s) in your list.\n", makeSpace(5), tasks.size());
    }

    private void delete(int taskNumber) throws IOException {
        IOHandler ioHandler = new IOHandler();
        
        Task removedTask = tasks.remove(taskNumber);
        ioHandler.overwriteLocalSave(tasks);

        System.out.println(makeSpace(5) + "Noted. I've removed this task:\n"
                + makeSpace(7) + removedTask + "\n\n"
                + makeSpace(5) + String.format("Now you have %d task(s) in your list.", tasks.size()));
        }
}
