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
                int index = sc.nextInt();
                this.sheet.markAsDone(index);
                System.out.println(Formatter.LINE + Formatter.INDENT + Formatter.DONE + Formatter.INDENT +
                        sheet.get(index - 1).toString() + "\n" + Formatter.LINE);
            } else if (command.equals("todo")){
                Task todoTask = new Todo(sc.nextLine());
                this.sheet.add(todoTask);
            }else if (command.equals("deadline")) {
                String next;
                String description = "";
                while (!(next = sc.next()).equals("/by")) {
                    description += " " + next;
                }
                String by = sc.nextLine().trim();
                Task dlTask = new Deadline(description, by);
                this.sheet.add(dlTask);
            } else if (command.equals("event")){
                String next;
                String description = "";
                while (!(next = sc.next()).equals("/at")) {
                    description += " " + next;
                }
                String span = sc.nextLine().trim();
                Task eventTask = new Event(description, span);
                this.sheet.add(eventTask);
            } else {}
        }
    }
}
