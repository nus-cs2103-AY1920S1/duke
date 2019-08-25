import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private String filePath;
    private ArrayList<SimpleDateFormat> dateFormats;

    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
        this.filePath = storage.filePath;
        dateFormats = new ArrayList<>() {
            {
                add(new SimpleDateFormat("dd/M/yyyy h a"));
                add(new SimpleDateFormat("dd-M-yyyy h a"));
                add(new SimpleDateFormat("dd.M.yyyy h a"));
                add(new SimpleDateFormat("dd/M/yyyy hhmm a"));
                add(new SimpleDateFormat("dd-M-yyyy hhmm a"));
                add(new SimpleDateFormat("dd.M.yyyy hhmm a"));
                add(new SimpleDateFormat("dd/M/yyyy hh:mm a"));
                add(new SimpleDateFormat("dd-M-yyyy hh:mm a"));
                add(new SimpleDateFormat("dd.M.yyyy hh:mm a"));
                add(new SimpleDateFormat("dd/M/yyyy hhmm"));
                add(new SimpleDateFormat("dd-M-yyyy hhmm"));
                add(new SimpleDateFormat("dd.M.yyyy hhmm"));
                add(new SimpleDateFormat("dd/M/yyyy hh:mm"));
                add(new SimpleDateFormat("dd-M-yyyy hh:mm"));
                add(new SimpleDateFormat("dd.M.yyyy hh:mm"));
                add(new SimpleDateFormat("dd/M/yyyy HHmm"));
                add(new SimpleDateFormat("dd-M-yyyy HHmm"));
                add(new SimpleDateFormat("dd.M.yyyy HHmm"));
                add(new SimpleDateFormat("dd/M/yyyy HH:mm"));
                add(new SimpleDateFormat("dd-M-yyyy HH:mm"));
                add(new SimpleDateFormat("dd.M.yyyy HH:mm"));
                add(new SimpleDateFormat("dd MMM yyyy HHmm"));
                add(new SimpleDateFormat("dd MMMM yyyy HHmm"));
                add(new SimpleDateFormat("dd/M/yyyy"));
                add(new SimpleDateFormat("dd-M-yyyy"));
                add(new SimpleDateFormat("dd.M.yyyy"));
                add(new SimpleDateFormat("dd MMM yyyy"));
                add(new SimpleDateFormat("dd MMMM yyyy"));
            }
        };
    }

    public void process(String userInput) {
        boolean shouldOverwriteFile = false;
        try {
            try {
                String[] inputSplit = userInput.split(" ");
                switch (inputSplit[0]) {
                case "list":
                    ui.printTaskList(tasks.toArrayList());
                    break;
                case "done":
                    if (inputSplit.length != 2) {
                        // Exception if there is no task number or multiple words after "done"
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! Please specify number of a single task to mark as done.\n"
                                + ui.separationLine + "\n");
                    }
                    int specifiedDone = Integer.parseInt(inputSplit[1]); // throws NumberFormatException if not int
                    if (specifiedDone < 1 || specifiedDone > tasks.getSize()) {
                        // Exception if task number is beyond current number of tasks
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! Please specify valid task number.\n"
                                + ui.separationLine + "\n");
                    }
                    Task doneTask = tasks.getElement(specifiedDone - 1);
                    doneTask.setDone();
                    shouldOverwriteFile = true;
                    ui.printDoneNotification(doneTask.toString());
                    break;
                case "todo":
                    if (inputSplit.length == 1) {
                        // Exception if no description after "todo"
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! The description of a todo cannot be empty.\n"
                                + ui.separationLine + "\n");
                    }
                    ToDo todo = new ToDo(userInput.replace("todo ", ""), 0);
                    tasks.addToList(todo);
                    String writeStringT = todo.type + " " + 0 + " " + todo.description + "\n";
                    storage.writeToFile(filePath, writeStringT, true);
                    ui.printAddNotification(todo.toString(), tasks.getSize());
                    break;
                case "deadline":
                    if (!userInput.contains(" /by ")) {
                        // Exception for invalid deadline format
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! For deadline please use the format\n"
                                + "               \"deadline description /by end time\"\n"
                                + ui.separationLine + "\n");
                    }
                    String[] splitStringD = userInput.split(" /by ");
                    Date inputDateD = convertToDate(splitStringD[1], dateFormats);
                    String inputDateStrD = inputDateD == null ? splitStringD[1]
                            : new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(inputDateD);
                    Deadline deadline = new Deadline(splitStringD[0].replace("deadline ", ""), 0,
                            inputDateStrD);
                    tasks.addToList(deadline);
                    String writeStringD = deadline.type + " 0" + " " + deadline.description + " | " + deadline.endTime
                            + "\n";
                    storage.writeToFile(filePath, writeStringD, true);
                    ui.printAddNotification(deadline.toString(), tasks.getSize());
                    break;
                case "event":
                    if (!userInput.contains(" /at ")) {
                        // Exception for invalid deadline format
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! For event please use the format\n"
                                + "               \"event description /at period\"\n"
                                + ui.separationLine + "\n");
                    }
                    String[] splitStringE = userInput.split(" /at ");
                    Event event = new Event(splitStringE[0].replace("event ", ""), 0,
                            splitStringE[1]);
                    tasks.addToList(event);
                    String writeStringE = event.type + " 0" + " " + event.description + " | " + event.eventPeriod
                            + "\n";
                    storage.writeToFile(filePath, writeStringE, true);
                    ui.printAddNotification(event.toString(), tasks.getSize());
                    break;
                case "delete":
                    if (inputSplit.length != 2) {
                        // Exception if there is no task number or multiple words after "delete"
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! Please specify number of a single task to delete.\n"
                                + ui.separationLine + "\n");
                    }
                    int specifiedDel = Integer.parseInt(inputSplit[1]); // will throw NumberFormatException if not int
                    if (specifiedDel < 1 || specifiedDel > tasks.getSize()) {
                        // Exception if task number is beyond current number of tasks
                        throw new DukeException(ui.separationLine
                                + "\n     \u2639 OOPS!!! Please specify valid task number.\n"
                                + ui.separationLine + "\n");
                    }
                    Task delTask = tasks.getElement(specifiedDel - 1);
                    tasks.deleteFromList(specifiedDel - 1);
                    shouldOverwriteFile = true;
                    ui.printDeleteNotification(delTask.toString(), tasks.getSize());
                    break;
                default:
                    // Exception if invalid instruction
                    throw new DukeException(ui.separationLine
                            + "\n     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + ui.separationLine + "\n");
                }
            } catch (DukeException de) {
                ui.printErrorMessage(de.getMessage());
            } catch (NumberFormatException ne) {
                ui.printErrorMessage(ui.separationLine
                        + "\n     \u2639 OOPS!!! Please specify task number as one integer only.\n"
                        + ui.separationLine + "\n");
            }

            if (shouldOverwriteFile) {
                String saveToFile = "";
                ArrayList<Task> listOfTasks = tasks.toArrayList();
                for (Task t : listOfTasks) {
                    saveToFile += String.format("%s %d %s", t.type, t.isDone, t.description);
                    if ("D".equals(t.type)) {
                        saveToFile += " | " + ((Deadline) t).endTime;
                    } else if ("E".equals(t.type)) {
                        saveToFile += " | " + ((Event) t).eventPeriod;
                    }
                    saveToFile += "\n";
                }
                storage.writeToFile(filePath, saveToFile, false);
            }
        } catch (IOException ioe) {
            System.err.println("Failed to save changes to file. Please try again.");
        }
    }

    private static Date convertToDate(String dateString, ArrayList<SimpleDateFormat> dateFormats) {
        Date date = null;
        for (SimpleDateFormat sdf : dateFormats) {
            try {
                sdf.setLenient(false);
                date = sdf.parse(dateString);
            } catch (ParseException pe) {
                // Continue checking for matching date format
            }
            if (date != null) break;
        }
        return date;
    }
}
