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
            } else {
                command = command + sc.nextLine();
                Task task = new Task(command);
                this.sheet.add(task);
                System.out.print(Formatter.LINE + Formatter.INDENT + "added: "
                                + command + "\n" + Formatter.LINE);
            }
        }
    }
}
