import java.util.InputMismatchException;
import java.util.Scanner;

public class Listener {

    private Sheet sheet;

    public void start(Sheet sheet) {

        Scanner sc = new Scanner(System.in);
        this.sheet = sheet;

        System.out.print(Formatter.WELCOME);

        while(sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.print(Formatter.LINE + Formatter.INDENT + Formatter.GOODBYE + "\n" + Formatter.LINE);
                break;
            } else if (command.equals("list")) {
                this.sheet.showList();
            } else if (command.equals("done")) {
                try {
                    int index = sc.nextInt();
                    if (index > this.sheet.numOfTask) {
                        if (this.sheet.isEmpty()) {
                            throw new TaskNotFoundException("☹ OOPS!!! The list is empty.");
                        } else {
                            throw new TaskNotFoundException("☹ OOPS!!! The list contains only " +
                                    this.sheet.numOfTask + (this.sheet.numOfTask == 1 ? " task." : " tasks."));
                        }
                    }
                    if (index < 1) {
                        throw new TaskNotFoundException("☹ OOPS!!! Do we have non-positive tasks?");
                    }
                    this.sheet.markAsDone(index);
                } catch (TaskNotFoundException tfe){
                    System.out.printf(tfe.toString());
                } catch (InputMismatchException ime) {
                    System.out.printf(
                            Formatter.LINE + Formatter.INDENT +
                                    "☹ OOPS!!! I cannot recognise that task index. :-(" + "\n"
                                    + Formatter.LINE );
                    sc.nextLine();
                }
            } else if (command.equals("todo")){
                try {
                    String description = sc.nextLine();
                    if (description.isBlank()) {
                        throw new MissingDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task todoTask = new Todo(description);
                    this.sheet.add(todoTask);
                } catch (MissingDescriptionException e) {
                    System.out.printf(e.toString());
                }
            }else if (command.equals("deadline")) {
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
                    Task dlTask = new Deadline(description, by);
                    this.sheet.add(dlTask);
                } catch (MissingDescriptionException dl) {
                    System.out.printf(dl.toString());
                } finally {

                }
            } else if (command.equals("event")){
                try {
                    String next;
                    String description = "";
                    while (!(next = sc.next()).equals("/at")) {
                        description += " " + next;
                    }
                    String span = sc.nextLine().trim();
                    if (span.isBlank()) {
                        throw new MissingDescriptionException("☹ OOPS!!! The event time cannot be empty.");
                    }
                    Task eventTask = new Event(description, span);
                    this.sheet.add(eventTask);
                } catch (MissingDescriptionException mde) {
                    System.out.printf(mde.toString());
                } finally {
                }
            } else {
                try {
                    throw new IllegalCommandException(Formatter.UNKNOWN);
                } catch (IllegalCommandException ice) {
                    System.out.printf(ice.toString());
                }
            }
        }
    }
}
