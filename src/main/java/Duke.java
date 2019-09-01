import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Exception;
import java.time.LocalDateTime;

class DukeException extends Exception {
    public DukeException(String s) {
        super(s);
    }
}

public class Duke {
    private ArrayList<Task> todoList;

    public Duke() {
        this.todoList = new ArrayList<Task>();
    }

    private static void print(String message) {
        System.out.println(">>" + message);
    }

    private void list() {
        StringBuilder outputMessageMessage = new StringBuilder("List:");
        int i = todoList.size();
        for (int x = 1; x <= i; x++) {
            outputMessageMessage.append("\n  " + x + "." + todoList.get(x - 1));
        }
        String outputMessage = outputMessageMessage.toString();
        print(outputMessage);
    }

    private void run(String userInput) {
        userInput = userInput.trim();
        int spacePosition = userInput.indexOf(" ");
        if (spacePosition == -1) {  //raise exception: only 1 word given
            try {
                this.raiseSingleWordInput(userInput);
            } catch (DukeException e) {
                Duke.print(e.getMessage());
            }
        } else {
            String command = userInput.substring(0, spacePosition);
            String taskContent = userInput.substring(spacePosition + 1);
            if (command.equals("done")) {
                this.done(taskContent);
            } else if (command.equals("delete")) {
                this.delete(taskContent);
            } else if (command.equals("todo")) {
                this.addTodo(taskContent);
            } else if (command.equals("event")) {
                this.addEvent(taskContent);
            } else if (command.equals("deadline")) {
                this.addDeadline(taskContent);
            } else {
                Duke.print("Error: input not recognized");  //
            }
        }
    }

    private void done(String doneIndex) {
        try {
            int doneInt = Integer.parseInt(doneIndex);
            this.todoList.get(doneInt - 1).setDone();
        } catch (NumberFormatException e) {
            Duke.print("Error: bad task index"); // for wrong index provided
        } catch (IndexOutOfBoundsException e) {
            Duke.print("Error: no such task index");  //for index>array length
        }
    }

    private void delete(String deleteIndex) {  //exception same as done method
        try {
            int deleteInt = Integer.parseInt(deleteIndex);
            StringBuilder outputMessageMessage = new StringBuilder("Following task removed:\n    "
                    + this.todoList.get(deleteInt - 1));
            outputMessageMessage.append("\n  " + (this.todoList.size() - 1) + " tasks left in the list");
            this.todoList.remove(deleteInt - 1);
            Duke.print(outputMessageMessage.toString());
        } catch (NumberFormatException e) {
            Duke.print("Error: bad task index");
        } catch (IndexOutOfBoundsException e) {
            Duke.print("Error: no such task index");
        }
    }

    private void addTodo(String task) {
        task = task.trim();
        Todo td = new Todo(task);
        this.todoList.add(td);
        StringBuilder outputMessage = new StringBuilder("Task added:\n");
        outputMessage.append("    " + td);
        outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list");
        Duke.print(outputMessage.toString());
    }

