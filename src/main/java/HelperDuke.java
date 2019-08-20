import java.util.Scanner;

public class HelperDuke {
    public static void main(String[] args) {
        //start a Duke
        Duke duke = new Duke();

        //introduction
        duke.startDuke();

        //input action
        Scanner s = new Scanner(System.in);
        String action = s.next().toLowerCase();

        while (!action.equals("bye")) {
            switch (action) {
                case "list":
                    duke.listAll();
                    break;
                case "done":
                    //get the specified task
                    int index = s.nextInt();
                    duke.markAsDone(index);
                    break;
                case "todo":
                    String tTask = s.nextLine();
                    if (tTask.isEmpty()) {
                        duke.error(action);
                    } else {
                        Todo todo = new Todo(tTask);
                        duke.add(todo);

                        //echo the text added
                        duke.echo(todo);
                    }
                    break;
                case "deadline":
                    String dTask = s.nextLine();
                    if (dTask.isEmpty()) {
                        duke.error(action);
                    } else {
                        String[] d = dTask.split("/by", 2);
                        Deadline deadline = new Deadline(d[0], d[1]);
                        duke.add(deadline);

                        //echo the text added
                        duke.echo(deadline);
                    }
                    break;
                case "event":
                    String eTask = s.nextLine();
                    if (eTask.isEmpty()) {
                        duke.error(action);
                    } else {
                        String[] e = eTask.split(" /at ", 2);
                        Event event = new Event(e[0], e[1]);
                        duke.add(event);

                        //echo the text added
                        duke.echo(event);
                    }
                    break;
                default:
                    //no such action
                    duke.error();
                    break;
            }

            //ask for next action
            action = s.next().toLowerCase();
        }

        //ending
        duke.endDuke();
    }


}
