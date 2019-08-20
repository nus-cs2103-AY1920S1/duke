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
                    if (!s.hasNextLine()) {
                        duke.error(action);
                    } else {
                        Todo todo = new Todo(s.nextLine());
                        duke.add(todo);

                        //echo the text added
                        duke.echo(todo);
                    }
                    break;
                case "deadline":
                    if (!s.hasNextLine()) {
                        duke.error(action);
                    } else {
                        String[] d = s.nextLine().split("/by", 2);
                        Deadline deadline = new Deadline(d[0], d[1]);
                        duke.add(deadline);

                        //echo the text added
                        duke.echo(deadline);
                    }
                    break;
                case "event":
                    if (!s.hasNextLine()) {
                        duke.error(action);
                    } else {
                        String[] e = s.nextLine().split(" /at ", 2);
                        Event event = new Event(e[0], e[1]);
                        duke.add(event);

                        //echo the text added
                        duke.echo(event);
                    }
                    break;
                case "delete":
                    if (!s.hasNextInt()) {
                        duke.error(action);
                    } else {
                        duke.delete(s.nextInt());
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