    private void addEvent(String task) {
        int split = task.indexOf(" /at");
        if (split == -1) {
            Duke.print("Error: event time not given. Specify event time using \"/at\""); //throw exception?
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);  //exception may occur
                description = description.trim();
                deadline = deadline.trim();
                Event e = new Event(description, deadline);
                this.todoList.add(e);
                StringBuilder outputMessage = new StringBuilder("Task added:\n");
                outputMessage.append("    " + e);
                outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list.");
                Duke.print(outputMessage.toString());
            } catch (IndexOutOfBoundsException e) { // happens when input is "event xx /at" with no time given
                Duke.print("Error: no event time provided");
            }
        }
    }

    private void addDeadline(String task) {
        int split = task.indexOf(" /by");
        if (split == -1) {
            Duke.print("Error: deadline not given. Specify deadline using \"/by\"");
        } else {
            try {
                String description = task.substring(0, split);
                String deadline = task.substring(split + 5);
                description = description.trim();
                deadline = deadline.trim();
                Deadline d = new Deadline(description, deadline);
                this.todoList.add(d);
                StringBuilder outputMessage = new StringBuilder("Task added:\n");
                outputMessage.append("    " + d);
                outputMessage.append("\n  There are " + this.todoList.size() + " tasks in the list.");
                Duke.print(outputMessage.toString());
            } catch (IndexOutOfBoundsException e) { //same as event time
                Duke.print("Error: no deadline provided");
            }
        }
    }

    private void raiseSingleWordInput(String badInput) throws DukeException {
        if (badInput.equals("todo") || badInput.equals("event") || badInput.equals("deadline")) {
            throw new DukeException("Error: no description for task.");
        } else if (badInput.equals("done")) {
            throw new DukeException("Error: done task index missing");
        } else {
            throw new DukeException("Error: no such command");
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void setDone() {
            this.isDone = true;
            String message = "The following task has been marked as done:\n    " + this;
            Duke.print(message);
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[" + "\u2713" + "]" + this.description;
            } else {
                return "[" + "\u2718" + "]" + this.description;
            }
        }
    }

    public class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            if (isDone) {
                return "[T]" + "[" + "\u2713" + "]" + this.description;
            } else {
                return "[T]" + "[" + "\u2718" + "]" + this.description;
            }
        }
    }

    public class Event extends Task {
        private LocalDateTime startDateTime;   // DD/MM/YYYY
        private LocalDateTime endDateTime;

        public Event(String description, String timePeriod) {
            super(description);
            this.setDateTime(timePeriod);
        }

        private void setDateTime(String timePeriod){
            String datetimeInput[] = timePeriod.split(" ");
            String dateInput[] = datetimeInput[0].split("/");
            String timeInput[] = datetimeInput[1].split("-");
            int year, month, day, startHour, startMinute, endHour, endMinute;
            year = Integer.parseInt(dateInput[2]);
            month = Integer.parseInt(dateInput[1]);
            day = Integer.parseInt(dateInput[0]);
            startHour = Integer.parseInt(timeInput[0])/100;
            startMinute = Integer.parseInt(timeInput[0])%100;
            endHour = Integer.parseInt(timeInput[1])/100;
            endMinute = Integer.parseInt(timeInput[1])%100;
            this.startDateTime = LocalDateTime.of(year, month, day, startHour, startMinute);
            this.endDateTime = LocalDateTime.of(year, month, day, endHour, endMinute);
        }
        @Override
        public String toString() {
            if (isDone) {
                return "[E]" + "[" + "\u2713" + "]" + this.description + " (at: " + this.startDateTime.toLocalDate()
                        + " " + this.startDateTime.toLocalTime() + "-" + this.endDateTime.toLocalTime() + ")";
            } else {
                return "[E]" + "[" + "\u2718" + "]" + this.description + " (at: " + this.startDateTime.toLocalDate()
                        + " " + this.startDateTime.toLocalTime() + "-" + this.endDateTime.toLocalTime() + ")";
            }
        }
    }

    public class Deadline extends Task {
        private LocalDateTime deadline; //   DD/MM/YYYY

        protected Deadline(String description, String deadline) {
            super(description);
            try {
                this.setDateTime(deadline);
            }catch(NumberFormatException e){
                print("Bad time input");
            }
        }

        private void setDateTime(String deadline){
            String datetimeInput[] = deadline.split(" ");
            String dateInput[] = datetimeInput[0].split("/");
            String timeInput = datetimeInput[1];
            int year, month, day, hour, minute;
            year = Integer.parseInt(dateInput[2]);
            month = Integer.parseInt(dateInput[1]);
            day = Integer.parseInt(dateInput[0]);
            hour = Integer.parseInt(timeInput)/100;
            minute = Integer.parseInt(timeInput)%100;
            this.deadline = LocalDateTime.of(year, month, day, hour, minute);
        }
        @Override
        public String toString() {
            if (isDone) {
                return "[D]" + "[" + "\u2713" + "]" + this.description + " (by: " + this.deadline.toLocalDate() + " "
                        + this.deadline.toLocalTime() + ")";
            } else {
                return "[D]" + "[" + "\u2718" + "]" + this.description + " (by: " + this.deadline.toLocalDate() + " "
                        + this.deadline.toLocalTime() + ")";
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| OwO\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke process = new Duke();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                process.list();
            } else {
                process.run(input);
            }
        }
        String exitMessage = "Goodbye. Hope to see you again. UwU";
        print(exitMessage);
    }
}
