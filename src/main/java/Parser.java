import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

import java.util.Scanner;

public class Parser {

    private Sheet sheet;

    public void start(Sheet sheet) {

        Scanner sc = new Scanner(System.in);
        TimeManager tm = new TimeManager();
        Ui ui = new Ui();
        this.sheet = sheet;

        ui.showLogo();
        ui.showWelcome();

        while (sc.hasNext()) {
            String command = sc.next();
            try {
                if (command.equals("bye")) {
                    ui.showGoodbye();
                    break;
                } else if (command.equals("list")) {
                    this.sheet.showList();
                } else if (command.equals("delete") || command.equals("done")) {
                    try {
                        int index = sc.nextInt();
                        if (index > this.sheet.getNumOfTask()) {
                            if (this.sheet.isEmpty()) {
                                throw new TaskNotFoundException("☹ OOPS!!! The list is empty.");
                            } else {
                                throw new TaskNotFoundException("☹ OOPS!!! The list contains only " +
                                        this.sheet.getNumOfTask() +
                                        (this.sheet.getNumOfTask() == 1 ? " task." : " tasks."));
                            }
                        }
                        if (index < 1) {
                            throw new TaskNotFoundException("☹ OOPS!!! Do we have non-positive tasks?");
                        }
                        if (command.equals("done")) {
                            this.sheet.markAsDone(index);
                        } else {
                            this.sheet.delete(index);
                        }
                    } catch (TaskNotFoundException tfe) {
                        ui.printDukeException(tfe);
                    } catch (InputMismatchException ime) {
                        ui.printTaskIndexMismatchException();
                        sc.nextLine();
                    }
                } else if (command.equals("todo")) {
                    try {
                        String description = sc.nextLine().trim();
                        if (description.isBlank()) {
                            throw new MissingDescriptionException(
                                    "☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todoTask = new Todo(description);
                        this.sheet.add(todoTask);
                    } catch (MissingDescriptionException e) {
                        ui.printDukeException(e);
                    }
                } else if (command.equals("deadline")) {
                    try {
                        String next;
                        String description = "";
                        while (!(next = sc.next()).equals("/by")) {
                            description += " " + next;
                        }
                        String by = sc.nextLine().trim();
                        if (by.isBlank()) {
                            throw new MissingDescriptionException(
                                    "☹ OOPS!!! The deadline cannot be empty."
                            );
                        }
                        if (description.isBlank()) {
                            throw new MissingDescriptionException(
                                    "☹ Oh! Did you forget to add the task?");
                        }
                        Task dlTask = new Deadline(description, tm.getTime(by));
                        this.sheet.add(dlTask);
                    } catch (MissingDescriptionException dl) {
                        ui.printDukeException(dl);
                    } catch (IllegalTimeFormatException itef){
                        ui.printDukeException(itef);
                    }
                } else if (command.equals("event")) {
                    try {
                        String next;
                        String description = "";
                        while (!(next = sc.next()).equals("/from")) {
                            description += " " + next;
                        }

                        String[] span = sc.nextLine().trim().split("to");
                        if (span.length < 2) {
                            throw new MissingDescriptionException("☹ OOPS!!! The event span is incomplete.");
                        }
                        if (description.isBlank()) {
                            throw new MissingDescriptionException(
                                    "☹ Oh! Did you forget to add the task?");
                        }
                        Task eventTask = new Event(description, tm.getTime(span[0]), tm.getTime(span[1]));
                        this.sheet.add(eventTask);
                    } catch (MissingDescriptionException mde) {
                        ui.printDukeException(mde);
                    } catch (IllegalTimeFormatException itef){
                        ui.printDukeException(itef);
                    }
                } else {
                    ui.showUnknownError();
                }
            } catch (FileNotFoundException e) {
                ui.printFileNotFoundException();
            } catch (IOException e) {
                ui.printIOException();
            }
        }
    }
}
